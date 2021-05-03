package com.eksamen.scenes;

import com.eksamen.networking.ClientNetworking;
import com.eksamen.networking.ServerNetworking;
import com.eksamen.uis.ClientUi;
import com.eksamen.uis.ServerUi;
import com.eksamen.uis.layouts.TestLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestSceneServer extends Scene {
    private ServerNetworking nettverk;
    private TestLayout testLayout;
    public TestSceneServer(Stage stage, TestLayout testLayout) {
        super(testLayout.getPane());
        this.testLayout = testLayout;
        nettverk = new ServerNetworking();
        nettverk.start();
    }
}
