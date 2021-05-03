package com.eksamen;
import com.eksamen.scenes.StartScene;
import com.eksamen.uis.layouts.StartLayout;
import com.eksamen.uis.layouts.TestLayout;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainClient extends Application {
    public static boolean debug = false;
    private StartScene scene;
    private StartLayout startLayout;

    @Override
    public void start(Stage stage) {
        startLayout = new StartLayout();
        scene = new StartScene(stage, startLayout);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
