package com.eksamen.networking;

import com.eksamen.components.Rom;
import com.eksamen.scenes.ServerScene;
import com.eksamen.utils.LogOperations;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ServerNetworking håndterer nettverksdelen for serveren
 */
public class ServerNetworking extends Thread {
    private int port = 1234;
    private Socket socket;
    private ServerSocket serverSocket;
    private ArrayList<ClientSocket> clients;
    private ServerScene scene;
    private LogNetwork logNetwork;

    /**
     * Konstruerer en ny ServerNetworking
     * @param scene server scenen den tilhører
     */
    public ServerNetworking(ServerScene scene) {
        try {
            this.scene = scene;
            this.logNetwork = new LogNetwork();
            serverSocket = new ServerSocket(port);
            clients = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run kjøres av Thread klassen
     * Er ansvarlig for å lytte til nye koblinger til serveren
     * Opretter en ny tråd for hver kobling
     */
    public void run(){
        try {
            while (true) {
                socket = serverSocket.accept(); //"Lytter" etter klient koblinger
                System.out.println("Client connected");
                ClientSocket client = new ClientSocket(socket, scene, this, logNetwork);
                clients.add(client);
                client.start();
                sendInformationToclient(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sender melding til klientene at nytt rom har blitt opprettet
     * @param roomName navnet på rommet som har blitt opprettet
     * @param brukernavn brukernavnet til brukeren som opprettet rommet
     */
    public void sendNewRoom(String roomName, String brukernavn) {
        for(ClientSocket client : clients) {
            client.newRoom(roomName, brukernavn);
        }
        logNetwork.logToDatabase(brukernavn, LogOperations.NY_ROM.getHandling(), "IPHER", roomName);
    }

    /**
     * Sender start informasjon til klient
     * @param client klient
     */
    private void sendInformationToclient(ClientSocket client) {
        client.sendInformation(scene.getRooms());
    }

    /**
     * Sender melding om at nytt rom har blitt opprettet til alle klienter
     * Hopper over klient som opprettet rommet
     * @param roomName navnet på rommet som har blitt opprettet
     * @param brukernavn brukernavnet til brukeren som opprettet rommet
     * @param clientSocket klienten som opprettet rommet
     */
    public void updateClientsWithNewRoom(String roomName, String brukernavn, ClientSocket clientSocket) {
        for(ClientSocket client : clients) {
            if(client.equals(clientSocket)) {
                continue;
            }
            client.newRoom(roomName, brukernavn);
        }
    }

    /**
     * Sender melding om at ny melding har blitt sendt i rom
     * @param roomName navnet på rommet som meldingen tilhører
     * @param brukernavn navnet på brukeren som meldingen tilhører
     * @param message meldingen som brukeren sendte
     * @param clientSocket klienten som opprettet meldingen
     */
    public void updateClientsWithNewMessage(String roomName, String brukernavn, String message, ClientSocket clientSocket) {
        for(ClientSocket client : clients) {
            if(client.equals(clientSocket)) {
                continue;
            }
            client.newMessage(roomName, brukernavn, message);
        }
    }

    /**
     * Sender melding om at ny melding har blitt sendt i rom
     * @param roomName navnet på rommet som meldingen tilhører
     * @param brukernavn navnet på brukeren som meldingen tilhører
     * @param message meldingen som brukeren sendte
     */
    public void sendNewMessage(String roomName, String brukernavn, String message) {
        for(ClientSocket client : clients) {
            client.newMessage(roomName, brukernavn, message);
        }
        logNetwork.logToDatabase(brukernavn, LogOperations.NY_MELDING_ROM.getHandling(), "IPHER", roomName);
    }

    /**
     * Sender melding om at ny bruker har blitt med i rom
     * @param roomName navnet på rommet brukeren ble med i
     * @param brukernavn navnet på brukeren
     */
    public void sendNewUser(String roomName, String brukernavn) {
        for(ClientSocket client : clients) {
            client.newBruker(roomName, brukernavn);
        }
        logNetwork.logToDatabase(brukernavn, LogOperations.NY_BRUKER_ROM.getHandling(), "IPHER", roomName);
    }

    /**
     * Sender melding om at bruker ble med i rom til alle klienter
     * Hopper over klient som ble med i rommet
     * @param roomName navnet på rommet som brukeren ble med i
     * @param brukernavn navnet på brukeren
     * @param clientSocket klienten som ble med i rommet
     */
    public void updateClientsWithNewUserInRoom(String roomName, String brukernavn, ClientSocket clientSocket) {
        for(ClientSocket client : clients) {
            if(client.equals(clientSocket)) {
                continue;
            }
            client.newBruker(roomName, brukernavn);
        }
    }

    /**
     * Sender melding om at bruker forlot rom til alle klienter
     * Hopper over klient som opprettet rommet
     * @param roomName navnet på rommet som brukeren forlot
     * @param brukernavn navnet på brukeren
     * @param clientSocket klienten som forlot rommet
     */
    public void updateClientsWithRemoveUserInRoom(String roomName, String brukernavn, ClientSocket clientSocket) {
        for(ClientSocket client : clients) {
            if(client.equals(clientSocket)) {
                continue;
            }
            client.removeBruker(roomName, brukernavn);
        }
    }

    /**
     * Sender melding om at bruker forlot rom til alle klienter
     * @param roomName navnet på rommet som brukeren forlot
     * @param brukernavn navnet på brukeren
     */
    public void removeBruker(String roomName, String brukernavn) {
        for(ClientSocket client : clients) {
            client.removeBruker(roomName, brukernavn);
        }
        logNetwork.logToDatabase(brukernavn, LogOperations.FJERNA_ROM_BRUKER.getHandling(), "IPHER", roomName);
    }

    /**
     * Sender melding om at rom har blitt fjernet til alle klienter
     * Hopper over klient som opprettet rommet
     * @param roomName navnet på rommet som ble fjernet
     * @param clientSocket klienten som fjernet rommet
     */
    public void updateClientsWithRemoveRoom(String roomName, ClientSocket clientSocket) {
        for(ClientSocket client : clients) {
            if(client.equals(clientSocket)) {
                continue;
            }
            client.removeRoom(roomName);
        }
    }

    /**
     * Sender melding om at rom har blitt fjernet til alle klienter
     * @param roomName navnet på rommet som ble fjernet
     * @param rom rom informasjon
     */
    public void removeRoom(String roomName, Rom rom, String brukernavn) {
        for(ClientSocket client : clients) {
            client.removeRoom(roomName);
        }
        logNetwork.logToDatabase(brukernavn, LogOperations.FJERNA_ROM.getHandling(), "IPHER", roomName);
    }
}
