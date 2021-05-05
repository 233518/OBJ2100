package com.eksamen.uis.layouts;

import com.eksamen.systems.chatsystem.DeltakerTabell;
import com.eksamen.systems.chatsystem.InndataTabell;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Klasse for å opprette chatterommet
 */
public class RomChat {
    private TextField meldingsBoks;
    private TableView inndata;
    private TableView deltakere;
    private Label deltakereLabel;
    private Label inndataLabel;
    private Button sendKnapp;
    private BorderPane borderpane;
    private HBox bottomBorderPane;
    private VBox centerBorderPane;
    private VBox rightBorderPane;
    private TableColumn deltakerKolonne1, inndataKolonne1, inndataKolonne2, inndataKolonne3;

    /**
     * Constructor for RomChat
     * Lager layouten til chatterommet
     */
    public RomChat(){
        lagRomChat();
    }

    /**
     * Metode for å lage chatterom
     */
    public void lagRomChat(){
        //Setter variabler
        borderpane = new BorderPane();
        bottomBorderPane = new HBox();
        centerBorderPane = new VBox();
        rightBorderPane = new VBox();
        inndata = new TableView();
        deltakere = new TableView();
        sendKnapp = new Button("Send");
        meldingsBoks = new TextField();
        deltakereLabel = new Label("Deltakere");
        inndataLabel = new Label("Meldinger");

        //Plasserer panes
        borderpane.setBottom(bottomBorderPane);
        borderpane.setCenter(centerBorderPane);
        borderpane.setRight(rightBorderPane);

        //Setter størrelser
        borderpane.setMinSize(700, 500);
        bottomBorderPane.setMinSize(400,50);
        deltakere.setMinSize(100,450);
        inndata.setMinSize(350,450);
        meldingsBoks.setMinSize(500, 35);
        sendKnapp.setPrefSize(50,35);

        //Posisjonerer
        centerBorderPane.setAlignment(Pos.TOP_CENTER);

        //Modifikasjon til inndata
        inndata.setEditable(false);
        deltakere.setEditable(false);

        //Spacing mellom komponenter
        bottomBorderPane.setSpacing(20);
        bottomBorderPane.setMargin(meldingsBoks, new Insets(0, 0, 0, 20));
        centerBorderPane.setMargin(inndata, new Insets(0, 20,20,0));

        //Adder kolonner i tabeller
        deltakerKolonne1 = new TableColumn<>("Brukernavn");
        deltakerKolonne1.setCellValueFactory(new PropertyValueFactory<DeltakerTabell, String>("brukernavn"));
        inndataKolonne1 = new TableColumn<>("Tid");
        inndataKolonne1.setCellValueFactory(new PropertyValueFactory<InndataTabell, String>("tidspunkt"));
        inndataKolonne2 = new TableColumn<>("Brukernavn");
        inndataKolonne2.setCellValueFactory(new PropertyValueFactory<InndataTabell, String>("brukernavn"));
        inndataKolonne3 = new TableColumn<>("Melding");
        inndataKolonne3.setCellValueFactory(new PropertyValueFactory<InndataTabell, String>("melding"));
        inndata.getColumns().addAll(inndataKolonne1, inndataKolonne2, inndataKolonne3);
        deltakere.getColumns().add(deltakerKolonne1);

        //Legger til komponenter i panes
        centerBorderPane.getChildren().addAll(inndataLabel, inndata);
        bottomBorderPane.getChildren().addAll(meldingsBoks, sendKnapp);
        rightBorderPane.getChildren().addAll(deltakereLabel, deltakere);
    }

    /**
     * Getter for å få tak i hoved pane
     * @return BorderPane
     */
    public BorderPane getBorderpane() {
        return borderpane;
    }

    /**
     * Getter for å få tak i sende knappen
     * @return Button
     */
    public Button getSendKnapp() {
        return sendKnapp;
    }

    /**
     * Getter for å få tak i der brukeren skriver meldingen han vil sende
     * @return TextField
     */
    public TextField getMeldingsBoks() {
        return meldingsBoks;
    }

    /**
     * Getter for å få tak i tabellen med deltakere
     * @return TableView
     */
    public TableView getDeltakere() {
        return deltakere;
    }

    /**
     * Oppdaterer tabellen med deltakere
     * @param liste
     */
    public void oppdaterDeltakerListe(ObservableList<DeltakerTabell> liste) {
        ObservableList<DeltakerTabell> deltakerListe = liste;
        deltakere.setItems(deltakerListe);
    }

    /**
     * Oppdaterer tabellen med meldinger
     * @param liste
     */
    public void oppdaterMeldingListe(ObservableList<InndataTabell> liste) {
        ObservableList<InndataTabell> meldingListe = liste;
        inndata.setItems(meldingListe);
    }

    /**
     * Getter for kolonne nummer 1 i deltaker tabellen
     * @return TableColumn
     */
    public TableColumn getDeltakerKolonne1() {
        return deltakerKolonne1;
    }
}
