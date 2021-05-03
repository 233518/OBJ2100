package com.eksamen.systems;

import com.eksamen.components.Bruker;
import com.eksamen.uis.layouts.RomChat;
import javafx.scene.control.Button;

public class InputSystem {
    private RomChat romChat;
    private MessageSystem message;
    private Bruker bruker;

    public InputSystem(){

    }

    public void sendMelding(){
        romChat.getSendKnapp().setOnAction(actionEvent -> {
            String melding = romChat.getMeldingsBoks().getText();
            message = new MessageSystem(bruker.getName(), melding);
        });
    }

}
