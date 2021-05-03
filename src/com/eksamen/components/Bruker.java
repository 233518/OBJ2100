package com.eksamen.components;

public class Bruker {
    private String name;
    private String ipAdress;
    private Rom rom;

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

    /**
     * Metode for å hente ipadresse til bruker
     * @return ipadressen til brukeren
     */
    public String getIpAdress() {
        return ipAdress;
    }

    /**
     * Metode for å sette ip adresse til bruker
     * @param ipAdress ipadresse til brukeren
     */
    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    /**
     * Metode for å skaffe rom brukeren er i
     * @return
     */
    public Rom getRom() {
        return rom;
    }

    /**
     * Metode for å sette rom brukere er i
     * @param rom rom bruker er i
     */
    public void setRom(Rom rom) {
        this.rom = rom;
    }
}
