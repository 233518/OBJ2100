package com.eksamen.systems;

import com.eksamen.components.Rom;
import com.eksamen.systems.chatsystem.InndataTabell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class MessageSystem {
    /**
     * Constructor for meldingssystemet
     */
    public MessageSystem(){

    }

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
}
