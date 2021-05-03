package com.eksamen.systems.romsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class RomTableView {
    private final TableView<RomTabell> romTableView = new TableView<>();

    private TableView tableView() {
        try {
            TableColumn<RomTabell, String> romKolonne = new TableColumn<>("Rom");
            TableColumn<RomTabell, String> brukerKolonne = new TableColumn<>("Laget av");

            romKolonne.setMinWidth(50);
            romKolonne.setCellValueFactory(new PropertyValueFactory<>("romNavn"));

            brukerKolonne.setMinWidth(50);
            brukerKolonne.setCellValueFactory(new PropertyValueFactory<>("opprettetNavn"));

            romTableView.getColumns().addAll(romKolonne, brukerKolonne);
        }catch (Exception e){
            System.out.println("Kunne ikke konfigurere tableview: " +e);
        }
        return romTableView;
    }

    /**
     * Metode for å oppdatere TableViewen med rom som blir lagt til ved å lese de rommene som alle eksisterer, så legge til nye rom.
     * Denne metoden har støtte for å få inn en liste med flere nye rom.
     * @param liste ObservableList med nye rom som skal bli lagt til og oppdatert i TableViewen.
     */
    public void oppdaterTableView(ObservableList<RomTabell> liste) {
        ObservableList<RomTabell> tableViewItems = romTableView.getItems();
        ObservableList<RomTabell> romArrayList = FXCollections.observableArrayList();

        romArrayList.addAll(tableViewItems);
        romArrayList.addAll(liste);
        romTableView.setItems(romArrayList);
    }

    /**
     * Metode for å returnere tablieView'en.
     * @return Returnerer tableView'en.
     */
    public TableView<RomTabell> getRomTableView() {
        return tableView();
    }
}
