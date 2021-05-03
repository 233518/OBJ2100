package com.eksamen.systems;

import com.eksamen.components.Bruker;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.uis.layouts.HovedLayout;
import com.eksamen.uis.layouts.RomChat;
import com.eksamen.uis.layouts.RomListeUI;
import javafx.scene.control.Button;

public class InputSystem {
    private RomChat romChat;
    private MessageSystem message;
    private Bruker bruker;
    private HovedLayout hovedLayout;
    private RomListeUI romListeUI;
    private RomSystem romSystem;


    public InputSystem(RomListeUI romListeUI, Bruker bruker, HovedLayout hovedLayout){
        this.romListeUI = romListeUI;
        this.bruker = bruker;
        this.hovedLayout = hovedLayout;
        bliMedRom();
        opprettRom();
    }

    public void sendMelding(){
        romChat.getSendKnapp().setOnAction(actionEvent -> {
            String melding = romChat.getMeldingsBoks().getText();
            message = new MessageSystem(bruker.getName(), melding);
        });
    }

    public void opprettRom(){
        romListeUI.getButtonNyttRom().setOnAction(actionEvent -> {
            romSystem = new RomSystem(romListeUI, bruker);
            romSystem.opprettRom("Rom 1", bruker.getName());
            hovedLayout.lagNyTab("Rom 1");
        });
    }

    public void bliMedRom(){
        romListeUI.getButtonBliMed().setOnAction(actionEvent -> {
            System.out.println("hello");
        });
    }

}
