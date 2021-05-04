package com.eksamen.uis.layouts;

import com.eksamen.components.Bruker;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class HovedLayout {
    private RomChat romChat;
    private RomListeUI romListe;
    private TabPane tabPane;
    private Tab tab;
    private Serverlog serverlog;
    public HovedLayout(){
        romChat = new RomChat();
        romListe = new RomListeUI();
        serverlog = new Serverlog();
        lagLayout();
    }

    public RomListeUI getRomListe() {
        return romListe;
    }

    public void lagLayout(){
        tabPane = new TabPane();
        Tab tab1 = new Tab("Romliste");
        Tab tab2 = new Tab("ServerLog");
        tab1.setClosable(false);
        tab1.setContent(romListe.getRomUI());
        tab2.setClosable(false);
        tab2.setContent(serverlog.getHbox());
        tabPane.getTabs().addAll(tab1, tab2);
    }

    public RomChat getRomChat() {
        return romChat;
    }

    public Tab getTab() {
        return tab;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void lagNyTab(String navn){
        tab = new Tab();
        tabPane.getTabs().add(tab);
        tab.setText(navn);
        tab.setContent(romChat.getBorderpane());
    }

    public void slettTab(){
        if(getTabPane().getTabs().size() == 3){
            getTabPane().getTabs().remove(2);
        }
    }

}
