package com.eksamen;

import com.eksamen.networking.Server;
import com.eksamen.scenes.ServerScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    public ServerScene scene;
    public static Server server;

    @Override
    public void start(Stage stage) throws SQLException {
        server = new Server();
        server.start();

        scene = new ServerScene();

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
