package com.eksamen;

import com.eksamen.scenes.ServerScene;
import com.eksamen.uis.ServerUi;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
    public static ServerScene scene;

    @Override
    public void start(Stage stage) throws SQLException {
        scene = new ServerScene(new ServerUi());
        stage.setScene(scene);

        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
