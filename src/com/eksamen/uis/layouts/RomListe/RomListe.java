package com.eksamen.uis.layouts.RomListe;

import com.eksamen.components.Rom;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class RomListe {
    private ArrayList<Rom> romArrayList = new ArrayList<Rom>();
    private TableView<RomTabell> romTableView = new TableView<>();



    private void tableView() {
        try {
            TableColumn<RomTabell, Integer> idKolonne = new TableColumn<>("ID");
            idKolonne.setMinWidth(50);
            idKolonne.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<RomTabell, String> romKolonne = new TableColumn<>("Rom");
            idKolonne.setMinWidth(50);
            idKolonne.setCellValueFactory(new PropertyValueFactory<>("romNavn"));

            TableColumn<RomTabell, String> brukerKolonne = new TableColumn<>("Laget av");
            idKolonne.setMinWidth(50);
            idKolonne.setCellValueFactory(new PropertyValueFactory<>("opprettetNavn"));

            romTableView.getColumns().addAll(idKolonne, romKolonne, brukerKolonne);
        }catch (Exception e){
            System.out.println("Kunne ikke konfigurere tableview: " +e);
        }


    }
}
