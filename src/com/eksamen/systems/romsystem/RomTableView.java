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
    private ArrayList<Rom> romArrayList = new ArrayList<Rom>();
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
            opprettRomOgBruker();

            romTableView.setItems(getRom());

            romTableView.getColumns().addAll(romKolonne, brukerKolonne);
        }catch (Exception e){
            System.out.println("Kunne ikke konfigurere tableview: " +e);
        }
        return romTableView;
    }

    private ObservableList<RomTabell> getRom() {
        ObservableList<RomTabell> listen = FXCollections.observableArrayList();
        for (int i = 0; i < romArrayList.size(); i++) {
            listen.add(new RomTabell(romArrayList.get(i).getRomNavn(), romArrayList.get(i).getBruker().getName()));
        }
        return listen;
    }

    private void opprettRomOgBruker() {
        Bruker bruker0 = new Bruker("Sigve");
        Bruker bruker1 = new Bruker("Ørjan");
        Bruker bruker2 = new Bruker("Sivert");
        Bruker bruker3 = new Bruker("Govert");

        opprettRom("Rom 1", bruker0);
        opprettRom("Rom 2", bruker1);
        opprettRom("Rom 3", bruker2);
        opprettRom("Rom 4", bruker3);
    }

    private void opprettRom( String romNavn, Bruker bruker) {
        romArrayList.add(new Rom(romNavn, bruker));
    }

    public TableView<RomTabell> getRomTableView() {
        return tableView();
    }
}
