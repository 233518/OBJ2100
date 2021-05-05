package com.eksamen.components;

import com.eksamen.systems.MessageSystem;
import com.eksamen.systems.chatsystem.DeltakerTabell;
import com.eksamen.systems.chatsystem.InndataTabell;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Rom klassen inneholder all informasjon om et rom
 */
public class Rom {
    private String romNavn;
    private String brukerNavn;
    private ArrayList<InndataTabell> meldinger;
    private ArrayList<DeltakerTabell> brukere;

    /**
     * Konstruerer et nytt Rom
     * @param romNavn romnavnet
     * @param brukerNavn navnet på brukeren som opprettet rommet
     */
    public Rom(String romNavn, String brukerNavn) {
        this.romNavn = romNavn; //Navn på rommet
        this.brukerNavn = brukerNavn; //Navnet på den som opprettet rommet
        this.meldinger = new ArrayList<>();
        this.brukere = new ArrayList<>();
    }

    /**
     * Skaffer navnet på rommet
     * @return String
     */
    public String getRomNavn() {
        return romNavn;
    }

    /**
     * Skaffer brukernavnet
     * @return String
     */
    public String getBrukerNavn() {
        return brukerNavn;
    }

    /**
     * Skaffer meldinger
     * @return ArrayList av InndataTabell
     */
    public ArrayList<InndataTabell> getMeldinger() {
        return meldinger;
    }

    /**
     * Skaffer brukere
     * @return ArrayList av DeltakerTabell
     */
    public ArrayList<DeltakerTabell> getBrukere() {
        return brukere;
    }

    /**
     * Legger til ny melding i meldingslisten
     * @param melding ny InndataTabell
     */
    public void leggTilMld(InndataTabell melding){
        meldinger.add(melding);
    }

    /**
     * Legger til deltaker i deltakerlisten
     * @param deltaker ny DeltakerTabell
     */
    public void leggTilDeltaker(DeltakerTabell deltaker){
        brukere.add(deltaker);
    }

    /**
     * Sletter deltaker fra deltakerliste
     * @param deltaker DeltakerTabell som skal slettes
     */
    public void slettDeltaker(DeltakerTabell deltaker) {
        brukere.remove(deltaker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(romNavn);
    }
}

