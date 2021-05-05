package com.eksamen.systems.loggsystem;

import com.eksamen.components.Logg;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Klasse for å opprette tableView og returnere den, samt oppdatere den.
 */
public class LoggTableView {
    private TableView<Logg> loggTableView;
    private TableColumn logKolonne1, logKolonne2, logKolonne3, logKolonne4, logKolonne5;
    /**
     * Constructor for å opprette TableView.
     */
    public LoggTableView(){
        lagLoggTableView();
    }
    /**
     * Metode for å opprette TableView for Logging samt å konstruere kolonnene til den.
     */
    public void lagLoggTableView(){
        loggTableView = new TableView();
        logKolonne1 = new TableColumn<Logg, String>("Dato");
        logKolonne1.setCellValueFactory(new PropertyValueFactory<>("dato"));
        logKolonne1.setMinWidth(150);
        logKolonne2 = new TableColumn<Logg, String>("IP");
        logKolonne2.setCellValueFactory(new PropertyValueFactory<>("ip"));
        logKolonne2.setMinWidth(125);
        logKolonne3 = new TableColumn<Logg, String>("Bruker");
        logKolonne3.setCellValueFactory(new PropertyValueFactory<>("bruker"));
        logKolonne3.setMinWidth(80);
        logKolonne4 = new TableColumn<Logg, String>("Rom");
        logKolonne4.setCellValueFactory(new PropertyValueFactory<>("rom"));
        logKolonne4.setMinWidth(80);
        logKolonne5 = new TableColumn<Logg, String>("Melding");
        logKolonne5.setCellValueFactory(new PropertyValueFactory<>("melding"));
        logKolonne5.setMinWidth(200);
        loggTableView.getColumns().addAll(logKolonne1,logKolonne2,logKolonne3,logKolonne4,logKolonne5);
    }
    /**
     * Metode for å returnere TableView'en som skal ta vare på de siste Loggene..
     * @return Returnerer TableViewen med logger.
     */
    public TableView<Logg> getLoggTableView() {
        return loggTableView;
    }
    /**
     * Metode for å oppdatere tableviewen med rader.
     * @param liste Listen med logger tableviewen skal inneholde.
     */
    public void oppdaterTableView(ObservableList<Logg> liste) {
        ObservableList<Logg> loggListe = liste;
        loggTableView.setItems(loggListe);
    }
}
