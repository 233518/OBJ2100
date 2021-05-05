package com.eksamen.uis;

import com.eksamen.systems.loggsystem.LoggSystem;
import com.eksamen.uis.layouts.HovedLayout;
import javafx.scene.layout.GridPane;

public class ServerUi {
    private GridPane hovedPane;
    private HovedLayout hovedLayout;

    public ServerUi(LoggSystem loggSystem) {
        hovedPane = new GridPane();
        hovedLayout = new HovedLayout(loggSystem);
        hovedPane.getChildren().add(hovedLayout.getTabPane());
        hovedLayout.lagLoggingTab();
    }

    public GridPane getHovedPane() {
        return hovedPane;
    }

    public HovedLayout getHovedLayout() {
        return hovedLayout;
    }
}
