package com.eksamen;

import com.eksamen.networking.Server;
import com.eksamen.scenes.ClientScene;
import com.eksamen.uis.ClientUi;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainClient extends Application {
    public ClientScene scene;
    public static Server server;

    @Override
    public void start(Stage stage) {
        scene = new ClientScene(new ClientUi());

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
