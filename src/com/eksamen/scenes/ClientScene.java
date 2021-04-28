package com.eksamen.scenes;

import com.eksamen.uis.Client;
import com.eksamen.uis.Server;
import javafx.scene.Scene;

public class ClientScene extends Scene {
    private Client clientUi;
    public ClientScene() {
        super(new Client.getGrid());
    }
}
