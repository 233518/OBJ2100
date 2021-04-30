package com.eksamen.networking;

import com.eksamen.uis.ClientUi;

import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class Client extends Thread {
    private Socket socket;
    private InputStreamReader input;
    private OutputStreamWriter output;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ClientUi client;

    /**
     *
     * @param client
     */
    public Client(ClientUi client) {
        try {
            this.client = client;
            socket = new Socket("localhost", 1234);

            input = new InputStreamReader(socket.getInputStream());
            output = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(input);
            bufferedWriter = new BufferedWriter(output);
        } catch (IOException e) {
            System.out.println("Connection refused");
            //e.printStackTrace();
        }
    }

    /**
     *
     */
    public void run() {
        try {
            if(socket == null)
                return;
            while(true) {
                String message = bufferedReader.readLine();
                client.getServerDescription().setText(message);
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            new CloseConnection().closeConnection(socket, input, output, bufferedReader, bufferedWriter);
        }
    }

    /**
     *
     */
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


}
