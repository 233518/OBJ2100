package com.eksamen.uis.layouts;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Serverlog {
    private HBox hbox;
    private TableView logTable;
    private TableColumn logKolonne1, logKolonne2, logKolonne3, logKolonne4, logKolonne5;
    public Serverlog(){
        lagLogUI();
    }

    public void lagLogUI(){
        hbox = new HBox();
        logTable = new TableView();
        logKolonne1 = new TableColumn<>("Dato");
        logKolonne1.setCellValueFactory(new PropertyValueFactory<>("dato"));
        logKolonne2 = new TableColumn<>("IP");
        logKolonne2.setCellValueFactory(new PropertyValueFactory<>("ip"));
        logKolonne3 = new TableColumn<>("Bruker");
        logKolonne3.setCellValueFactory(new PropertyValueFactory<>("bruker"));
        logKolonne4 = new TableColumn<>("Rom");
        logKolonne4.setCellValueFactory(new PropertyValueFactory<>("rom"));
        logKolonne5 = new TableColumn<>("Melding");
        logKolonne5.setCellValueFactory(new PropertyValueFactory<>("melding"));
        logTable.getColumns().addAll(logKolonne1,logKolonne2,logKolonne3,logKolonne4,logKolonne5);
        hbox.getChildren().add(logTable);
    }

    public HBox getHbox() {
        return hbox;
    }
}
