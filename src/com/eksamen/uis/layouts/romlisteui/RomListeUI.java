package com.eksamen.uis.layouts.romlisteui;

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
        hBoxButtons.getChildren().addAll(buttonNyttRom, buttonBliMed, buttonForlat);
        vBox.getChildren().addAll(hBoxButtons, romTableView.getRomTableView());
        return vBox;
    }
}
