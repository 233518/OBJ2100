package com.eksamen.networking;

import com.eksamen.components.Rom;
import com.eksamen.scenes.ClientScene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class SyncClient {
    private BufferedWriter bufferedWriter;
    private ClientScene clientScene;

    public SyncClient(BufferedWriter bufferedWriter, ClientScene clientScene) {
        this.bufferedWriter = bufferedWriter;
        this.clientScene = clientScene;
    }

    /**
     * Synker klienten med info fra server
     * @param message
     * @param rom
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
     * @param rom
     */
    public void newRoomServer(String message) {
        String[] messageArray = message.split(":");
        Rom rom = new Rom(messageArray[0],messageArray[1]);
        clientScene.getRomSystem().opprettRom(rom);
        clientScene.getRooms().add(new Rom(messageArray[0], messageArray[1]));
    }

    /**
     * Får kommando fra server at ny melding har blitt lagd
     * @param message
     */
    public void newMessageServer(String message) {
        String[] messageArray = message.split(":");
        Rom rom = new Rom(messageArray[0],messageArray[1]);
        clientScene.getRomSystem().opprettRom(rom);
        clientScene.getRooms().add(new Rom(messageArray[0], messageArray[1]));
    }

    /**
     * Synker server med info fra klient
     * @param message
     * @param rom
     */
    public void syncServer(String message, Rom rom) {
        switch(message) {
            case "newRoom":
                newRoomClient("newRoom:" + rom.getRomNavn() + ":" + rom.getBrukerNavn());
                break;
            case "newMessage":
                newMessageClient("newMessage:"+rom.getRomNavn() + ":" + rom.getBrukerNavn());
        }
    }

    /**
     * Sender kommando til server at klient lagde nytt rom
     * @param message
     * @param rom
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
