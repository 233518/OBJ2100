package com.eksamen.networking;

import com.eksamen.components.Rom;
import com.eksamen.scenes.ServerScene;
import javafx.application.Platform;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ServerNetworking extends Thread {
    private int port = 1234;
    private Socket socket;
    private ServerSocket serverSocket;
    private ArrayList<ClientSocket> clients;
    private ServerScene scene;

    public ServerNetworking(ServerScene scene) {
        try {
            this.scene = scene;
            serverSocket = new ServerSocket(port);
            clients = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Kjøres av thread
    public void run(){
        try {
            while (true) {
                socket = serverSocket.accept(); //"Lytter" etter klient koblinger
                System.out.println("Client connected");
                ClientSocket client = new ClientSocket(socket, scene);
                clients.add(client);
                client.start();
                sendRoomsToClient(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessagesToClients() {
        for(ClientSocket client : clients) {
            //client.sendMessage("HelloClients");
        }
    }
    public void sendNewRoom(String roomName, String brukernavn) {
        for(ClientSocket client : clients) {
            client.newRoom(roomName, brukernavn);
        }
    }
    public void leggTilRom(ServerScene scene, Rom rom) {
        scene.getRooms().add(rom);
    }
    private void sendRoomsToClient(ClientSocket client) {
        client.sendRomListe(scene.getRooms());
    }
}
