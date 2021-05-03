package com.eksamen.components;

import com.eksamen.systems.MessageSystem;
import com.eksamen.systems.chatsystem.InndataTabell;

import java.util.ArrayList;
import java.util.Objects;

public class Rom {
    private String romNavn;
    private String brukerNavn;
    private ArrayList<InndataTabell> meldinger;
    private ArrayList<Bruker> brukere;

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

    public ArrayList<Bruker> getBrukere() {
        return brukere;
    }

    public void leggTilMld(InndataTabell melding){
        meldinger.add(melding);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rom rom = (Rom) o;
        return Objects.equals(romNavn, rom.romNavn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(romNavn);
    }
}

