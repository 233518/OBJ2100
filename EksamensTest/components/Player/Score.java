package com.eksamen.components.Player;

/**
 * Klasse som tar vare på poeng som spilleren har tjent.
 * @author Sigve
 */
public class Score {
    private int score;

    /**
     * Constructor for poeng
     */
    public Score(){
        this.score = 0;
    }

    /**
     * Metode for å legge til poeng til den totale poengsummen
     * @param points Antall poeng du får
     */
    public void scorePoints(int points) {
        this.score = this.score + points;
    }

    /**
     * Metode for å returnere den totale poengsummen
     * @return Terurnerer total poengsum.
     */
    public int getScore() {
        return score;
    }
}
