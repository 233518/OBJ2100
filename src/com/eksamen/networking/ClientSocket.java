package com.eksamen.networking;

import java.io.*;
import java.net.Socket;

public class ClientSocket extends Thread {
    private Socket socket;
    private InputStreamReader input;
    private OutputStreamWriter output;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ClientSocket(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        while (true) {
            try {
                input = new InputStreamReader(socket.getInputStream());
                output = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(input);
                bufferedWriter = new BufferedWriter(output);
                while (true) {
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println(msgFromClient);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
