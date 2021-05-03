package com.eksamen.systems.chatsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class InndataTabell {
    private String brukernavn;
    private String tidspunkt;
    private String melding;

    public InndataTabell(String brukernavn, String melding){
        this.brukernavn = brukernavn;
        this.tidspunkt = hentTidspunkt(LocalDateTime.now());
        this.melding = melding;
    }


    public String getBrukernavn() {
        return brukernavn;
    }

    public String getTidspunkt() {
        return tidspunkt;
    }

    public String getMelding() {
        return melding;
    }

    public String hentTidspunkt(LocalDateTime dato){
        String time = String.valueOf(dato.getHour());
        String minutt = String.valueOf(dato.getMinute());
        String sekund = String.valueOf(dato.getSecond());
        return time+":"+minutt+":"+sekund;
    }
}
