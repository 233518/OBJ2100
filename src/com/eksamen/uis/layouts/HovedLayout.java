package com.eksamen.uis.layouts;

import com.eksamen.uis.layouts.RomListe.RomListe;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class HovedLayout {
    private RomChat romChat;
    private RomListe romListe;
    private TabPane tabPane;
    public HovedLayout(){
        romChat = new RomChat();
        romListe = new RomListe();
        lagLayout();
    }

    public void lagLayout(){
        tabPane = new TabPane();
        Tab tab1 = new Tab("Romliste");
        tab1.setClosable(false);
        tab1.setContent(romListe.getRomTableView());
        tabPane.getTabs().add(tab1);
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void lagNyTab(){
        Tab tab = new Tab();
        tabPane.getTabs().add(tab);
        tab.setText("Navn på chatterom");
        tab.setContent(romChat.getBorderpane());
    }
}
