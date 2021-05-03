package com.eksamen.uis.layouts;

import com.eksamen.systems.romsystem.RomTableView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RomListeUI {
    private VBox vBox = new VBox();
    private HBox nyttRomHBox = new HBox();
    private TextField textField = new TextField();
    private HBox hBoxButtons = new HBox();
    private Button buttonLeggTilRom;
    private Button buttonNyttRom;
    private Button buttonBliMed;
    private Button buttonForlat;
    private RomTableView romTableView = new RomTableView();

    public VBox getRomUI() {
        buttonLeggTilRom = new Button("Legg til");
        buttonBliMed = new Button("Bli med i rom");
        buttonForlat = new Button("Forlat rom");

        textField = new TextField();
        textField.setMinSize(100,30);
        buttonLeggTilRom.setMinSize(60, 30);
        buttonNyttRom = new Button("Nytt rom");
        nyttRomHBox.getChildren().addAll(textField, buttonLeggTilRom);

        hBoxButtons.getChildren().addAll(buttonNyttRom, buttonBliMed, buttonForlat);
        vBox.getChildren().addAll(hBoxButtons, romTableView.getRomTableView());
        return vBox;
    }

    public TextField getTextField() {
        return textField;
    }

    public void visOpprettRom() {
        vBox.getChildren().addAll(nyttRomHBox);
    }

    public void skjulOpprettRom() {
        vBox.getChildren().remove(nyttRomHBox);
    }

    public RomTableView getRomTableView() {
        return romTableView;
    }

    public Button getButtonNyttRom() {
        return buttonNyttRom;
    }

    public Button getButtonLeggTilRom() {
        return buttonLeggTilRom;
    }

    public Button getButtonBliMed() {
        return buttonBliMed;
    }

    public Button getButtonForlat() {
        return buttonForlat;
    }
}
