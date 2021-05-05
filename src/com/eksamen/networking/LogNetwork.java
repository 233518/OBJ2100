package com.eksamen.networking;

import com.eksamen.Main;

public class LogNetwork {
    public void logToDatabase(String bruker, String melding, String ip, String rom) {
        Main.logger.logg(bruker, melding, ip, rom);
    }
}
