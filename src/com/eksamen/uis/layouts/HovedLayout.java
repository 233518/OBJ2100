package com.eksamen.uis.layouts;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class HovedLayout {
    private RomChat romChat;
    private RomListeUI romListe;
    private TabPane tabPane;
    public HovedLayout(){
        romChat = new RomChat();
        //romListe = new RomListeUI();
        romListe = new RomListeUI();
        lagLayout();
    }

    public RomListeUI getRomListe() {
        return romListe;
    }

    public void lagLayout(){
        tabPane = new TabPane();
        Tab tab1 = new Tab("Romliste");
        tab1.setClosable(false);
        tab1.setContent(romListe.getRomUI());
        tabPane.getTabs().add(tab1);
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void lagNyTab(String navn){
        Tab tab = new Tab();
        tabPane.getTabs().add(tab);
        tab.setText(navn);
        tab.setContent(romChat.getBorderpane());
    }
}
