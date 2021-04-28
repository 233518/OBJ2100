package com.eksamen.uis;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Client {
    private BorderPane clientGui;
    private HBox clientGuiBottom;
    private Button sendBtn;
    private TextField clientGuess;
    private TextField serverDescription;

    public Client() {
        makeClientGui();
    }

    public void makeClientGui(){
        clientGui = new BorderPane();
        clientGuiBottom = new HBox();
        clientGui.setPrefSize(500, 500);
        sendBtn = new Button("Gjett");
        serverDescription = new TextField();
        serverDescription.setEditable(false);
        clientGuess = new TextField("Gjett hva ordet er her");
        clientGui.setCenter(clientGuess);
        clientGui.setBottom(clientGuiBottom);
        clientGui.setTop(serverDescription);
        clientGuiBottom.getChildren().add(sendBtn);
        clientGuiBottom.setAlignment(Pos.CENTER);
    }

    public BorderPane getClientGui() {
        return clientGui;
    }
}
