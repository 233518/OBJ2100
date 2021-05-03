package com.eksamen.uis.layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    public RomChat(){
        lagRomChat();
    }

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
        borderpane.setMinSize(400, 500);
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

        //Legger til komponenter i panes
        centerBorderPane.getChildren().addAll(inndataLabel, inndata);
        bottomBorderPane.getChildren().addAll(meldingsBoks, sendKnapp);
        rightBorderPane.getChildren().addAll(deltakereLabel, deltakere);
    }

    public BorderPane getBorderpane() {
        return borderpane;
    }

    public TableView getInndata() {
        return inndata;
    }

    public void setInndata(TableView inndata) {
        this.inndata = inndata;
    }

    public Button getSendKnapp() {
        return sendKnapp;
    }

    public TextField getMeldingsBoks() {
        return meldingsBoks;
    }
}
