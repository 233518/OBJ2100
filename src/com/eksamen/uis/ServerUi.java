package com.eksamen.uis;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ServerUi {

    private Text question1;
    private TextField ord;
    private Text question2;
    private TextField beskrivelse;
    private Button nesteKnapp;
    private GridPane spill;

    public ServerUi() {
        makeServerGUI();
    }

    public void makeServerGUI() {
        // Komponenter i bruk
        question1 = new Text("Hva er ordet?");
        ord = new TextField();
        question2 = new Text("Forklaring for ordet?");
        beskrivelse = new TextField();
        nesteKnapp = new Button("Neste");

        // Oppsett
        spill = new GridPane();
        spill.setPadding(new Insets(10,10,10,10));
        spill.add(question1, 0, 0);
        spill.add(ord, 0, 1);
        spill.add(question2, 0, 2);
        spill.add(beskrivelse, 0, 3);
        spill.add(nesteKnapp, 0, 4);
    }

    public GridPane getGrid() {
        return spill;
    }

    public Button getNesteKnapp() {
        return nesteKnapp;
    }
}
