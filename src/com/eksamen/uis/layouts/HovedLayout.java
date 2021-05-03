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
        Tab tab2 = new Tab("Chat");
        tab1.setClosable(false);
        tab1.setContent(romListe.getRomTableView());
        tab2.setContent(romChat.getBorderpane());
        tabPane.getTabs().addAll(tab1, tab2);
    }

    public TabPane getTabPane() {
        return tabPane;
    }
}
