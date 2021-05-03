package com.eksamen.scenes;

import com.eksamen.networking.ClientNetworking;
import com.eksamen.uis.ClientUi;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ClientScene extends Scene {
    private ClientUi clientUi;
    private ClientNetworking nettverk;
    public ClientScene(ClientUi clientUi) {
        super(clientUi.getHovedPane());
        this.clientUi = clientUi;
        nettverk = new ClientNetworking();
        nettverk.start();

    }
}
