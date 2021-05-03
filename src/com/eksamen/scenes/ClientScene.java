package com.eksamen.scenes;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.networking.ClientNetworking;
import com.eksamen.systems.InputSystem;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.uis.ClientUi;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ClientScene extends Scene {
    private ClientUi clientUi;
    private ClientNetworking nettverk;
    private Bruker bruker;
    private RomSystem romSystem;
    private ArrayList<Rom> rooms;
    private InputSystem inputSystem;


    public ClientScene(Stage stage, ClientUi clientUi, String username) {
        super(clientUi.getHovedPane());
        this.clientUi = clientUi;
        rooms = new ArrayList<>();
        nettverk = new ClientNetworking();
        nettverk.start();
        rooms = nettverk.getRooms();
        bruker = new Bruker(username);
        romSystem = new RomSystem(clientUi.getHovedLayout().getRomListe(), bruker);
        romSystem.fyllInnTableview(rooms);
        inputSystem = new InputSystem(clientUi.getHovedLayout().getRomListe(), bruker, clientUi.getHovedLayout());
    }
}
