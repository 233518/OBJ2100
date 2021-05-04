package com.eksamen.networking;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.scenes.ClientScene;
import com.eksamen.systems.chatsystem.DeltakerTabell;
import com.eksamen.systems.chatsystem.InndataTabell;

import java.io.*;
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
    private SyncClient syncClient;

    //Kobler opp klient til serveren og initialiserer streams/buffers
    public ClientNetworking(ClientScene clientScene, Bruker bruker) {
        try {
            socket = new Socket("localhost", 1234);

            input = new InputStreamReader(socket.getInputStream());
            output = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(input);
            bufferedWriter = new BufferedWriter(output);

            rooms = new ArrayList<>();

            syncClient = new SyncClient(bufferedWriter, clientScene, this, bruker);

            getInformation();
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
                System.out.println("Melding fra server: " + message);
                syncClient.syncClient(message);

            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            //avslutter kobling
            new CloseConnection().closeConnection(socket, input, output, bufferedReader, bufferedWriter);
        }
    }

    public void getInformation() {
        try {
            Rom rom = null;
            looper: do {
                String message = bufferedReader.readLine();
                String[] messageArray = message.split(":");
                switch(messageArray[0]) {
                    case "romInfo":
                        rom = new Rom(messageArray[1], messageArray[2]);
                        rooms.add(rom);
                        break;
                    case "meldingInfo":
                        rom.leggTilMld(new InndataTabell(messageArray[1], messageArray[2], messageArray[3]));
                        break;
                    case "brukerInfo":
                        rom.leggTilDeltaker(new DeltakerTabell(messageArray[1]));
                        break;
                    case "Ferdig":
                        break looper;
                }
            } while(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newRoom(String message, Rom rom) {
        syncClient.syncServer(message, rom, "", "");
    }
    public void newMessage(String message, Rom rom, String senderUsername, String messageUser) {
        syncClient.syncServer(message, rom, senderUsername, messageUser);
    }
    public void newBruker(String message, Rom rom, String brukernavn) {
        syncClient.syncServer(message, rom, brukernavn, "");
    }
    public void removeBruker(String message, Rom rom, String brukernavn) {
        syncClient.syncServer(message, rom, brukernavn, "");
    }

    public ArrayList<Rom> getRooms() {
        return rooms;
    }

    public void removeRoom(String message, Rom rom) {
        syncClient.syncServer(message, rom, "", "");
    }
}
