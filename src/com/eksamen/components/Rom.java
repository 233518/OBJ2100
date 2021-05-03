package com.eksamen.components;

import java.util.ArrayList;

public class Rom {
    private String romNavn;
    private Bruker bruker;
    private ArrayList<String> meldinger;
    private ArrayList<Bruker> brukere;

    public Rom(String romNavn, Bruker bruker) {
        this.romNavn = romNavn; //Navn på rommet
        this.bruker = bruker; //Navnet på den som opprettet rommet
        this.meldinger = new ArrayList<>();
    }

    public String getRomNavn() {
        return romNavn;
    }

    public Bruker getBruker() {
        return bruker;
    }

    public String getBrukerNavn() {
        return bruker.getName();
    }
}
