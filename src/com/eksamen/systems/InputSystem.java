package com.eksamen.systems;

import com.eksamen.uis.layouts.RomChat;
import javafx.scene.control.Button;

public class InputSystem {
    private RomChat romChat;

    public void sendMelding(){
        romChat.getSendKnapp().setOnAction(actionEvent -> {
            String melding = romChat.getMeldingsBoks().getText();
            romChat.getMeldingsBoks().setText("");
        });
    }

}
