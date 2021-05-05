package com.eksamen.systems.romsystem;

import com.eksamen.components.Rom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * RomTableView inneholder en tableview som viser rom liste
 */
public class RomTableView {
    private TableView<Rom> romTableView = new TableView<>();

    /**
     * Lager tableview med navn og kolonner
     */
    public RomTableView() {
        try {
            TableColumn<Rom, String> romKolonne = new TableColumn<Rom, String>("Rom");
            romKolonne.setMinWidth(50);
            romKolonne.setCellValueFactory(new PropertyValueFactory<>("romNavn"));

            TableColumn<Rom, String> brukerKolonne = new TableColumn<Rom, String>("Laget av");
            brukerKolonne.setMinWidth(50);
            brukerKolonne.setCellValueFactory(new PropertyValueFactory<>("brukerNavn"));

            romTableView.setStyle("-fx-background-color: #a1eaf7; -fx-selection-bar: #E9F7CA");
            romTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            romTableView.getColumns().addAll(romKolonne, brukerKolonne);
        }catch (Exception e){
            System.out.println("Kunne ikke konfigurere tableview: " +e);
        }
    }

    /**
     * Metode for å oppdatere TableViewen med rom som blir lagt til ved å lese de rommene som alle eksisterer, så legge til nye rom.
     * Denne metoden har støtte for å få inn en liste med flere nye rom.
     * @param liste ObservableList med nye rom som skal bli lagt til og oppdatert i TableViewen.
     */
    public void oppdaterTableView(ObservableList<Rom> liste) {
        ObservableList<Rom> romListe = liste;
        romTableView.setItems(romListe);
    }

    /**
     * Skaffer tabellen
     * @return TableView av Rom
     */
    public TableView<Rom> getRomTableView() {
        return romTableView;
    }
}
