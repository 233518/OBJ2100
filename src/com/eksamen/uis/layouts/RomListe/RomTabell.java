package com.eksamen.uis.layouts.RomListe;

public class RomTabell {
    private int id;
    private String romNavn;
    private String opprettetNavn;

    public RomTabell(int id, String romNavn, String opprettetNavn) {
        this.id = id;
        this.romNavn = romNavn;
        this.opprettetNavn = opprettetNavn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRomNavn(String romNavn) {
        this.romNavn = romNavn;
    }

    public void setOpprettetNavn(String opprettetNavn) {
        this.opprettetNavn = opprettetNavn;
    }
}
