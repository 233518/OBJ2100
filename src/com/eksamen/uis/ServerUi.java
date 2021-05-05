package com.eksamen.uis;

import com.eksamen.systems.loggsystem.LoggSystem;
import com.eksamen.uis.layouts.HovedLayout;
import javafx.scene.layout.GridPane;

/**
 * Klasse for å sette opp ServerUI
 */
public class ServerUi {
    private GridPane hovedPane;
    private HovedLayout hovedLayout;

    /**
     * Constructoren for UI til server
     * @param loggSystem Loggesystemet
     */
    public ServerUi(LoggSystem loggSystem) {
        hovedPane = new GridPane();
        hovedLayout = new HovedLayout(loggSystem);
        hovedPane.getChildren().add(hovedLayout.getTabPane());
        hovedLayout.lagLoggingTab();
    }

    /**
     * Getter for hovedPane
     * @return GridPane
     */
    public GridPane getHovedPane() {
        return hovedPane;
    }

    /**
     * Getter for hovedlayout
     * @return HovedLayout
     */
    public HovedLayout getHovedLayout() {
        return hovedLayout;
    }
}
