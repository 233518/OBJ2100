package com.eksamen.scenes;

import com.eksamen.networking.Client;
import com.eksamen.uis.ClientUi;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class ClientScene extends Scene {
    private ClientUi clientUi;
    private Client client;

    public ClientScene(ClientUi clientUi) {
        super(clientUi.getClientGui());
        this.clientUi = clientUi;

        client = new com.eksamen.networking.Client();
        client.start();

        clientUi.getSendBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                client.sendMessage();
            }
        });

    }
}
