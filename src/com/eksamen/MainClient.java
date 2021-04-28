package com.eksamen;

import com.eksamen.networking.Client;
import com.eksamen.networking.Server;
import com.eksamen.scenes.ClientScene;
import com.eksamen.scenes.ServerScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainClient extends Application {
    public ClientScene scene;
    public static Server server;

    @Override
    public void start(Stage stage) {
        //Client test = new Client();

        scene = new ClientScene();

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
