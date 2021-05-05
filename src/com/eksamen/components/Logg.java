package com.eksamen.components;

import java.util.ArrayList;

/**
 * Klassen logg inneholder informasjon om en logg
 */
public class Logg {
    private String dato;
    private String ip;
    private String bruker;
    private String rom;
    private String melding;

    /**
     * Konstruerer en ny Logg
     * @param dato dato loggen ble laget
     * @param ip ipaddressen der handlingen ble utført
     * @param bruker brukeren det gjelder
     * @param rom rom handlingen ble utført i
     * @param melding hva handlingen var
     */
    public Logg(String dato, String ip, String bruker, String rom, String melding) {
        this.dato = dato;
        this.ip = ip;
        this.bruker = bruker;
        this.rom = rom;
        this.melding = melding;
    }

    /**
     * Skaffer dato til logg
     * @return String
     */
    public String getDato() {
        return dato;
    }
}
