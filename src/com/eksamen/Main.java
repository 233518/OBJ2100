package com.eksamen;

import com.eksamen.scenes.ServerScene;
import com.eksamen.systems.DatabaseSystem;
import com.eksamen.systems.loggsystem.LoggSystem;
import com.eksamen.uis.ServerUi;
import com.eksamen.uis.layouts.TestLayout;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private ServerScene scene;
    private ServerUi serverUi;
    private LoggSystem loggSystem;
    public static DatabaseSystem logger = new DatabaseSystem();

    @Override
    public void start(Stage stage) {
        loggSystem = new LoggSystem();
        logger = new DatabaseSystem(loggSystem);
        serverUi = new ServerUi(loggSystem);
        scene = new ServerScene(stage, serverUi, loggSystem);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

