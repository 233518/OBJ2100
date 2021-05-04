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
                ClientSocket client = new ClientSocket(socket, scene, this);
                clients.add(client);
                client.start();
                sendRoomsToClient(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
    public void updateClientsWithNewRoom(String roomName, String brukernavn, ClientSocket clientSocket) {
        for(ClientSocket client : clients) {
            if(client.equals(clientSocket)) {
                continue;
            }
            client.newRoom(roomName, brukernavn);
        }
    }
    public void updateClientsWithNewMessage(String roomName, String brukernavn, String message, ClientSocket clientSocket) {
        for(ClientSocket client : clients) {
            if(client.equals(clientSocket)) {
                continue;
            }
            client.newMessage(roomName, brukernavn, message);
        }
    }
    public void sendNewMessage(String roomName, String brukernavn, String message) {
        for(ClientSocket client : clients) {
            client.newMessage(roomName, brukernavn, message);
        }
    }

    public void sendNewUser(String roomName, String brukernavn) {
        for(ClientSocket client : clients) {
            client.newBruker(roomName, brukernavn);
        }
    }

    public void updateClientsWithNewUserInRoom(String roomName, String brukernavn, ClientSocket clientSocket) {
        for(ClientSocket client : clients) {
            if(client.equals(clientSocket)) {
                continue;
            }
            client.newBruker(roomName, brukernavn);
        }
    }

    public void updateClientsWithRemoveUserInRoom(String roomName, String brukernavn, ClientSocket clientSocket) {
        for(ClientSocket client : clients) {
            if(client.equals(clientSocket)) {
                continue;
            }
            client.removeBruker(roomName, brukernavn);
        }
    }

    public void removeBruker(String roomName, String brukernavn, String message) {
        for(ClientSocket client : clients) {
            client.newMessage(roomName, brukernavn, message);
        }
    }
}
