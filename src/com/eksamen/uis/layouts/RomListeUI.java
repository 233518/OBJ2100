package com.eksamen.uis.layouts;

import com.eksamen.systems.romsystem.RomTableView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Klasse for å opprette UI til romlisten.
 */
public class RomListeUI {
    private final RomTableView romTableView = new RomTableView();
    private final VBox vBox = new VBox();
    private final HBox nyttRomHBox = new HBox();
    private final HBox hBoxButtons = new HBox();

    private TextField textField = new TextField();
    private Button buttonLeggTilRom;
    private Button buttonNyttRom;
    private Button buttonBliMed;
    private Button buttonForlat;

    /**
     * Metode for å opprette alle elementene og sette de sammen i vBox som holder på alle elementene.
     * @return Returnerer vBoxen som holder på hele UI'et til romlisten.
     */
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


    /**
     * Getter for TextField som brukes til å opprette rom.
     * @return Returnerer textField
     */
    public TextField getTextField() {
        return textField;
    }

    /**
     * Metode for å legge til HBox som inneholder UI med funksjonalitet til å opprette rom.
     */
    public void visOpprettRom() {
        vBox.getChildren().addAll(nyttRomHBox);
    }

    /**
     * Metode for å skjule HBox som inneholder UI med funksjonalitet til å opprette rom.
     */
    public void skjulOpprettRom() {
        vBox.getChildren().remove(nyttRomHBox);
    }

    /**
     * Metode som returnerer tableviewen.
     * @return Returnerer tableviewen.
     */
    public RomTableView getRomTableView() {
        return romTableView;
    }

    /**
     * Metode for å returnere knappen som man trykker på for å vise UI som har funksjonalitet til å opprette rom.
     * @return returnerer knappen som man trykker på for å vise UI som har funksjonalitet til å opprette rom.
     */
    public Button getButtonNyttRom() {
        return buttonNyttRom;
    }

    /**
     * Metode for å returnere knappen som legger til ett nytt rom.
     * @return Returnerer knappen som legger til ett nytt rom.
     */
    public Button getButtonLeggTilRom() {
        return buttonLeggTilRom;
    }

    /**
     * Metode for å returnere knappen som lar deg bli med i ett rom.
     * @return Returnerer knappen som lar deg bli med i ett rom.
     */
    public Button getButtonBliMed() {
        return buttonBliMed;
    }

    /**
     * Metode for å returnere knappen som lar deg forlate ett rom.
     * @return Returnerer knappen som lar forlate ett rom.
     */
    public Button getButtonForlat() {
        return buttonForlat;
    }
}
