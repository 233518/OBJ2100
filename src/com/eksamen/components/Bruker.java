package com.eksamen.components;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Klassen Bruker inneholder informasjon om brukeren som bruker programmet
 */
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
        this.rom = null;
    }
    /**
     * Metode for å få kallenavnet til brukeren
     * @return kallenavnet til brukeren
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
        try{
            InetAddress ip = InetAddress.getLocalHost();
            ipAdress = String.valueOf(ip);
        } catch(UnknownHostException e){
            System.out.println("Kunne ikke skaffe ip");
        }
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
     * @return Rom
     */
    public Rom getRom() {
        return rom;
    }
    /**
     * Metode for å sette rom brukere er i
     * @param rom Rom bruker er i
     */
    public void setRom(Rom rom) {
        this.rom = rom;
    }
}
