package com.eksamen;

import com.eksamen.networking.ServerNetworking;
import com.eksamen.scenes.ClientScene;
import com.eksamen.scenes.ServerScene;
import com.eksamen.uis.ClientUi;
import com.eksamen.uis.ServerUi;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
    private ServerScene scene;
    private ServerUi serverUi;

    @Override
    public void start(Stage stage) throws SQLException {
        serverUi = new ServerUi();
        scene = new ServerScene(serverUi.getHovedPane());
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

