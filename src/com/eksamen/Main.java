package com.eksamen;

import com.eksamen.networking.ServerNetworking;
import com.eksamen.scenes.ClientScene;
import com.eksamen.scenes.ServerScene;
import com.eksamen.scenes.TestSceneServer;
import com.eksamen.uis.ClientUi;
import com.eksamen.uis.ServerUi;
import com.eksamen.uis.layouts.TestLayout;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
    public static boolean debug = false;
    private ServerScene scene;
    private ServerUi serverUi;
    private TestLayout testLayout;

    @Override
    public void start(Stage stage) {
        if(debug) {
            serverUi = new ServerUi();
            testLayout = new TestLayout();
            TestSceneServer sceneTest = new TestSceneServer(stage, testLayout);
            stage.setScene(sceneTest);
        } else {
            serverUi = new ServerUi();
            scene = new ServerScene(stage, serverUi);
            stage.setScene(scene);
        }
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

