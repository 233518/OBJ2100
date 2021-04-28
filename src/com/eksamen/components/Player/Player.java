package com.eksamen.components.Player;

/**
 * Klasse til spillere.
 * @author Sigve
 */
public class Player {
    private Score score; //Poeng
    private boolean tur; //Er det denne spilleren sin tur?
    private String ord;

    /**
     * Default constructor for spiller.
     */
    public Player() {
        this.score = new Score();
        this.tur = false;
        this.ord = "";
    }

    /**
     * Metode for å gjette ord.
     * @param ord Ordet spilleren vil gjette på
     */
    public void gjettOrd(String ord) {
        try {
            if (!tur) {
                throw new Exception("Det er ikke din tur!");
            }else if(ord.equals("")) {
                throw new Exception("Du må gjette et ord!");
            }else {
                setOrd(ord);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metode for å cleare gjettet ord
     */
    public void clearOrd() {
        this.ord = "";
    }

    private void setOrd(String ord) {
        this.ord = ord;
    }

}
