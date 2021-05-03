package com.eksamen.components;

public class Rom {
    private int id;
    private String romNavn;
    private Bruker bruker;

    public Rom(int id, String romNavn, Bruker bruker) {
        this.id = id;
        this.romNavn = romNavn; //Navn på rommet
        this.bruker = bruker; //Navnet på den som opprettet rommet
    }

    public int getId() {
        return id;
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
