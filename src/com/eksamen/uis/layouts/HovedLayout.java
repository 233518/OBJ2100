package com.eksamen.uis.layouts;

import com.eksamen.systems.loggsystem.LoggSystem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Klasse for å kombinere de forskjellige layouts
 */
public class HovedLayout {
    private RomChat romChat;
    private RomListeUI romListe;
    private TabPane tabPane;
    private Tab tab;
    private LoggLayout loggLayout;

    /**
     * Constructor for hovedlayout som viser loggsystem, romliste og chatterom
     * @param loggSystem Loggesystemet som innebærer tabell og logs
     */
    public HovedLayout(LoggSystem loggSystem){
        romChat = new RomChat();
        romListe = new RomListeUI();
        loggLayout = new LoggLayout(loggSystem);
        lagLayout();
    }

    /**
     * Constructor for hovedlayout som viser romliste og chatterom
     */
    public HovedLayout() {
        romChat = new RomChat();
        romListe = new RomListeUI();
        lagLayout();
    }

    /**
     * Getter for å få romliste
     * @return RomListeUI
     */
    public RomListeUI getRomListe() {
        return romListe;
    }

    /**
     * Lager en ny tabpane og tab for innholdet av romUI
     */
    public void lagLayout(){
        tabPane = new TabPane();
        Tab tab1 = new Tab("Romliste");
        tab1.setClosable(false);
        tab1.setContent(romListe.getRomUI());
        tabPane.getTabs().add(tab1);
    }

    /**
     * Getter for RomChat
     * @return RomChat
     */
    public RomChat getRomChat() {
        return romChat;
    }

    /**
     * Getter for å få tak i tab
     * @return Tab
     */
    public Tab getTab() {
        return tab;
    }

    /**
     * Getter for å få tak i tabpane som inneholder alle tabs
     * @return TabPane
     */
    public TabPane getTabPane() {
        return tabPane;
    }

    /**
     * Metode for å lage en ny tab i tabpane
     * @param navn Navnet du vil kalle tabben
     */
    public void lagNyTab(String navn){
        tab = new Tab();
        tab.setText(navn);
        tab.setContent(romChat.getBorderpane());
        tabPane.getTabs().add(tab);
    }

    /**
     * Metode for å lage loggetab
     */
    public void lagLoggingTab(){
        tab = new Tab("Serverlog");
        tab.setClosable(false);
        tab.setContent(loggLayout.getHbox());
        tabPane.getTabs().add(tab);
    }

    /**
     * Metode for å slette tab.
     * Brukes for at brukeren skal ha maks 2 tabs
     */
    public void slettTab(){
        if(getTabPane().getTabs().size() == 2){
            getTabPane().getTabs().remove(1);
        }
    }

}
