package com.eksamen.utils;

public interface Logging {
    public void lagOppkoblingTabell();
    public void lagBliMedTabell();
    public void lagForlatTabell();
    public void lagMeldingTabell();
    public void lagOpprettetRomTabell();
    public void loggMelding(String bruker, String melding, String ip, String rom, String dato);
    public void loggLoggInn(String bruker, String ip, String rom, String dato);
    public void loggBliMed(String bruker, String ip, String rom, String dato);
    public void loggForlat(String bruker, String ip, String rom, String dato);
    public void loggLoggUt(String bruker, String ip, String rom, String dato);
    public void logNewRoom(String bruker, String ip, String rom, String dato);
}
