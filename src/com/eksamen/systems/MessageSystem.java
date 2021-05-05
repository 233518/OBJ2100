package com.eksamen.systems;

import com.eksamen.components.Rom;
import com.eksamen.systems.chatsystem.InndataTabell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;

public class MessageSystem {
    /**
     *  Metode for å sende ny melding, den tar inn hvilket rom og innholdet i meldingen
     * @param rom Chatterommet
     * @param melding Melding fra bruker
     */
    public void nyMelding(Rom rom, InndataTabell melding){
        rom.leggTilMld(melding);
    }

    /**
     *  Metode for å hente alle meldinger i ett spesifikt rom
     * @param rom Rom
     * @return ObservableList med meldinger som er sendt i ett rom
     */
    public ObservableList<InndataTabell> getMeldinger(Rom rom) {
        ObservableList<InndataTabell> liste = FXCollections.observableArrayList();
        for(InndataTabell melding : rom.getMeldinger()){
            liste.add(melding);
        }
        return liste;
    }

    /**
     * Metode for å hente de siste 10 meldingene i ett spesifikt rom når bruker blir med
     * @param rom Rom det gjelder
     * @return ObservableList med 10 siste meldinger som er sendt i ett rom
     */
    public ObservableList<InndataTabell> getMeldingerBliMed(Rom rom) {
        ObservableList<InndataTabell> liste = FXCollections.observableArrayList();
        ArrayList<InndataTabell> meldingListe = rom.getMeldinger();
        ArrayList<InndataTabell> nyMeldingsListe = new ArrayList<>();
        if(meldingListe.size() <= 10) {
            for(InndataTabell melding : rom.getMeldinger()){
                liste.add(melding);
            }
            return liste;
        }

        for(int i = meldingListe.size() - 1; i > meldingListe.size() - 11; i--) {
            liste.add(meldingListe.get(i));
            nyMeldingsListe.add(meldingListe.get(i));
        }
        Collections.reverse(liste);
        Collections.reverse(nyMeldingsListe);
        rom.setMeldinger(nyMeldingsListe);
        return liste;
    }
}
