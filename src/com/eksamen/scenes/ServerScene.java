package com.eksamen.scenes;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.networking.ServerNetworking;
import com.eksamen.systems.InputSystem;
import com.eksamen.systems.MessageSystem;
import com.eksamen.systems.ServerInput;
import com.eksamen.systems.loggsystem.LoggSystem;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.uis.ServerUi;
import com.eksamen.uis.layouts.RomChat;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

/**
 * ServerScene kobler sammen alt og er hoved vinduet til serveren
 */
public class ServerScene extends Scene {
    private ServerUi serverUi;
    private ServerNetworking nettverk;
    private ArrayList<Rom> rooms;
    private Bruker bruker;
    private RomSystem romSystem;
    private InputSystem inputSystem;
    private MessageSystem message;
    private RomChat romChat;
    /**
     * Konstruerer en ny ServerScene
     * @param stage stage som scene skal tilhøre
     * @param serverUi serveren sin ui
     * @param loggSystem loggsystemet som skal brukes
     */
    public ServerScene(Stage stage, ServerUi serverUi, LoggSystem loggSystem) {
        super(serverUi.getHovedPane(), 750, 600);
        this.serverUi = serverUi;
        nettverk = new ServerNetworking(this);
        nettverk.start();
        rooms = new ArrayList<>();
        bruker = new Bruker("Admin");
        romSystem = new RomSystem(serverUi.getHovedLayout().getRomListe(), bruker);
        romSystem.fyllInnTableview(rooms);
        message = new MessageSystem();
        inputSystem = new ServerInput(serverUi.getHovedLayout().getRomListe(), bruker, serverUi.getHovedLayout(), message, serverUi.getHovedLayout().getRomChat(), romSystem,rooms,nettverk);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                if(bruker.getRom() != null) {
                    nettverk.removeBruker("removeBruker", bruker.getName());
                }
                nettverk.stopNetwork();
                Platform.exit();
                System.exit(0);
            }
        });
    }
    /**
     * Skaffer rom systemet
     * @return RomSystem
     */
    public RomSystem getRomSystem() {
        return romSystem;
    }
    /**
     * Skaffer rom
     * @return ArrayList av Rom
     */
    public ArrayList<Rom> getRooms() {
        return rooms;
    }
    /**
     * Skaffer meldings systemet
     * @return MessageSystem
     */
    public MessageSystem getMessage() {
        return message;
    }
    /**
     * Skaffer serveren sin ui
     * @return ServerUi
     */
    public ServerUi getServerUi() {
        return serverUi;
    }
    /**
     * Skaffer brukeren til serveren
     * @return Bruker
     */
    public Bruker getBruker() {
        return bruker;
    }
}
