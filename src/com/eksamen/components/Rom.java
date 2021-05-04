package com.eksamen.components;

import com.eksamen.systems.MessageSystem;
import com.eksamen.systems.chatsystem.DeltakerTabell;
import com.eksamen.systems.chatsystem.InndataTabell;

import java.util.ArrayList;
import java.util.Objects;

public class Rom {
    private String romNavn;
    private String brukerNavn;
    private ArrayList<InndataTabell> meldinger;
    private ArrayList<DeltakerTabell> brukere;

    public Rom(String romNavn, String brukerNavn) {
        this.romNavn = romNavn; //Navn på rommet
        this.brukerNavn = brukerNavn; //Navnet på den som opprettet rommet
        this.meldinger = new ArrayList<>();
        this.brukere = new ArrayList<>();
    }

    public String getRomNavn() {
        return romNavn;
    }

    public String getBrukerNavn() {
        return brukerNavn;
    }

    public ArrayList<InndataTabell> getMeldinger() {
        return meldinger;
    }

    public ArrayList<DeltakerTabell> getBrukere() {
        return brukere;
    }

    public void leggTilMld(InndataTabell melding){
        meldinger.add(melding);
    }

    public void leggTilDeltaker(DeltakerTabell deltaker){
        brukere.add(deltaker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(romNavn);
    }
}

