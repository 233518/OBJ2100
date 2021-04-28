package com.eksamen.uis;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Server {

    Text question1;
    Text ord;
    Text question2;
    Text beskrivelse;
    Button nesteKnapp;
    GridPane spill;

    public Server() {}

    public void makeServerGUI() {
    // Komponenter i bruk
    Text question1 = new Text("Hva er ordet?");
    TextField ord = new TextField();
    Text question2 = new Text("Forklaring for ordet?");
    TextField beskrivelse = new TextField();
    Button nesteKnapp = new Button("Neste");

    // Oppsett
    GridPane spill = new GridPane();
    spill.setPadding(new Insets(10,10,10,10));
    spill.add(question1, 0, 0);
    spill.add(ord, 0, 1);
    spill.add(question2, 0, 2);
    spill.add(beskrivelse, 0, 3);
    spill.add(nesteKnapp, 0, 4);
    }

}
