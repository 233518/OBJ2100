package com.eksamen.components;

import java.util.ArrayList;

public class Rom {
    private String romNavn;
    private String brukerNavn;
    private ArrayList<String> meldinger;
    private ArrayList<Bruker> brukere;

    public Rom(String romNavn, String brukerNavn) {
        this.romNavn = romNavn; //Navn på rommet
        this.brukerNavn = brukerNavn; //Navnet på den som opprettet rommet
        this.meldinger = new ArrayList<>();
    }

    public String getRomNavn() {
        return romNavn;
    }

    public String getBrukerNavn() {
        return brukerNavn;
    }
}
