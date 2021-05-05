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

    /**
     * Skaffer ipadressen til logg
     * @return String
     */
    public String getIp() {
        return ip;
    }

    /**
     * Metode for å få tak i brukernavn
     * @return brukernavn
     */
    public String getBruker() {
        return bruker;
    }

    /**
     * Metode for å få tak i nomnavnet
     * @return romnavnet
     */
    public String getRom() {
        return rom;
    }

    /**
     * Metode for å få tak i meldingen
     * @return meldingen.
     */
    public String getMelding() {
        return melding;
    }
}
