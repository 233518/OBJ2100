package com.eksamen.systems.romsystem;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.uis.layouts.RomListeUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class RomSystem {
    private Bruker bruker;
    public ArrayList<Rom> romArrayList;
    private RomListeUI romListeUi;

    public RomSystem(RomListeUI romListeUi, Bruker bruker){
        this.romListeUi = romListeUi;
        this.bruker = bruker;
        romArrayList = new ArrayList<Rom>();
    }

    public void fyllInnTableview(ArrayList<Rom> roomsListe) {
        for(Rom room : roomsListe) {
            opprettRom(room.getRomNavn(), room.getBrukerNavn());
        }
        romListeUi.getRomTableView().oppdaterTableView(getRom());
    }

    public void opprettRom(String romNavn, String brukernavn) {
        romArrayList.add(new Rom(romNavn, brukernavn));
        romListeUi.getRomTableView().oppdaterTableView(getRom());
    }

     public ObservableList<RomTabell> getRom() {
        System.out.println(romArrayList.size());
        ObservableList<RomTabell> liste = FXCollections.observableArrayList();
        for (int i = 0; i < romArrayList.size(); i++) {
            liste.add(new RomTabell(romArrayList.get(i).getRomNavn(), romArrayList.get(i).getBrukerNavn()));
        }
        return liste;
    }

    public void bliMedIRom(){
        //RomTabell rom = romliste.getRomTableView().getSelectionModel().getSelectedItem();
    }


    //Ferdig utfyller Til testing
    /* public void opprettRomOgBruker() {
        Bruker bruker0 = new Bruker("Sigve");
        Bruker bruker1 = new Bruker("Ørjan");
        Bruker bruker2 = new Bruker("Sivert");
        Bruker bruker3 = new Bruker("Govert");

        opprettRom("Rom 1", bruker0);
        opprettRom("Rom 2", bruker1);
        opprettRom("Rom 3", bruker2);
        opprettRom("Rom 4", bruker3);

        romListeUi.getRomTableView().setItems(getRom());
    }
    */
}
