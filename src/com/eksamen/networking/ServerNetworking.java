package com.eksamen.networking;

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

    public ServerNetworking() {
        try {
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
                ClientSocket client = new ClientSocket(socket);
                clients.add(client);
                client.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
