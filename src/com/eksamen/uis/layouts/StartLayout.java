package com.eksamen.uis.layouts;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class StartLayout {
    private GridPane startPane;
    private Label beskjed;
    private TextField skrivBrukernavn;
    private String brukernavn;
    private Button enter;

    public StartLayout() {
        startPane = new GridPane();
        beskjed = new Label("Skriv brukernavn: ");
        skrivBrukernavn = new TextField();
        enter = new Button("Enter");

        // Plassering
        startPane.add(beskjed, 0,0);
        startPane.add(skrivBrukernavn,1,0);
        startPane.add(enter, 2,0);
    }
    public GridPane getStartPane() {return startPane;}
}
