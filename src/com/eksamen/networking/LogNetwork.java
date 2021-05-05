package com.eksamen.networking;

import com.eksamen.Main;

/**
 * Klasse som logger til database
 * Blir brukt i nettverks klassene
 */
public class LogNetwork {
    /**
     * Logger til database
     * @param bruker brukernavnet
     * @param melding melding som skal logges
     * @param ip ipadressen der handlingen ble utført
     * @param rom rommet handlingen ble utført i
     */
    public void logToDatabase(String bruker, String melding, String ip, String rom) {
        Main.logger.logg(bruker, melding, ip, rom);
    }
}
