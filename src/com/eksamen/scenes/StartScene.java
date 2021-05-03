package com.eksamen.scenes;

import com.eksamen.MainClient;
import com.eksamen.networking.ClientNetworking;
import com.eksamen.uis.ClientUi;
import com.eksamen.uis.layouts.StartLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScene extends Scene {
    private StartLayout start;

    public StartScene(Stage stage, StartLayout start) {
        super(start.getStartPane(), 500, 500);
        this.start = start;
        start.getEnter().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hi?");
                stage.setScene(new ClientScene(stage, new ClientUi(), start.getSkrivBrukernavn().getText()));
            }
        });
    }

}
