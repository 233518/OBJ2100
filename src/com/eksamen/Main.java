package com.eksamen;

import com.eksamen.networking.ServerNetworking;
import com.eksamen.scenes.ClientScene;
import com.eksamen.scenes.ServerScene;
import com.eksamen.scenes.TestSceneServer;
import com.eksamen.uis.ClientUi;
import com.eksamen.uis.ServerUi;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
    private ServerScene scene;
    private ServerUi serverUi;

    @Override
    public void start(Stage stage) {
        serverUi = new ServerUi();
        scene = new ServerScene(stage, serverUi);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

