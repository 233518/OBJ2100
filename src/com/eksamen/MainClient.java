package com.eksamen;
import com.eksamen.networking.ClientNetworking;
import com.eksamen.scenes.ClientScene;
import com.eksamen.uis.ClientUi;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class MainClient extends Application {
    private ClientScene scene;
    private ClientUi clientUi;

    @Override
    public void start(Stage stage) {
        clientUi = new ClientUi();
        scene = new ClientScene(clientUi.getHovedPane());
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
