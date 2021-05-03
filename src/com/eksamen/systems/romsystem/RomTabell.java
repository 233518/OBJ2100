package com.eksamen.systems.romsystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RomTabell {
    private SimpleStringProperty romNavn;
    private SimpleStringProperty opprettetNavn;

    public RomTabell(String romNavn, String opprettetNavn) {
        this.romNavn = new SimpleStringProperty(romNavn);
        this.opprettetNavn = new SimpleStringProperty((opprettetNavn));
    }

    public String getRomNavn() {
        return romNavn.get();
    }

    public SimpleStringProperty romNavnProperty() {
        return romNavn;
    }

    public String getOpprettetNavn() {
        return opprettetNavn.get();
    }

    public SimpleStringProperty opprettetNavnProperty() {
        return opprettetNavn;
    }

    public void setRomNavn(SimpleStringProperty romNavn) {
        this.romNavn = romNavn;
    }

    public void setOpprettetNavn(SimpleStringProperty opprettetNavn) {
        this.opprettetNavn = opprettetNavn;
    }
}
