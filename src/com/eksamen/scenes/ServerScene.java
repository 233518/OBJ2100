package com.eksamen.scenes;

import com.eksamen.networking.ServerNetworking;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ServerScene extends Scene {
    public ServerScene(Parent parent) {
        super(parent);
        ServerNetworking server = new ServerNetworking();
        server.start();
    }
}
