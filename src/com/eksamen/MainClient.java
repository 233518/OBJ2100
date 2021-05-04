package com.eksamen;
import com.eksamen.scenes.StartScene;
import com.eksamen.systems.DatabaseSystem;
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

        DatabaseSystem db = new DatabaseSystem();
        db.logg("Sigve","Det var litt rart", "192.168.10.1","Det kule rommet");
        db.print();

        startLayout = new StartLayout();
        scene = new StartScene(stage, startLayout);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
