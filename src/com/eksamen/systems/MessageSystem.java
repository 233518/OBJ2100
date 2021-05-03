package com.eksamen.systems;

import java.time.LocalDateTime;

public class MessageSystem {
    private String timestamp;
    private String brukernavn;
    private String melding;

    //MessageSystem msg = new MessageSystem("Brukernavn", "Melding");
    public MessageSystem(String brukernavn, String melding){
        this.brukernavn = brukernavn;
        this.melding = melding;
        this.timestamp = hentTidspunkt(LocalDateTime.now());
    }

    /**
     * Henter kun tidspunktet i datoen
     * @param dato
     * @return Tidspunkt i string
     */
    public String hentTidspunkt(LocalDateTime dato){
        String time = String.valueOf(dato.getHour());
        String minutt = String.valueOf(dato.getMinute());
        String sekund = String.valueOf(dato.getSecond());
        return time+":"+minutt+":"+sekund;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public String getMelding() {
        return melding;
    }
}
