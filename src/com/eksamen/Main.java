package com.eksamen;

import com.eksamen.scenes.ServerScene;
import com.eksamen.systems.DatabaseSystem;
import com.eksamen.systems.loggsystem.LoggSystem;
import com.eksamen.uis.ServerUi;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private ServerScene scene;
    private ServerUi serverUi;
    private LoggSystem loggSystem;
    public static DatabaseSystem logger;

    @Override
    public void start(Stage stage) {
        loggSystem = new LoggSystem();
        logger = new DatabaseSystem(loggSystem);
        loggSystem.startup();
        serverUi = new ServerUi(loggSystem);
        scene = new ServerScene(stage, serverUi, loggSystem);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

