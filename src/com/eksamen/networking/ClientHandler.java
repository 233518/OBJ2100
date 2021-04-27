package com.eksamen.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler extends Thread {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            while (true) {
                in = new ObjectInputStream(socket.getInputStream());
                System.out.println((String)in.readObject());
                //out = new ObjectOutputStream(socket.getOutputStream());
            }
        } catch (IOException e) {
            System.out.println("Client closed connection");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            System.out.println("Closing client socket...");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
