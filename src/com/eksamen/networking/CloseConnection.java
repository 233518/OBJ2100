package com.eksamen.networking;

import java.io.*;
import java.net.Socket;

/**
 * CloseConnection håndterer avslutting av kobling
 */
public class CloseConnection {

    /**
     * Funksjon som lukker kobling
     * @param socket kobling
     * @param input input leser
     * @param output output skriver
     * @param bufferedReader buffered leser
     * @param bufferedWriter buffered skriver
     */
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
