package com.eksamen;

public class Bruker {
    private String name;

    /**
     * Default constructor for å opprette en bruker.
     * @param name brukerens navn
     */
    public Bruker(String name) {
        this.name = name;
    }

    /**
     * Metode for å få kallenavnet til brukeren
     * @return Returnerer kallenavnet til brukeren
     */
    public String getName() {
        return name;
    }

    /**
     * Metode for å sette kallenavnet til brukeren.
     * @param name kallenavnet til brukeren.
     */
    public void setName(String name) {
        this.name = name;
    }
}
