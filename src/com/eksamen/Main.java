package com.eksamen;

import com.eksamen.scenes.ServerScene;
import com.eksamen.systems.DatabaseSystem;
import com.eksamen.uis.ServerUi;
import com.eksamen.uis.layouts.TestLayout;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private ServerScene scene;
    private ServerUi serverUi;
    public static DatabaseSystem logger = new DatabaseSystem();

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

