package com.eksamen.scenes;

import com.eksamen.networking.ClientNetworking;
import com.eksamen.systems.MessageSystem;
import com.eksamen.uis.ClientUi;
import com.eksamen.uis.ServerUi;
import com.eksamen.uis.layouts.TestLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestSceneClient extends Scene {
    private ClientNetworking nettverk;
    private TestLayout testLayout;
    public TestSceneClient(Stage stage, TestLayout testLayout) {
        super(testLayout.getPane());
        this.testLayout = testLayout;
        nettverk = new ClientNetworking();
        nettverk.start();
    }
}
