package com.eksamen.uis.layouts.romlisteui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RomTabell {
    private SimpleIntegerProperty id;
    private SimpleStringProperty romNavn;
    private SimpleStringProperty opprettetNavn;

    public RomTabell(int id, String romNavn, String opprettetNavn) {
        this.id = new SimpleIntegerProperty(id);
        this.romNavn = new SimpleStringProperty(romNavn);
        this.opprettetNavn = new SimpleStringProperty((opprettetNavn));
    }


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
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

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public void setRomNavn(SimpleStringProperty romNavn) {
        this.romNavn = romNavn;
    }

    public void setOpprettetNavn(SimpleStringProperty opprettetNavn) {
        this.opprettetNavn = opprettetNavn;
    }
}
