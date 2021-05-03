package com.eksamen.scenes;

import com.eksamen.components.Bruker;
import com.eksamen.networking.ClientNetworking;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.uis.ClientUi;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientScene extends Scene {
    private ClientUi clientUi;
    private ClientNetworking nettverk;
    private Bruker bruker;
    private RomSystem romSystem;

    public ClientScene(Stage stage, ClientUi clientUi, String username) {
        super(clientUi.getHovedPane());
        this.clientUi = clientUi;
        nettverk = new ClientNetworking();
        nettverk.start();
        bruker = new Bruker(username);
        romSystem = new RomSystem(clientUi.getHovedLayout().getRomListe(), bruker);
        romSystem.opprettRomOgBruker();
    }
}
