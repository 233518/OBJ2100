package com.eksamen.uis.layouts;

import com.eksamen.systems.romsystem.RomTableView;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RomListeUI {
    private VBox vBox = new VBox();
    private HBox hBoxButtons = new HBox();
    private Button buttonNyttRom;
    private Button buttonBliMed;
    private Button buttonForlat;
    private RomTableView romTableView = new RomTableView();

    public VBox getRomUI() {
        buttonNyttRom = new Button("Nytt rom");
        buttonBliMed = new Button("Bli med i rom");
        buttonForlat = new Button("Forlat rom");

        //ActionEvents

        buttonNyttRom.setOnAction(e -> metode());

        hBoxButtons.getChildren().addAll(buttonNyttRom, buttonBliMed, buttonForlat);
        vBox.getChildren().addAll(hBoxButtons, romTableView.getRomTableView());
        return vBox;
    }

    public RomTableView getRomTableView() {
        return romTableView;
    }

    private void metode() {

    }
}
