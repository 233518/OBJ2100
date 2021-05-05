package com.eksamen.uis.layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Layout for oppstart av programmet, for da må en skrive inn et brukernavn.
 *
 */

public class StartLayout {
    private Label beskjed;
    private TextField skrivBrukernavn;
    private Button enter;
    private BorderPane startPane;
    private HBox topBox;
    private VBox centerBox;
    private VBox bottomBox;
    private ImageView imageView;

    public StartLayout() {
        try{
            //Setter opp panes og komponenter
            startPane = new BorderPane();
            topBox = new HBox();
            centerBox = new VBox();
            bottomBox = new VBox();
            skrivBrukernavn = new TextField();
            enter = new Button("Enter");
            beskjed = new Label("Skriv brukernavn: ");

            //Setter opp imageview med bilde
            imageView = new ImageView();
            Image image1 = new Image(new FileInputStream("res/yyyy.png"));
            imageView.setImage(image1);
            imageView.setFitHeight(200);
            imageView.setFitWidth(400);

            //Modifikasjoner til komponenter
            startPane.setPadding(new Insets(10,10,10,10));
            topBox.setAlignment(Pos.CENTER);
            centerBox.setAlignment(Pos.CENTER);
            bottomBox.setAlignment(Pos.TOP_CENTER);
            bottomBox.setMargin(enter, new Insets(0,0,100,0));
            startPane.setStyle("-fx-background-color: rgb(182,180,140)");
            enter.setPrefSize(100,30);
            beskjed.setFont(Font.font("Verdana", 16));
            skrivBrukernavn.setPrefSize(200, 30);

            // Legger komponenter i panes
            topBox.getChildren().addAll(beskjed, skrivBrukernavn);
            centerBox.getChildren().add(imageView);
            bottomBox.getChildren().add(enter);
            startPane.setTop(centerBox);
            startPane.setCenter(topBox);
            startPane.setBottom(bottomBox);
        } catch (FileNotFoundException e){
            System.out.println(e);
        }

    }

    /**
     * Getter for å få StartPane
     * @return BorderPane
     */
    public BorderPane getStartPane() {return startPane;}

    /**
     * Getter for å hente tekstfeltet med brukernavn
     * @return TextField
     */
    public TextField getSkrivBrukernavn() {
        return skrivBrukernavn;
    }

    /**
     * Getter for å hente knappen Enter
     * @return Button
     */
    public Button getEnter() {
        return enter;
    }

}
