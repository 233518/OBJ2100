package com.eksamen.uis.layouts;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import javax.swing.text.html.ListView;
import java.util.concurrent.Flow;

public class TestLayout {
    private FlowPane pane;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private ListView list;

    public TestLayout() {
        pane = new FlowPane();
        button1 = new Button("Test1");
        button2 = new Button("Test2");
        button3 = new Button("Test3");
        button4 = new Button("Test4");
        button5 = new Button("Test5");
        pane.getChildren().addAll(button1, button2, button3, button4, button5);
    }

    public FlowPane getPane() {
        return pane;
    }

    public Button getButton1() {
        return button1;
    }

    public Button getButton2() {
        return button2;
    }

    public Button getButton3() {
        return button3;
    }

    public Button getButton4() {
        return button4;
    }

    public Button getButton5() {
        return button5;
    }
}
