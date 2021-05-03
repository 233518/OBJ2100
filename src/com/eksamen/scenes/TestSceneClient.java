package com.eksamen.scenes;

import com.eksamen.networking.ClientNetworking;
import com.eksamen.systems.MessageSystem;
import com.eksamen.uis.ClientUi;
import com.eksamen.uis.ServerUi;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class TestSceneClient extends Scene {
    private ClientNetworking nettverk;
    private ClientUi clientUi;
    private MessageSystem messageSystem;
    public TestSceneClient(ClientUi clientUi) {
        super(clientUi.getHovedPane());
        this.clientUi = clientUi;
        nettverk = new ClientNetworking();
        nettverk.start();

        messageSystem = new MessageSystem();



        clientUi.getTest().getButton1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nettverk.sendMessage();
            }
        });
    }
}
