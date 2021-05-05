package com.eksamen.uis.layouts;

import com.eksamen.systems.loggsystem.LoggSystem;
import com.eksamen.systems.loggsystem.LoggTableView;
import javafx.scene.layout.HBox;

/**
 * Klasse for LoggLayout
 */
public class LoggLayout {
    private HBox hbox;

    /**
     * Constructor for å lage nytt UI/Layout til loggsystemet.
     * @param loggSystem
     */
    public LoggLayout(LoggSystem loggSystem) {
        this.hbox = new HBox();
        hbox.getChildren().add(loggSystem.getLoggTableView().getLoggTableView());
    }

    /**
     * Metode for å få tak i hboxen som inneholder LoggLayout/UI
     * @return Hbox med tableview til Logging.
     */
    public HBox getHbox() {
        return hbox;
    }
}
