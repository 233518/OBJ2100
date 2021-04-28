package com.eksamen.components.Player;

/**
 * Klasse til spillere.
 * @author Sigve
 */
public class Player {
    public Score score; //Poeng
    private boolean turGjetteOrd; //Er det denne spilleren sin tur til å gjette?
    private boolean turBeskriveOrd;
    private String gjettetOrd;
    private String name;

    /**
     * Default constructor for å opprette en spiller.
     * @param name Spillerens navn
     */
    public Player(String name) {
        this.turBeskriveOrd = false;
        this.score = new Score();
        this.turGjetteOrd = false;
        this.gjettetOrd = "";
        this.name = name;
    }
    /**
     * Metode for å gjette ord.
     * @param ord Ordet spilleren vil gjette på
     */
    public void gjettOrd(String ord) {
        try {
            if (!turGjetteOrd) {
                throw new Exception("Det er ikke din tur!");
            }else if(ord.equals("")) {
                throw new Exception("Du må gjette et ord!");
            }else {
                setGjettetOrd(ord);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metode for å beskrive et ord for andre å gjette.
     * @param ord Beskrivelsen for ordet andre skal gjette..
     */
    public void beskrivOrd(String ord){
        try{
            if (!turBeskriveOrd){
                throw new Exception("Det er ikke din tur å beskrive!");
            }else if(ord.equals("")) {
                throw new Exception("Du må gjette et ord!");
            }else {
                this.gjettetOrd = ord;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metode for å cleare gjettet ord
     */
    public void clearOrd() {
        this.gjettetOrd = "";
    }

    /**
     * Metode for å få vite om det er spilleren sin tur til å gjette ordet.
     * @return Returnerer true eller false om det er spilleren sin tur til å gjette ordet.
     */
    public boolean isTurGjetteOrd() {
        return turGjetteOrd;
    }

    /**
     * Metode for å velge om det spilleren sin tur til å gjette ord.
     * @param turGjetteOrd True betyr at det blir spillerens tur, false = det er ikke spilleren sin tur lenger.
     */
    public void setTurGjetteOrd(boolean turGjetteOrd) {
        this.turGjetteOrd = turGjetteOrd;
    }

    /**
     * Metode for å få tak i det gjettede ordet.
     * @return Returnerer det gjettede ordet.
     */
    public String getGjettetOrd() {
        return gjettetOrd;
    }

    private void setGjettetOrd(String ord) {
        this.gjettetOrd = ord;
    }

    /**
     * Metode for å få vite om det er spilleren sin tur til å beskrive ordet.
     * @return Returnerer true eller false om det er spilleren sin tur til å beskrive ordet for andre å gjette.
     */
    public boolean isTurBeskriveOrd() {
        return turBeskriveOrd;
    }

    /**
     * Metode for å velge om det spilleren sin tur til å beskrive ord.
     * @param turBeskriveOrd True betyr at det blir spillerens tur, false = det er ikke spilleren sin tur lenger.
     */
    public void setTurBeskriveOrd(boolean turBeskriveOrd) {
        this.turBeskriveOrd = turBeskriveOrd;
    }

    /**
     * Metode for å få navnet til spilleren
     * @return Returnerer navnet til spilleren
     */
    public String getName() {
        return name;
    }

    /**
     * Metode for å sette navnet til spilleren.
     * @param name Navnet til spilleren.
     */
    public void setName(String name) {
        this.name = name;
    }
}
