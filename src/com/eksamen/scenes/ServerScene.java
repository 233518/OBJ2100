package com.eksamen.scenes;

import com.eksamen.uis.Server;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ServerScene extends Scene {
    private Server serverUi;
    public ServerScene() {
        super(new Server().getGrid());
    }
}
