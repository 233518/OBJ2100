package com.eksamen.uis.layouts;

import com.eksamen.systems.loggsystem.LoggSystem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class HovedLayout {
    private RomChat romChat;
    private RomListeUI romListe;
    private TabPane tabPane;
    private Tab tab;
    private LoggLayout loggLayout;
    public HovedLayout(LoggSystem loggSystem){
        romChat = new RomChat();
        romListe = new RomListeUI();
        loggLayout = new LoggLayout(loggSystem);
        lagLayout();
    }
    public HovedLayout() {
        romChat = new RomChat();
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
        tab.setContent(loggLayout.getHbox());
        tabPane.getTabs().add(tab);
    }

    public void slettTab(){
        if(getTabPane().getTabs().size() == 2){
            getTabPane().getTabs().remove(1);
        }
    }

}
