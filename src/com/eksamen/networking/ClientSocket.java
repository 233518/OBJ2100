package com.eksamen.networking;

import com.eksamen.components.Rom;
import com.eksamen.scenes.ServerScene;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientSocket extends Thread {
    private Socket socket;
    private InputStreamReader input;
    private OutputStreamWriter output;

    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ClientSocket(Socket socket) {
        this.socket = socket;
        try {
            input = new InputStreamReader(socket.getInputStream());
            output = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(input);
            bufferedWriter = new BufferedWriter(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                while (true) {
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println(msgFromClient);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Sender liste med alle rom til klient
     * @param scene scene
     */
    public void sendRomListe(ArrayList<Rom> rooms) {
        try {
            if(bufferedWriter != null) {
                for(Rom room : rooms) {
                    System.out.println("Wtf");
                    bufferedWriter.write(room.getRomNavn() + ":" + room.getBrukerNavn());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                bufferedWriter.write("Ferdig");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch(IOException e) {
             e.printStackTrace();
        }
    }

    public void newRoom(String roomName, String brukerNavn) {
        try {
            bufferedWriter.write("newRoom" + ":" + roomName + ":" + brukerNavn);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}
