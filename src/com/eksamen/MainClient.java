package com.eksamen;
import com.eksamen.networking.ClientNetworking;
import com.eksamen.scenes.ClientScene;
import com.eksamen.scenes.StartScene;
import com.eksamen.scenes.TestSceneClient;
import com.eksamen.uis.ClientUi;
import com.eksamen.uis.layouts.StartLayout;
import com.eksamen.uis.layouts.TestLayout;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class MainClient extends Application {
    public static boolean debug = false;
    private StartScene scene;
    private StartLayout startLayout;
    private TestLayout testLayout;

    @Override
    public void start(Stage stage) {
        if(debug) {
            startLayout = new StartLayout();
            TestSceneClient sceneTest = new TestSceneClient(stage, testLayout);
            stage.setScene(sceneTest);
        } else {
            startLayout = new StartLayout();
            scene = new StartScene(stage, startLayout);
            stage.setScene(scene);
        }

        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
