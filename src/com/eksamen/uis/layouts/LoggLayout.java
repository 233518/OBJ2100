package com.eksamen.uis.layouts;

import com.eksamen.systems.loggsystem.LoggSystem;
import com.eksamen.systems.loggsystem.LoggTableView;
import javafx.scene.layout.HBox;

public class LoggLayout {
    private HBox hbox;
    //private final LoggTableView loggTableView = new LoggTableView();
    private LoggSystem loggSystem = new LoggSystem();

    public LoggLayout() {
        this.hbox = new HBox();
        hbox.getChildren().add(loggSystem.getLoggTableView().getLoggTableView());
    }

    public HBox getHbox() {
        return hbox;
    }
}
