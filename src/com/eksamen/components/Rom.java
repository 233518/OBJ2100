package com.eksamen.components;

public class Rom {
    private int id;
    private String romNavn;
    private String bruker;

    public Rom(int id, String romNavn, String opprettetNavn) {
        this.id = id;
        this.romNavn = romNavn; //Navn på rommet
        this.bruker = opprettetNavn; //Navnet på den som opprettet rommet
    }
}
