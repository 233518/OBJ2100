package com.eksamen.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Feilmelding {
    /**
     * Metode for å vise feilmeldinger
     * @param melding Informasjon du vil vise i feilmeldingen.
     */
    public static void visFeilmelding(String melding) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Feilmelding");
        alert.setHeaderText("En feil har oppstått");
        alert.setContentText(melding);
        alert.showAndWait();
    }
}
