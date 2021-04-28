package com.eksamen;

import com.eksamen.networking.Server;
import com.eksamen.uis.MainUi;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    public Scene scene;
    public static Server server;

    @Override
    public void start(Stage stage) throws SQLException {

        server = new Server();
        server.start();

        Connection con = DriverManager.getConnection("jdbc:sqlite:OppgaveDB.db");
        scene = new Scene(new Pane(), 640, 480);

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
