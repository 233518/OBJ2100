package com.eksamen.networking;

import com.eksamen.components.Rom;
import com.eksamen.scenes.ClientScene;
import com.eksamen.scenes.ServerScene;
import com.eksamen.systems.chatsystem.DeltakerTabell;
import com.eksamen.systems.chatsystem.InndataTabell;
import com.eksamen.utils.LogOperations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * SyncServer håndterer hva som skal gjøres under input fra nettverk
 * Sender også informasjon til klienter
 */
public class SyncServer {
    private BufferedWriter bufferedWriter;
    private ServerScene serverScene;
    private ServerNetworking serverNetworking;
    private LogNetwork logNetwork;

    /**
     * Konstruerer en ny SyncServer
     * @param bufferedWriter buffered skriver
     * @param serverScene scene som den tilhører
     * @param serverNetworking server side nettverksdel
     * @param logNetwork logger for nettverks operasjoner
     */
    public SyncServer(BufferedWriter bufferedWriter, ServerScene serverScene, ServerNetworking serverNetworking, LogNetwork logNetwork) {
        this.bufferedWriter = bufferedWriter;
        this.serverScene = serverScene;
        this.serverNetworking = serverNetworking;
        this.logNetwork = logNetwork;
    }

    /**
     * Synker server med info fra klient
     * @param message melding fra klient
     */
    public void syncServer(String message, ClientSocket clientSocket)  {
        if(message != null) {
            switch(message.split(":")[0]) {
                case "newRoom":
                    newRoomServer(message, clientSocket);
                    break;
                case "newMessage":
                    newMessageServer(message, clientSocket);
                    break;
                case "newBruker":
                    newBrukerServer(message, clientSocket);
                    break;
                case "removeBruker":
                    removeBrukerServer(message, clientSocket);
                    break;
                case "removeRoom":
                    removeRoom(message, clientSocket);
                    break;
                case "newKobling":
                    newKobling(message, clientSocket);
                    break;
            }
        }
    }

    /**
     * Får kommando fra klient at ny bruker har koblet til chatteprogrammet og er klar
     * Sender samme kommando til alle andre klienter
     * @param message melding fra klient
     * @param clientSocket klienten meldingen kom fra
     */
    private void newKobling(String message, ClientSocket clientSocket) {
        //0 - kommando
        //1 - brukernavn
        //2 - ipadresse
        String[] messageArray = message.split(":");
        serverNetworking.updateClientsWithNewKobling(messageArray[1], clientSocket);
        logNetwork.logToDatabase(messageArray[1], LogOperations.NY_BRUKER_KOBLING.getHandling(), messageArray[2], "");
    }

    /**
     * Får kommando fra klient at bruker har forlatt rom
     * Sletter rom hos server og sender samme kommando til alle andre klienter
     * @param message melding fra klient
     * @param clientSocket klienten meldingen kom fra
     */
    private void removeRoom(String message, ClientSocket clientSocket) {
        //0 - kommando
        //1 - romnavn
        //2 - brukernavn
        //3 - ipadresse
        String[] messageArray = message.split(":");
        Rom rom = null;
        for(Rom room : serverScene.getRooms()) {
            String romNavn = room.getRomNavn();
            if(romNavn.equals(messageArray[1])) {
                rom = room;
            }
        }
        serverScene.getRooms().remove(rom);
        serverScene.getRomSystem().removeRom(rom);
        serverNetworking.updateClientsWithRemoveRoom(messageArray[1], messageArray[2], clientSocket);
        logNetwork.logToDatabase(messageArray[2], LogOperations.FJERNA_ROM.getHandling(), messageArray[3], messageArray[1]);
    }

