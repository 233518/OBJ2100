package com.eksamen.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    private int port;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    private boolean running;

    public Client() {
        try {
            port = 8000;
            socket = new Socket("localhost", port);
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        try {
            out.writeObject("Hi");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
