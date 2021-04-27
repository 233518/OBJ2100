package com.eksamen.uis;

import javafx.scene.layout.Pane;

public class Kolonne1Rad1Ui {
    private Pane pane;
    private FlexBoxListeUi box;

    public Kolonne1Rad1Ui() {
        box = new FlexBoxListeUi();
        pane.getChildren().add(box.getPane());
    }
}
