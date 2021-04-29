package com.eksamen.scenes;

import com.eksamen.networking.Server;
import com.eksamen.uis.ServerUi;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class ServerScene extends Scene {
    private ServerUi serverui;
    private Server server;
    private Button test;
    public ServerScene(ServerUi serverui) {
        super(serverui.getGrid());
        this.serverui = serverui;

        server = new com.eksamen.networking.Server();
        server.start();

        serverui.getNesteKnapp().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                server.sendMessage();
            }
        });
    }
}
