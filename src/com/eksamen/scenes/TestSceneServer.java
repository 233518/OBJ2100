package com.eksamen.scenes;

import com.eksamen.networking.ClientNetworking;
import com.eksamen.networking.ServerNetworking;
import com.eksamen.uis.ClientUi;
import com.eksamen.uis.ServerUi;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class TestSceneServer extends Scene {
    private ServerNetworking nettverk;
    private ServerUi serverUi;
    public TestSceneServer(ServerUi serverUi) {
        super(serverUi.getHovedPane());
        this.serverUi = serverUi;
        nettverk = new ServerNetworking();
        nettverk.start();
        serverUi.getTest().getButton1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nettverk.sendMessagesToClients();
            }
        });

    }
}
