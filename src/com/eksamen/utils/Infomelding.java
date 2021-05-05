package com.eksamen.utils;

import javafx.scene.control.Alert;

/**
 * Infomelding håndterer sending av alert med info
 */
public class Infomelding {
    /**
     * Viser alert med info
     * @param melding melding som skal vises
     */
    public static void visInfoMelding(String melding) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informasjon");
        alert.setHeaderText("En ny hendelse har oppstått");
        alert.setContentText(melding);
        alert.showAndWait(); // dette metodekallet trigger visning av dialogvinduet
    }
}
