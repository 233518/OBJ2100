package com.eksamen.networking;

import java.io.*;
import java.net.Socket;

public class CloseConnection {
    private Socket socket;
    private InputStreamReader input;
    private OutputStreamWriter output;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public void closeConnection(Socket socket, InputStreamReader input, OutputStreamWriter output, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if(socket != null)
                socket.close();
            if (input != null)
                input.close();
            if(output != null)
                output.close();
            if(bufferedWriter != null)
                bufferedWriter.close();
            if(bufferedReader != null)
                bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
