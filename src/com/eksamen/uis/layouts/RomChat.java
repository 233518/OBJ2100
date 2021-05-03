package com.eksamen.uis.layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RomChat {
    private TextField meldingsBoks;
    private TableView inndata;
    private Button sendKnapp;
    private BorderPane borderpane;
    private HBox bottomBorderPane;
    private VBox centerBorderPane;

    public RomChat(){
        lagRomChat();
    }

    public void lagRomChat(){
        //Setter variabler
        borderpane = new BorderPane();
        bottomBorderPane = new HBox();
        centerBorderPane = new VBox();
        inndata = new TableView();
        sendKnapp = new Button("Send");
        meldingsBoks = new TextField();

        //Plasserer panes
        borderpane.setBottom(bottomBorderPane);
        borderpane.setCenter(centerBorderPane);

        //Setter størrelser
        borderpane.setMinSize(400, 500);
        bottomBorderPane.setMinSize(400,50);
        inndata.setMinSize(350,450);
        inndata.setMaxSize(380,450);
        meldingsBoks.setMinSize(300, 35);
        sendKnapp.setPrefSize(50,35);

        //Posisjonerer
        centerBorderPane.setAlignment(Pos.TOP_CENTER);

        //Modifikasjon til inndata
        inndata.setEditable(false);

        //Spacing mellom komponenter
        bottomBorderPane.setSpacing(20);
        bottomBorderPane.setMargin(meldingsBoks, new Insets(0, 0, 0, 20));
        centerBorderPane.setMargin(inndata, new Insets(0, 0,20,0));

        //Legger til komponenter i panes
        centerBorderPane.getChildren().add(inndata);
        bottomBorderPane.getChildren().addAll(meldingsBoks, sendKnapp);
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
