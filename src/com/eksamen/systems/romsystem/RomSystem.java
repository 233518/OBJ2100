package com.eksamen.systems.romsystem;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.systems.chatsystem.DeltakerTabell;
import com.eksamen.uis.layouts.RomListeUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Klasse for RomSystem
 */
public class RomSystem {
    private Bruker bruker;
    public ArrayList<Rom> romArrayList;
    private RomListeUI romListeUi;

    /**
     * Constructor for å opprette RomSystemet.
     * @param romListeUi Brukergrensesnittet (UI) til romlisten/velgeren.
     * @param bruker Brukren som opprettet rommet.
     */
    public RomSystem(RomListeUI romListeUi, Bruker bruker){
        this.romListeUi = romListeUi;
        this.bruker = bruker;
        romArrayList = new ArrayList<Rom>();

    }

    /**
     * Metode for å fylle inn Arraylisten med rom ved hjelp av opprettRom, så oppdatere Tableviewen.
     * @param roomsListe Arraylist<Rom> Med rom.
     */
    public void fyllInnTableview(ArrayList<Rom> roomsListe) {
        for(Rom room : roomsListe) {
            opprettRom(room);
        }
        romListeUi.getRomTableView().oppdaterTableView(getRom());
    }

    /**
     * Metode for å legge til ett nytt rom og legger det til i tableView.
     * @param rom Rommet som skal bli lagt til.
     */
    public void opprettRom(Rom rom) {
        romArrayList.add(rom);
        System.out.println("Denne kjører");
        romListeUi.getRomTableView().oppdaterTableView(getRom());
    }

    public void removeRom(Rom rom) {
        romArrayList.remove(rom);
        romListeUi.getRomTableView().oppdaterTableView(getRom());
    }

    /**
     * Metode for å gjøre om arraylist med rom til ObservarbleList med rom og returnere det.
     * @return Returnerer observablelist med rom fra romArrayList.
     */
     public ObservableList<Rom> getRom() {
         ObservableList<Rom> liste = FXCollections.observableArrayList();
         for (Rom rom : romArrayList) {
             liste.add(rom);
         }
        return liste;
    }

    public ObservableList<DeltakerTabell> getDeltakere(Rom rom) {
        ObservableList<DeltakerTabell> liste = FXCollections.observableArrayList();
        for(DeltakerTabell deltaker : rom.getBrukere()){
            liste.add(deltaker);
        }
        return liste;
    }

    public void slettRom(Rom rom, ArrayList array) {
         romArrayList.remove(rom);
         array.remove(rom);
         romListeUi.getRomTableView().oppdaterTableView(getRom());
    }
}
