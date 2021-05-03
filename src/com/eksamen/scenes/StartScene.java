package com.eksamen.scenes;

import com.eksamen.networking.ClientNetworking;
import com.eksamen.uis.ClientUi;
import com.eksamen.uis.layouts.StartLayout;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class StartScene extends Scene {
    private StartLayout start;

    public StartScene(StartLayout start) {
        super(start.getStartPane());
        this.start = start;
    }

}
