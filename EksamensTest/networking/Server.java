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

    //Kjøres av thread
    public void run(){
        try {
            Test: while (true) {
                socket = serverSocket.accept(); //"Lytter" etter klient koblinger
                input = new InputStreamReader(socket.getInputStream());
                output = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(input);
                bufferedWriter = new BufferedWriter(output);

                Test2: while (true) {
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println(msgFromClient);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            new CloseConnection().closeConnection(socket, input, output, bufferedReader, bufferedWriter);
        }
    }
    public void sendMessage(String forklaring) {
        try {
            if(bufferedWriter != null) {
                bufferedWriter.write(forklaring);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
