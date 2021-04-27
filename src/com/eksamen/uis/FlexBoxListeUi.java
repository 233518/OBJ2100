package com.eksamen.uis;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class FlexBoxListeUi {
    private Pane pane;
    private Image image;

    public FlexBoxListeUi() {
        pane = new Pane();
        image = new Image("File:image/test.jpg");
        pane.getChildren().add(new ImageView(image));
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
