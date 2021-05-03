package com.eksamen.systems.romsystem;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        System.out.println(romTableView.getItems().size());
        //tableViewItems.add(tableViewItems.size()+1, liste.get(1));
        romTableView.setItems(liste);
        romTableView.getItems();
    }


    public TableView<RomTabell> getRomTableView() {
        return tableView();
    }
}
