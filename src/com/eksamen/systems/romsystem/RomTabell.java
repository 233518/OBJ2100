package com.eksamen.systems.romsystem;

import javafx.beans.property.SimpleStringProperty;

/**
 * Klasse for å opprette rad i TableView
 */
public class RomTabell {
    private SimpleStringProperty romNavn;
    private SimpleStringProperty opprettetNavn;

    /**
     * Constructor for å lage rad i TableView
     * @param romNavn Navnet på rommet.
     * @param opprettetNavn Navnet til brukeren som opprettet rommet.
     */
    public RomTabell(String romNavn, String opprettetNavn) {
        this.romNavn = new SimpleStringProperty(romNavn);
        this.opprettetNavn = new SimpleStringProperty((opprettetNavn));
    }

    /**
     * Metode for å returnere romnavnet.
     * @return Returnerer romnavnet.
     */
    public String getRomNavn() {
        return romNavn.get();
    }

}
