package com.eksamen.systems.loggsystem;

import com.eksamen.Main;
import com.eksamen.components.Logg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Klasse for LoggSystem, som blant annet initialiserer TableView klassen.
 * Klassen har også metodene for å oppdatere tableViewen med logger fra Databasen.
 */
public class LoggSystem {
    private ArrayList<Logg> loggArrayList;
    private LoggTableView loggTableView;

    /**
     * Constructor for loggSystem
     */
    public LoggSystem() {
        this.loggArrayList = getArrayListFromDb();
        this.loggTableView = new LoggTableView();
        oppdaterTableView();
    }

    /**
     * Metode for å få tak i logger fra Databasen.
     * @return returnerer ArrayList<Logg> fra databasen.
     */
    private ArrayList<Logg> getArrayListFromDb() {
        return Main.logger.getLogs();
    }

    /**
     * Metode for å oppdatere TableViewen med logger.
     */
    public void oppdaterTableView() {
        loggTableView.oppdaterTableView(getLog());
    }

    public void refreshLoggSystem() {
        this.loggArrayList = getArrayListFromDb();
        oppdaterTableView();
    }

    /**
     * Metode for å hente logger fra loggArrayList og sette den inn i en ObservableArrayList for TableViewen.
     * @return returnerer ObservableList<Logg> som kan puttes inn i TableView.
     */
    private ObservableList<Logg> getLog() {
        ObservableList<Logg> liste = FXCollections.observableArrayList();
        for (Logg logg : loggArrayList) {
            liste.add(logg);
        }
        return liste;
    }

    /**
     * Metode for å få tak i TableViewen til Logg.
     * @return returnerer loggTableView objektet.
     */
    public LoggTableView getLoggTableView() {
        return loggTableView;
    }
}