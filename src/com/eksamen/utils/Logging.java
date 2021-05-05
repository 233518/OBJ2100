package com.eksamen.utils;

/**
 * Implementerer de nødvendige funksjonen for logging
 */
public interface Logging {
    public void lagTabell();
    public void logg(String bruker, String melding, String ip, String rom);
}
