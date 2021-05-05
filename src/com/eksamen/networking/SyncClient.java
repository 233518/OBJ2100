package com.eksamen.networking;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.scenes.ClientScene;
import com.eksamen.systems.chatsystem.DeltakerTabell;
import com.eksamen.systems.chatsystem.InndataTabell;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * SyncClient håndterer hva som skal gjøres under input fra nettverk
 * Sender også informasjon til server
 */
public class SyncClient {
    private BufferedWriter bufferedWriter;
    private ClientScene clientScene;
    private ClientNetworking clientNetworking;
    private Bruker bruker;

    /**
     * Konstruerer en ny SyncClient
     * @param bufferedWriter buffered skriver som skal brukes
     * @param clientScene scenen som den tilhører
     * @param clientNetworking klienten sin nettverkskomponent
     * @param bruker brukeren til klienten
     */
    public SyncClient(BufferedWriter bufferedWriter, ClientScene clientScene, ClientNetworking clientNetworking, Bruker bruker) {
        this.bufferedWriter = bufferedWriter;
        this.clientScene = clientScene;
        this.clientNetworking = clientNetworking;
        this.bruker = bruker;
    }

    /**
     * Synker klient med info fra server
     * @param message melding fra server
     */
    public void syncClient(String message)  {
        switch(message.split(":")[0]) {
            case "newRoom":
                newRoomServer(message);
                break;
            case "newMessage":
                newMessageServer(message);
                break;
            case "newBruker":
                newBrukerServer(message);
                break;
            case "removeBruker":
                removeBrukerServer(message);
                break;
            case "removeRoom":
                removeRoomServer(message);
                break;
        }
    }

    /**
     * Får kommando fra server at rom har blitt slettet
     * Sletter rom
     * @param message melding fra server
     */
    private void removeRoomServer(String message) {
        String[] messageArray = message.split(":");
        Rom rom = null;
        for(Rom room : clientScene.getRooms()) {
            if(room.getRomNavn().equals(messageArray[1])) {
                rom = room;
            }
        }
        if(rom != null) {
            clientScene.getRomSystem().slettRom(rom, clientScene.getRooms());
        }
    }

    /**
     * Får kommando fra server at bruker har forlatt rom
     * Fjerner bruker fra rom
     * @param message melding fra server
     */
    private void removeBrukerServer(String message) {
        System.out.println("Fjern deltaker starter");
        String[] messageArray = message.split(":");
        Rom rom = null;
        DeltakerTabell deltakerFunnet = null;
        for(Rom room : clientScene.getRooms()) {
            String romNavn = room.getRomNavn();
            if(romNavn.equals(messageArray[1])) {
                System.out.println("Fant rom");
                rom = room;
                break;
            }
        }
        if(rom != null)  {
            for(DeltakerTabell deltaker : rom.getBrukere()) {
                String deltakerNavn = deltaker.getBrukernavn();
                if(deltakerNavn.equals(messageArray[2])) {
                    System.out.println("Fant deltaker");
                    deltakerFunnet = deltaker;
                    break;
                }
            }
        }
        if(deltakerFunnet != null) {
            System.out.println("Fjerner deltaker fra rom");
            rom.slettDeltaker(deltakerFunnet);
        }
        if(clientScene.getBruker().getRom() != null) {
            if(rom.getRomNavn().equals(clientScene.getBruker().getRom().getRomNavn())) {
                System.out.println("Oppdaterer liste klient siden");
                clientScene.getClientUi().getHovedLayout().getRomChat().oppdaterDeltakerListe(clientScene.getRomSystem().getDeltakere(rom));
            }
        }
    }

    /**
     * Får kommando fra server at nytt rom har blitt lagd
     * Legger til nytt rom
     * @param message melding fra server
     */
    public void newRoomServer(String message) {
        String[] messageArray = message.split(":");
        Rom rom = new Rom(messageArray[1],messageArray[2]);
        clientScene.getRooms().add(rom);
        clientScene.getRomSystem().opprettRom(rom);
    }

    /**
     * Får kommando fra server at ny melding har blitt lagd
     * Lager ny melding
     * @param message melding ta server
     */
    public void newMessageServer(String message) {
        String[] messageArray = message.split(":");
        for(Rom room : clientScene.getRooms()) {
            String romNavn = room.getRomNavn();
            if(romNavn.equals(messageArray[1])) {
                clientScene.getMessageSystem().nyMelding(room, new InndataTabell(messageArray[2], messageArray[3]));
                if(romNavn.equals(clientScene.getBruker().getRom().getRomNavn())) {
                    clientScene.getClientUi().getHovedLayout().getRomChat().oppdaterMeldingListe(clientScene.getMessageSystem().getMeldinger(room));
                }
            }
        }
    }

    /**
     * Får kommando fra server at ny bruker logget på rom
     * Legger til bruker i rom
     * @param message melding fra server
     */
    private void newBrukerServer(String message) {
        String[] messageArray = message.split(":");
        for(Rom room : clientScene.getRooms()) {
            String romNavn = room.getRomNavn();
            if(romNavn.equals(messageArray[1])) {
                room.leggTilDeltaker(new DeltakerTabell(messageArray[2]));
                if(clientScene.getBruker().getRom() != null) {
                    if(romNavn.equals(clientScene.getBruker().getRom().getRomNavn())) {
                        clientScene.getClientUi().getHovedLayout().getRomChat().oppdaterDeltakerListe(clientScene.getRomSystem().getDeltakere(room));
                    }
                }
            }
        }
    }
    /**
     * Synker server med info fra klient
     * @param message melding som skal sendes
     */
    public void syncServer(String message, Rom rom, String args1, String args2) {
        switch(message) {
            case "newRoom":
                newRoomClient("newRoom:" + rom.getRomNavn() + ":" + rom.getBrukerNavn());
                break;
            case "newMessage":
                newMessageClient("newMessage:" + rom.getRomNavn() + ":" + args1 + ":" + args2);
                break;
            case "newBruker":
                newBrukerClient("newBruker:" + rom.getRomNavn() + ":" + args1);
                break;
            case "removeBruker":
                removeBrukerClient("removeBruker:" + rom.getRomNavn() + ":" + args1);
                break;
            case "removeRoom":
                removeRoomClient("removeRoom:" + rom.getRomNavn());
                break;
        }
    }

    /**
     * Sender kommando til server at rom blir sletta for inaktiv
     * @param message melding som skal sendes
     */
    private void removeRoomClient(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sender kommando til server at klient gikk ut av rom
     * @param message melding som skal sendes
     */
    private void removeBrukerClient(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sender kommando til server at klient logget på rom
     * @param message melding som skal sendes
     */
    private void newBrukerClient(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sender kommando til server at klient lagde nytt rom
     * @param message melding som skal sendes
     */
    public void newRoomClient(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sender kommando til server at klient sendte ny melding
     * @param message melding som skal sendes
     */
    public void newMessageClient(String message) {
        try{
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
