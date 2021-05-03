package com.eksamen.systems.romsystem;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class RomTableView {
    //private ArrayList<Rom> romArrayList = new ArrayList<Rom>();
    private TableView<RomTabell> romTableView = new TableView<>();

    private TableView tableView() {
        try {
            TableColumn<RomTabell, String> romKolonne = new TableColumn<>("Rom");
            TableColumn<RomTabell, String> brukerKolonne = new TableColumn<>("Laget av");


            romKolonne.setMinWidth(50);
            romKolonne.setCellValueFactory(new PropertyValueFactory<RomTabell, String>("romNavn"));

            brukerKolonne.setMinWidth(50);
            brukerKolonne.setCellValueFactory(new PropertyValueFactory<RomTabell, String>("opprettetNavn"));

            //Midlertidig til testing
            //RomSystem.opprettRomOgBruker();

            romTableView.getColumns().addAll(romKolonne, brukerKolonne);
        }catch (Exception e){
            System.out.println("Kunne ikke konfigurere tableview: " +e);
        }
        return romTableView;
    }

    public void oppdaterTableView(ObservableList<RomTabell> liste) {
        ObservableList<RomTabell> tableViewItems = romTableView.getItems();
        ObservableList<RomTabell> romArrayList = FXCollections.observableArrayList();

        for (int i = 0; i < tableViewItems.size(); i++){
            romArrayList.add(tableViewItems.get(i));
        }
        for (int i = 0; i < liste.size(); i++){
            romArrayList.add(liste.get(i));
        }

        romTableView.setItems(romArrayList);
    }


    public TableView<RomTabell> getRomTableView() {
        return tableView();
    }
}
