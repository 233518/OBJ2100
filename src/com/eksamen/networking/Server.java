package com.eksamen.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Server extends Thread {
    private int port;
    private ServerSocket server;
    private Socket socket;
    private boolean running;
    private ArrayList<ClientHandler> clients;

    public Server() {
        try {
            port = 8000;
            server = new ServerSocket(port);
            clients = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            running = true;
            while(running) {
                socket = server.accept();
                ClientHandler client = new ClientHandler(socket);
                clients.add(client);
                client.start();
                System.out.println("Client connected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        try {
            System.out.println("Closing server socket...");
            running = false;
            for (ClientHandler client: clients) {
                client.shutdown();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ClientHandler> getClients() {
        return clients;
    }
}
