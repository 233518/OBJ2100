package com.eksamen.scenes;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.networking.ClientNetworking;
import com.eksamen.systems.ClientInput;
import com.eksamen.systems.InputSystem;
import com.eksamen.systems.MessageSystem;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.uis.ClientUi;
import com.eksamen.uis.layouts.RomChat;
import com.eksamen.utils.Infomelding;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

/**
 * ClientScene kobler sammen alt og er hoved vinduet til klienten
 */
public class ClientScene extends Scene {
    private ClientUi clientUi;
    private ClientNetworking nettverk;
    private Bruker bruker;
    private RomSystem romSystem;
    private ArrayList<Rom> rooms;
    private InputSystem inputSystem;
    private MessageSystem messageSystem;
    private RomChat romChat;

    /**
     * Konstruerer en ny ClientScene
     * @param stage stage som scene skal tilhøre
     * @param clientUi klienten sin ui
     * @param username brukernavn til klienten
     */
    public ClientScene(Stage stage, ClientUi clientUi, String username) {
        super(clientUi.getHovedPane(), 750, 550);
        this.clientUi = clientUi;
        rooms = new ArrayList<>();
        bruker = new Bruker(username);

        nettverk = new ClientNetworking(this, bruker);
        nettverk.start();

        rooms = nettverk.getRooms();

        nettverk.newKobling(username);

        romSystem = new RomSystem(clientUi.getHovedLayout().getRomListe(), bruker);
        romSystem.fyllInnTableview(rooms);

        messageSystem = new MessageSystem();

        inputSystem = new ClientInput(clientUi.getHovedLayout().getRomListe(), bruker, clientUi.getHovedLayout(), messageSystem, clientUi.getHovedLayout().getRomChat(), romSystem, rooms, nettverk);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                if(bruker.getRom() != null) {
                    nettverk.removeBruker("removeBruker", bruker.getRom(), bruker.getName());
                }
                nettverk.stopNetwork();
                Platform.exit();
                System.exit(0);
            }
        });
    }

    /**
     * Skaffer romsystemet
     * @return RomSystem
     */
    public RomSystem getRomSystem() {
        return romSystem;
    }

    /**
     * Skaffer liste med rom
     * @return ArrayList av Rom
     */
    public ArrayList<Rom> getRooms() {
        return rooms;
    }

    /**
     * Skaffer meldings systemet
     * @return MessageSystem
     */
    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    /**
     * Skaffer klienten sin ui
     * @return ClientUi
     */
    public ClientUi getClientUi() {
        return clientUi;
    }

    /**
     * Skaffer brukeren til klienten
     * @return Bruker
     */
    public Bruker getBruker() {
        return bruker;
    }

    /**
     * Viser en melding når ny bruker kobler til chatteprogrammet
     * @param melding melding som skal vises
     */
    public void visInformasjonsMelding(String melding) {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                Infomelding.visInfoMelding(melding);
            }
        });
    }
}
