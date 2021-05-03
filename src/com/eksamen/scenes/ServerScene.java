package com.eksamen.scenes;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.networking.ServerNetworking;
import com.eksamen.uis.ServerUi;
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
    public ServerScene(Stage stage, ServerUi serverUi) {
        super(serverUi.getHovedPane());
        this.serverUi = serverUi;
        nettverk = new ServerNetworking(this);
        nettverk.start();
        rooms = new ArrayList<>();
        serverUi.getHovedLayout().getRomListe().getButtonNyttRom().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nettverk.sendNewRoom("Halla123", "Admin");
            }
        });
    }

    public ArrayList<Rom> getRooms() {
        return rooms;
    }
}