    /**
     * Får kommando fra klient at bruker har forlatt rom
     * Fjerner bruker fra rom og sender samme kommando til alle andre klienter
     * @param message melding fra klient
     * @param clientSocket klienten meldingen kom fra
     */
    private void removeBrukerServer(String message, ClientSocket clientSocket) {
        //0 - kommando
        //1 - romnavn
        //2 - brukernavn
        //3 - ipadresse
        String[] messageArray = message.split(":");
        Rom rom = null;
        DeltakerTabell deltakerFunnet = null;
        for(Rom room : serverScene.getRooms()) {
            String romNavn = room.getRomNavn();
            if(romNavn.equals(messageArray[1])) {
                rom = room;
                break;
            }
        }
        if(rom != null) {
            for(DeltakerTabell deltaker : rom.getBrukere()) {
                String deltakerNavn = deltaker.getBrukernavn();
                if(deltakerNavn.equals(messageArray[2])) {
                    deltakerFunnet = deltaker;
                    break;
                }
            }
        }
        if(deltakerFunnet != null) {
            rom.slettDeltaker(deltakerFunnet);
        }
        if(serverScene.getBruker().getRom() != null) {
            if(rom.getRomNavn().equals(serverScene.getBruker().getRom().getRomNavn())) {
                serverScene.getServerUi().getHovedLayout().getRomChat().oppdaterDeltakerListe(serverScene.getRomSystem().getDeltakere(rom));
            }
        }
        serverNetworking.updateClientsWithRemoveUserInRoom(messageArray[1], messageArray[2], clientSocket);
        logNetwork.logToDatabase(messageArray[2], LogOperations.FJERNA_ROM_BRUKER.getHandling(), messageArray[3], messageArray[1]);
    }

    /**
     * Får kommando fra klient at ny bruker har logget på rom
     * Legger til ny bruker og sender samme kommando til alle andre klienter
     * @param message melding fra klient
     * @param clientSocket klienten meldingen tilhører
     */
    private void newBrukerServer(String message, ClientSocket clientSocket) {
        //0 - kommando
        //1 - romnavn
        //2 - brukernavn
        //3 - ipadresse
        String[] messageArray = message.split(":");
        for(Rom room : serverScene.getRooms()) {
            String romNavn = room.getRomNavn();
            if(romNavn.equals(messageArray[1])) {
                room.leggTilDeltaker(new DeltakerTabell(messageArray[2]));
                if(serverScene.getBruker().getRom() != null) {
                    if(romNavn.equals(serverScene.getBruker().getRom().getRomNavn())) {
                        serverScene.getServerUi().getHovedLayout().getRomChat().oppdaterDeltakerListe(serverScene.getRomSystem().getDeltakere(room));
                    }
                }
                break;
            }
        }
        serverNetworking.updateClientsWithNewUserInRoom(messageArray[1], messageArray[2], clientSocket);
        logNetwork.logToDatabase(messageArray[2], LogOperations.NY_BRUKER_ROM.getHandling(), messageArray[3], messageArray[1]);
    }

    /**
     * Får kommando fra klient at nytt rom har blitt lagd
     * Lager nytt rom og sender samme kommando til alle andre klienter
     * @param message meldingen fra klient
     * @param clientSocket klienten meldingen tilhører
     */
    private void newRoomServer(String message, ClientSocket clientSocket) {
        //0 - kommando
        //1 - romnavn
        //2 - brukernavn
        //3 - ipadresse
        String[] messageArray = message.split(":");
        Rom rom = new Rom(messageArray[1], messageArray[2]);
        serverScene.getRooms().add(rom);
        serverScene.getRomSystem().opprettRom(rom);
        serverNetworking.updateClientsWithNewRoom(messageArray[1],  messageArray[2], clientSocket);
        logNetwork.logToDatabase(messageArray[2], LogOperations.NY_ROM.getHandling(), messageArray[3], messageArray[1]);
    }

    /**
     * Får kommando fra klient at ny melding har blitt sendt
     * Lager ny melding og sender samme kommando til alle andre klienter
     * @param message melding
     * @param clientSocket klienten meldingen tilhører
     */
    private void newMessageServer(String message, ClientSocket clientSocket) {
        //0 - kommando
        //1 - romnavn
        //2 - brukernavn
        //3 - melding
        //4 - ipadresse
        String[] messageArray = message.split(":");
        for(Rom room : serverScene.getRooms()) {
            String romNavn = room.getRomNavn();
            if(romNavn.equals(messageArray[1])) {
                serverScene.getMessage().nyMelding(room, new InndataTabell(messageArray[2], messageArray[3]));
                if(serverScene.getBruker().getRom() != null) {
                    if(romNavn.equals(serverScene.getBruker().getRom().getRomNavn())) {
                        serverScene.getServerUi().getHovedLayout().getRomChat().oppdaterMeldingListe(serverScene.getMessage().getMeldinger(room));
                    }
                }
            }
        }
        serverNetworking.updateClientsWithNewMessage(messageArray[1],messageArray[2],messageArray[3],clientSocket);
        logNetwork.logToDatabase(messageArray[2], LogOperations.NY_MELDING_ROM.getHandling() + messageArray[3], messageArray[4], messageArray[1]);
    }
}
