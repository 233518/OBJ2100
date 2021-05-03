package com.eksamen.scenes;

import com.eksamen.networking.ClientNetworking;
import com.eksamen.uis.ClientUi;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ClientScene extends Scene {
    private ClientNetworking nettverk;
    public ClientScene(Parent parent) {
        super(parent);
        nettverk = new ClientNetworking();
        nettverk.start();
    }
}
