package com.eksamen.scenes;

import com.eksamen.MainClient;
import com.eksamen.networking.ClientNetworking;
import com.eksamen.uis.ClientUi;
import com.eksamen.uis.layouts.StartLayout;
import com.eksamen.utils.Feilmelding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * StartScene skaffer brukernavn til klienten
 */
public class StartScene extends Scene {
    private StartLayout start;

    /**
     * Konstruerer en ny StartScene
     * @param stage stage som scene skal tilhøre
     * @param start start er ui som skal vises
     */
    public StartScene(Stage stage, StartLayout start) {
        super(start.getStartPane(), 500, 500);
        this.start = start;
        start.getEnter().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (godkjent(start.getSkrivBrukernavn().getText())) {
                    stage.setScene(new ClientScene(stage, new ClientUi(), start.getSkrivBrukernavn().getText()));
                }
            }

            private boolean godkjent(String brukerNavn) {
                if (brukerNavn != "" && brukerNavn.matches("[A-Za-z0-9æøå]+")) {
                    return true;
                }else{
                    Feilmelding.visFeilmelding(
                            "Brukernavnet ditt er ikke riktig formatert!"
                                    +"\n" + "(A-Å, a-å, 0-9)");
                    return false;
                }
            }
        });
    }

}
