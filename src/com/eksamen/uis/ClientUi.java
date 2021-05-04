package com.eksamen.uis;

import com.eksamen.uis.layouts.HovedLayout;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ClientUi {
    private GridPane hovedPane;
    private HovedLayout hovedLayout;
    private Button button;

    public ClientUi() {
        hovedLayout = new HovedLayout();
        hovedPane = new GridPane();
        button = new Button("Testing");
        hovedPane.getChildren().add(hovedLayout.getTabPane());
        hovedPane.getChildren().add(button);
    }

    public GridPane getHovedPane() {
        return hovedPane;
    }

    public HovedLayout getHovedLayout() {
        return hovedLayout;
    }

    public Button getButton() {
        return button;
    }
}
