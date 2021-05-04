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
        tab1.setClosable(false);
        tab1.setContent(romListe.getRomUI());
        tabPane.getTabs().add(tab1);
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

    public void lagLoggingTab(){
        tab = new Tab("Serverlog");
        tab.setClosable(false);
        tab.setContent(serverlog.getHbox());
        tabPane.getTabs().add(tab);
    }

    public void slettTab(){
        if(getTabPane().getTabs().size() == 2){
            getTabPane().getTabs().remove(1);
        }
    }

}
