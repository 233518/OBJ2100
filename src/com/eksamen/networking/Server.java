package com.eksamen.networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Server extends Thread {
    private int port = 1234;
    private Socket socket;
    private ServerSocket serverSocket;
    private InputStreamReader input;
    private OutputStreamWriter output;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Server() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            while(true) {
                socket = serverSocket.accept();
                input = new InputStreamReader(socket.getInputStream());
                output = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(input);
                bufferedWriter = new BufferedWriter(output);

                while (true) {
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println(msgFromClient);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage() {
        try {
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.write("Hi");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
