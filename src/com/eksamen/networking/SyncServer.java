package com.eksamen.networking;

import com.eksamen.components.Rom;
import com.eksamen.scenes.ClientScene;
import com.eksamen.scenes.ServerScene;
import com.eksamen.systems.chatsystem.InndataTabell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

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
        }
    }

    /**
     * Får kommando fra klient at nytt rom har blitt lagd
     * @param message
     * @param clientSocket
     */
    public void newRoomServer(String message, ClientSocket clientSocket) {
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
    public void newMessageServer(String message, ClientSocket clientSocket) {
        String[] messageArray = message.split(":");
        for(Rom rom : serverScene.getRooms()) {
            if(rom.getRomNavn() == messageArray[1]) {
                System.out.println("Fant rom");
                rom.leggTilMld(new InndataTabell(messageArray[2], messageArray[3]));
            }
        }
        serverNetworking.updateClientsWithNewMessage(messageArray[1],messageArray[2],messageArray[3],clientSocket);
    }
}
