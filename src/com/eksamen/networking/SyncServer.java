package com.eksamen.networking;

import com.eksamen.components.Rom;
import com.eksamen.scenes.ClientScene;
import com.eksamen.scenes.ServerScene;
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
        //ArrayList<Rom> rooms = serverScene.getRooms();
        //rooms.add(rom);
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
        for(Rom room : serverScene.getRooms()) {
            String romNavn = room.getRomNavn();
            if(romNavn.equals(messageArray[1])) {
                serverScene.getMessage().nyMelding(room, new InndataTabell(messageArray[2], messageArray[3]));
                serverScene.getServerUi().getHovedLayout().getRomChat().oppdaterMeldingListe(serverScene.getMessage().getMeldinger(room));
            }
        }
        serverNetworking.updateClientsWithNewMessage(messageArray[1],messageArray[2],messageArray[3],clientSocket);
    }
}
