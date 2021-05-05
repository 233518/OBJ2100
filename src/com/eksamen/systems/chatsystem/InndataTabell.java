package com.eksamen.systems.chatsystem;

import java.time.LocalDateTime;

/**
 * Klasse for meldinger som blir sendt
 */
public class InndataTabell {
    private String brukernavn;
    private String tidspunkt;
    private String melding;

    /**
     * Constructor for inndatatabell
     * @param brukernavn String
     * @param melding String
     */
    public InndataTabell(String brukernavn, String melding){
        this.brukernavn = brukernavn;
        this.tidspunkt = hentTidspunkt(LocalDateTime.now());
        this.melding = melding;
    }

    /**
     * Constructor for inndatatabell
     * @param brukernavn String
     * @param melding String
     * @param tidspunkt String
     */
    public InndataTabell(String brukernavn, String melding, String tidspunkt) {
        this.brukernavn = brukernavn;
        this.tidspunkt = tidspunkt;
        this.melding = melding;
    }

    /**
     * Getter for å få tak i brukernavn
     * @return brukernavn
     */
    public String getBrukernavn() {
        return brukernavn;
    }

    /**
     * Getter for å få tak i tidspunkt
     * @return tidspunkt
     */
    public String getTidspunkt() {
        return tidspunkt;
    }

    /**
     * Getter for å få tak i melding
     * @return melding
     */
    public String getMelding() {
        return melding;
    }

    /**
     * Metode for å hente ut klokkeslettet i datoen
     * @param dato Dato du vil hente klokkeslett fra
     * @return Dato som String i format HH:MM:SS
     */
    public String hentTidspunkt(LocalDateTime dato){
        String time = String.valueOf(dato.getHour());
        String minutt = String.valueOf(dato.getMinute());
        String sekund = String.valueOf(dato.getSecond());
        return time+":"+minutt+":"+sekund;
    }
}
