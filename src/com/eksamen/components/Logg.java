package com.eksamen.components;

import java.util.ArrayList;

public class Logg {
    private String dato;
    private String ip;
    private String bruker;
    private String rom;
    private String melding;

    public Logg(String dato, String ip, String bruker, String rom, String melding) {
        this.dato = dato;
        this.ip = ip;
        this.bruker = bruker;
        this.rom = rom;
        this.melding = melding;
    }

    public String getDato() {
        return dato;
    }

    public String getIp() {
        return ip;
    }

    public String getBruker() {
        return bruker;
    }

    public String getRom() {
        return rom;
    }

    public String getMelding() {
        return melding;
    }
}
