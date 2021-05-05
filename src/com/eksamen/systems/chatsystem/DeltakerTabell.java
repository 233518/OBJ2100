package com.eksamen.systems.chatsystem;

public class DeltakerTabell {
    private String brukernavn;

    /**
     * Constructor for DeltakerTabell
     * @param brukernavn brukernavnet til brukeren
     */
    public DeltakerTabell(String brukernavn){
        this.brukernavn = brukernavn;
    }

    /**
     * Getter for å få tak i brukernavn
     * @return brukernavn
     */
    public String getBrukernavn() {
        return brukernavn;
    }

}
