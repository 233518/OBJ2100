package com.eksamen.networking;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.scenes.ClientScene;
import com.eksamen.systems.chatsystem.InndataTabell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class SyncClient {
    private BufferedWriter bufferedWriter;
    private ClientScene clientScene;
    private ClientNetworking clientNetworking;
    private Bruker bruker;

    public SyncClient(BufferedWriter bufferedWriter, ClientScene clientScene, ClientNetworking clientNetworking, Bruker bruker) {
        this.bufferedWriter = bufferedWriter;
        this.clientScene = clientScene;
        this.clientNetworking = clientNetworking;
        this.bruker = bruker;
    }

    /**
     * Synker klienten med info fra server
     * @param message
     */
    public void syncClient(String message)  {
        switch(message.split(":")[0]) {
            case "newRoom":
                newRoomServer(message);
                break;
            case "newMessage":
                newMessageServer(message);

        }
    }
    /**
     * Får kommando fra server at nytt rom har blitt lagd
     * @param message
     */
    public void newRoomServer(String message) {
        System.out.println(message);
        String[] messageArray = message.split(":");
        Rom rom = new Rom(messageArray[1],messageArray[2]);
        clientScene.getRomSystem().opprettRom(rom);
        clientScene.getRooms().add(new Rom(messageArray[1], messageArray[2]));
        for(Rom romtest : clientScene.getRooms()) {
            System.out.println(romtest);
        }
    }

    /**
     * Får kommando fra server at ny melding har blitt lagd
     * @param message
     */
    public void newMessageServer(String message) {
        System.out.println("Kommando fra server at ny melding har blitt lagd");
        System.out.println(message);
        String[] messageArray = message.split(":");
        for(Rom room : clientScene.getRooms()) {
            System.out.println("Romnavn: " + room.getRomNavn());
            System.out.println("Romnavn fra server: " + messageArray[1]);
            if(room.getRomNavn() == messageArray[1]) {
                System.out.println("Fant rom");
                clientScene.getMessage().nyMelding(room, new InndataTabell(messageArray[1], messageArray[2]));
            }
        }

        //clientScene.getRomSystem().opprettRom(rom);
        //lientScene.getRooms().add(new Rom(messageArray[0], messageArray[1]));
    }

    /**
     * Synker server med info fra klient
     * @param message
     */
    public void syncServer(String message, Rom rom, String ekstra) {
        switch(message) {
            case "newRoom":
                newRoomClient("newRoom:" + rom.getRomNavn() + ":" + rom.getBrukerNavn());
                break;
            case "newMessage":
                newMessageClient("newMessage:"+rom.getRomNavn() + ":" + rom.getBrukerNavn() + ":" + ekstra);
        }
    }

    /**
     * Sender kommando til server at klient lagde nytt rom
     * @param message
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
     * @param message
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
