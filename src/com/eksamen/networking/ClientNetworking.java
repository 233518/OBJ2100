package com.eksamen.networking;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;

import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Opretter en ny klasse
 *
 */
public class ClientNetworking extends Thread {
    private Socket socket;
    private InputStreamReader input;
    private OutputStreamWriter output;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ArrayList<Rom> rooms;

    //Kobler opp klient til serveren og initialiserer streams/buffers
    public ClientNetworking() {
        try {
            socket = new Socket("localhost", 1234);

            input = new InputStreamReader(socket.getInputStream());
            output = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(input);
            bufferedWriter = new BufferedWriter(output);
        } catch (IOException e) {
            System.out.println("Connection refused");
        }
    }

    //Kjøres av thread
    //Kjører i en loop som leser inn meldinger mottat fra server
    public void run() {
        try {
            if(socket == null)
                return;
            while(true) {
                String message = bufferedReader.readLine();
                System.out.println(message);
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            //avslutter kobling
            new CloseConnection().closeConnection(socket, input, output, bufferedReader, bufferedWriter);
        }
    }

    //Sender melding til server
    public void sendMessage() {
        try {
            if(socket != null) {
                bufferedWriter.write("Hi");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getRoomListe() {
        try {
            while (bufferedReader.readLine() != null) {
                String message = bufferedReader.readLine();
                String[] string = message.split(":");
                rooms.add(new Rom(string[0], new Bruker(string[1])));
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
