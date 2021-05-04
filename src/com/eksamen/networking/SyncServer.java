package com.eksamen.networking;

import com.eksamen.components.Rom;
import com.eksamen.scenes.ClientScene;
import com.eksamen.scenes.ServerScene;
import com.eksamen.systems.chatsystem.DeltakerTabell;
import com.eksamen.systems.chatsystem.InndataTabell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SyncServer {
    private BufferedWriter bufferedWriter;
    private ServerScene serverScene;
    private ServerNetworking serverNetworking;

    public SyncServer(BufferedWriter bufferedWriter, ServerScene serverScene, ServerNetworking serverNetworking) {
        this.bufferedWriter = bufferedWriter;
        this.serverScene = serverScene;
        this.serverNetworking = serverNetworking;
    }

    /**
     * Synker server med info fra klient
     * @param message
     * @param rom
     */
    public void syncServer(String message, ClientSocket clientSocket)  {
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
        }
    }
    /**
     * Får kommando fra klient at bruker har forlatt rom
     * @param message
     * @param clientSocket
     */
    private void removeBrukerServer(String message, ClientSocket clientSocket) {
        String[] messageArray = message.split(":");
        System.out.println("Fjerner bruker fra server rom");
        for(Rom room : serverScene.getRooms()) {
            String romNavn = room.getRomNavn();
            if(romNavn.equals(messageArray[1])) {
                System.out.println("Fant rom");
                for(DeltakerTabell deltaker : room.getBrukere()) {
                    String deltakerNavn = deltaker.getBrukernavn();
                    if(deltakerNavn.equals(messageArray[2])) {
                        System.out.println("Fant deltaker");
                        room.slettDeltaker(deltaker);
                        break;
                    }
                }
                if(romNavn.equals(serverScene.getBruker().getRom().getRomNavn())) {
                    serverScene.getServerUi().getHovedLayout().getRomChat().oppdaterDeltakerListe(serverScene.getRomSystem().getDeltakere(room));
                }

            }
        }
        serverNetworking.updateClientsWithRemoveUserInRoom(messageArray[1], messageArray[2], clientSocket);
    }

    /**
     * Får kommando fra klient at ny bruker har logget på rom
     * @param message
     * @param clientSocket
     */
    private void newBrukerServer(String message, ClientSocket clientSocket) {
        String[] messageArray = message.split(":");
        for(Rom room : serverScene.getRooms()) {
            String romNavn = room.getRomNavn();
            if(romNavn.equals(messageArray[1])) {
                room.leggTilDeltaker(new DeltakerTabell(messageArray[2]));
                if(romNavn.equals(serverScene.getBruker().getRom().getRomNavn())) {
                    serverScene.getServerUi().getHovedLayout().getRomChat().oppdaterDeltakerListe(serverScene.getRomSystem().getDeltakere(room));
                }
                break;
            }
        }
        serverNetworking.updateClientsWithNewUserInRoom(messageArray[1], messageArray[2], clientSocket);
    }

    /**
     * Får kommando fra klient at nytt rom har blitt lagd
     * @param message
     * @param clientSocket
     */
    private void newRoomServer(String message, ClientSocket clientSocket) {
        String[] messageArray = message.split(":");
        Rom rom = new Rom(messageArray[1], messageArray[2]);
        serverScene.getRooms().add(rom);
        serverScene.getRomSystem().opprettRom(rom);
        serverNetworking.updateClientsWithNewRoom(messageArray[1],  messageArray[2], clientSocket);
    }

    /**
     * Får kommando fra klient at ny melding har blitt sendt
     * @param message
     * @param clientSocket
     */
    private void newMessageServer(String message, ClientSocket clientSocket) {
        String[] messageArray = message.split(":");
        for(Rom room : serverScene.getRooms()) {
            String romNavn = room.getRomNavn();
            if(romNavn.equals(messageArray[1])) {
                serverScene.getMessage().nyMelding(room, new InndataTabell(messageArray[2], messageArray[3]));
                if(romNavn.equals(serverScene.getBruker().getRom().getRomNavn())) {
                    serverScene.getServerUi().getHovedLayout().getRomChat().oppdaterMeldingListe(serverScene.getMessage().getMeldinger(room));
                }
            }
        }
        serverNetworking.updateClientsWithNewMessage(messageArray[1],messageArray[2],messageArray[3],clientSocket);
    }
}
