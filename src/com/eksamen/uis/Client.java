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
    private Scene scene;
    private Stage stage;

    public Client(Stage stage) {
        this.stage = stage;
        makeClientGui();
    }

    public void makeClientGui(){
        clientGui = new BorderPane();
        clientGuiBottom = new HBox();
        clientGui.setPrefSize(500, 500);
        sendBtn = new Button("Gjett");
        clientGuess = new TextField("Gjett hva ordet er her");
        clientGui.setCenter(clientGuess);
        clientGui.setBottom(clientGuiBottom);
        clientGuiBottom.getChildren().add(sendBtn);
        clientGuiBottom.setAlignment(Pos.CENTER);
        scene = new Scene(clientGui);
        stage.setScene(scene);
        stage.show();
    }

}
