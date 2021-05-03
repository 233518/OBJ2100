package com.eksamen;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws SQLException {
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

