package com.eksamen.scenes;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.networking.ServerNetworking;
import com.eksamen.systems.InputSystem;
import com.eksamen.systems.MessageSystem;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.uis.ServerUi;
import com.eksamen.uis.layouts.RomChat;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ServerScene extends Scene {
    private ServerUi serverUi;
    private ServerNetworking nettverk;
    private ArrayList<Rom> rooms;
    private Bruker bruker;
    private RomSystem romSystem;
    private InputSystem inputSystem;
    private MessageSystem message;
    private RomChat romChat;
    public ServerScene(Stage stage, ServerUi serverUi) {
        super(serverUi.getHovedPane());
        this.serverUi = serverUi;
        nettverk = new ServerNetworking(this);
        nettverk.start();
        rooms = new ArrayList<>();
        bruker = new Bruker("Admin");
        romSystem = new RomSystem(serverUi.getHovedLayout().getRomListe(), bruker);
        romSystem.fyllInnTableview(rooms);
        inputSystem = new InputSystem(serverUi.getHovedLayout().getRomListe(), bruker, serverUi.getHovedLayout(), message, serverUi.getHovedLayout().getRomChat());

        serverUi.getHovedLayout().getRomListe().getButtonNyttRom().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nettverk.sendNewRoom("Halla123", "Admin");
            }
        });
    }

    public RomSystem getRomSystem() {
        return romSystem;
    }

    public ArrayList<Rom> getRooms() {
        return rooms;
    }
}
