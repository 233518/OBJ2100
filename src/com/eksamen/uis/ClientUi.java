package com.eksamen.uis;

import com.eksamen.uis.layouts.HovedLayout;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * Klasse for å sette opp ClientUI
 */

public class ClientUi {
    private GridPane hovedPane;
    private HovedLayout hovedLayout;

    /**
     * Constructor for klienten sin UI
     */
    public ClientUi() {
        hovedLayout = new HovedLayout();
        hovedPane = new GridPane();
        hovedPane.getChildren().add(hovedLayout.getTabPane());
    }

    /**
     * Getter for å få gridpane
     * @return GridPane
     */
    public GridPane getHovedPane() {
        return hovedPane;
    }

    /**
     * Getter for å få tak i hovedlayout
     * @return HovedLayout
     */
    public HovedLayout getHovedLayout() {
        return hovedLayout;
    }

}
