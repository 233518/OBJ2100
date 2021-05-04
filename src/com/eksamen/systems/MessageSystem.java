package com.eksamen.systems;

import com.eksamen.components.Rom;
import com.eksamen.systems.chatsystem.InndataTabell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class MessageSystem {

    public ArrayList<InndataTabell> meldingArrayList;

    //MessageSystem msg = new MessageSystem("Brukernavn", "Melding");
    public MessageSystem(){

    }

    public void nyMelding(Rom rom, InndataTabell melding){
        rom.leggTilMld(melding);
    }

    public ObservableList<InndataTabell> getMeldinger(Rom rom) {
        ObservableList<InndataTabell> liste = FXCollections.observableArrayList();
        for(InndataTabell melding : rom.getMeldinger()){
            liste.add(melding);
        }
        return liste;
    }
}
