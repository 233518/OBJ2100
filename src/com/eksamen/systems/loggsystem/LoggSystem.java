package com.eksamen.systems.loggsystem;

import com.eksamen.Main;
import com.eksamen.components.Logg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class LoggSystem {
    private ArrayList<Logg> loggArrayList;
    private LoggTableView loggTableView;

    public LoggSystem() {
        this.loggArrayList = getArrayListFromDb();
        this.loggTableView = new LoggTableView();
        oppdaterTableView();
    }

    private ArrayList<Logg> getArrayListFromDb() {
        return Main.logger.getLogs();
    }

    public void oppdaterTableView() {
        loggTableView.oppdaterTableView(getLog());
    }

    public void refreshLoggSystem() {
        this.loggArrayList = getArrayListFromDb();
        oppdaterTableView();
    }

    private ObservableList<Logg> getLog() {
        ObservableList<Logg> liste = FXCollections.observableArrayList();
        for (Logg logg : loggArrayList) {
            liste.add(logg);
        }
        return liste;
    }

    public LoggTableView getLoggTableView() {
        return loggTableView;
    }
}