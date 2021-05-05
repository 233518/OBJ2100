package com.eksamen;
import com.eksamen.scenes.StartScene;
import com.eksamen.systems.DatabaseSystem;
import com.eksamen.uis.layouts.StartLayout;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * MainClient er klienten
 * Denne klassen er start punktet
 */
public class MainClient extends Application {
    private StartScene scene;
    private StartLayout startLayout;

    /**
     * Start funksjonen kjører i gang klienten
     * @param stage stage som skal brukes
     */
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
