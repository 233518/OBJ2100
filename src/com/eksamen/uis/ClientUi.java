package com.eksamen.uis;

import com.eksamen.uis.layouts.HovedLayout;
import com.eksamen.uis.layouts.RomChat;
import com.eksamen.uis.layouts.StartLayout;
import com.eksamen.uis.layouts.TestLayout;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ClientUi {
    private GridPane hovedPane;
    private HovedLayout hovedLayout;

    public ClientUi() {
        hovedLayout = new HovedLayout();
        hovedPane = new GridPane();
        hovedPane.getChildren().add(hovedLayout.getTabPane());
    }

    public GridPane getHovedPane() {
        return hovedPane;
    }
    
}
