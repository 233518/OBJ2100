package com.eksamen.uis;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientUi {
    private BorderPane clientGui;
    private HBox clientGuiBottom;
    private VBox clientGuiCenter;
    private Button sendBtn;
    private TextField clientGuess;
    private TextField serverDescription;
    private Label lbl;

    public ClientUi() {
        makeClientGui();
    }

    public void makeClientGui(){
        clientGui = new BorderPane();
        clientGuiBottom = new HBox();
        clientGuiCenter = new VBox();
        lbl = new Label("Gjett ordet her");
        clientGui.setPrefSize(500, 500);
        sendBtn = new Button("Gjett");
        clientGuess = new TextField();
        serverDescription = new TextField();
        serverDescription.setEditable(false);
        serverDescription.setPrefHeight(200);
        clientGui.setCenter(clientGuiCenter);
        clientGui.setBottom(clientGuiBottom);
        clientGui.setTop(serverDescription);
        clientGuiCenter.setMargin(lbl, new Insets(50,0,0,0));
        clientGuiCenter.setSpacing(20);
        clientGuiCenter.getChildren().addAll(lbl, clientGuess);
        clientGuiBottom.getChildren().add(sendBtn);
        clientGuiBottom.setAlignment(Pos.CENTER);
    }

    public BorderPane getClientGui() {
        return clientGui;
    }

    public Button getSendBtn() {
        return sendBtn;
    }

    public TextField getClientGuess() {
        return clientGuess;
    }
}
