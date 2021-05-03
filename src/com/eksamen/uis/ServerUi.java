package com.eksamen.uis;

import com.eksamen.uis.layouts.HovedLayout;
import com.eksamen.uis.layouts.TestLayout;
import javafx.scene.layout.GridPane;

public class ServerUi {
    private GridPane hovedPane;
    private HovedLayout hovedLayout;

    public ServerUi() {
        hovedPane = new GridPane();
        hovedLayout = new HovedLayout();
        hovedPane.getChildren().add(hovedLayout.getTabPane());
    }

    public GridPane getHovedPane() {
        return hovedPane;
    }

    public HovedLayout getHovedLayout() {
        return hovedLayout;
    }
}
