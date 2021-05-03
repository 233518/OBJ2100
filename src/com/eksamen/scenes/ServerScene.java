package com.eksamen.scenes;

import com.eksamen.Main;
import com.eksamen.networking.ServerNetworking;
import com.eksamen.uis.ServerUi;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerScene extends Scene {
    private ServerUi serverUi;
    private ServerNetworking nettverk;
    public ServerScene(Stage stage, ServerUi serverUi) {
        super(serverUi.getHovedPane());
        this.serverUi = serverUi;
        nettverk = new ServerNetworking();
        nettverk.start();
    }
}
