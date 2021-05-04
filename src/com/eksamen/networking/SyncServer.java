package com.eksamen.networking;

import com.eksamen.components.Rom;
import com.eksamen.scenes.ClientScene;
import com.eksamen.scenes.ServerScene;

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
        }
    }
    public void newRoomServer(String message, ClientSocket clientSocket) {
        String[] messageArray = message.split(":");
        Rom rom = new Rom(messageArray[0], messageArray[1]);
        serverScene.getRooms().add(rom);
        serverScene.getRomSystem().opprettRom(rom);
        serverNetworking.updateClientsWithNewRoom(messageArray[0],  messageArray[1], clientSocket);
    }
}
