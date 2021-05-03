package com.eksamen.uis.layouts.romlisteui;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class RomTableView {
    private ArrayList<Rom> romArrayList = new ArrayList<Rom>();
    private TableView<RomTabell> romTableView = new TableView<>();

    private TableView tableView() {
        try {
            TableColumn<RomTabell, Integer> idKolonne = new TableColumn<>("ID");
            TableColumn<RomTabell, String> romKolonne = new TableColumn<>("Rom");
            TableColumn<RomTabell, String> brukerKolonne = new TableColumn<>("Laget av");

            idKolonne.setMinWidth(50);
            idKolonne.setCellValueFactory(new PropertyValueFactory<RomTabell, Integer>("id"));

            romKolonne.setMinWidth(50);
            romKolonne.setCellValueFactory(new PropertyValueFactory<RomTabell, String>("romNavn"));

            brukerKolonne.setMinWidth(50);
            brukerKolonne.setCellValueFactory(new PropertyValueFactory<RomTabell, String>("opprettetNavn"));

            //Midlertidig til testing
            opprettRomOgBruker();

            romTableView.setItems(getRom());

            romTableView.getColumns().addAll(idKolonne, romKolonne, brukerKolonne);
        }catch (Exception e){
            System.out.println("Kunne ikke konfigurere tableview: " +e);
        }
        return romTableView;
    }

    private ObservableList<RomTabell> getRom() {
        ObservableList<RomTabell> listen = FXCollections.observableArrayList();
        for (int i = 0; i < romArrayList.size(); i++) {
            listen.add(new RomTabell(romArrayList.get(i).getId(),romArrayList.get(i).getRomNavn(), romArrayList.get(i).getBruker().getName()));
        }
        return listen;
    }

    private void opprettRomOgBruker() {
        Bruker bruker0 = new Bruker("Sigve");
        Bruker bruker1 = new Bruker("Ørjan");
        Bruker bruker2 = new Bruker("Sivert");
        Bruker bruker3 = new Bruker("Govert");

        opprettRom(0, "Rom 1", bruker0);
        opprettRom(1, "Rom 2", bruker1);
        opprettRom(2, "Rom 3", bruker2);
        opprettRom(3, "Rom 4", bruker3);
    }

    private void opprettRom(int id, String romNavn, Bruker bruker) {
        romArrayList.add(new Rom(id, romNavn, bruker));
    }

    public TableView<RomTabell> getRomTableView() {
        return tableView();
    }
}
