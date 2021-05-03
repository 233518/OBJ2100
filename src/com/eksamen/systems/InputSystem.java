package com.eksamen.systems;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.systems.romsystem.RomTabell;
import com.eksamen.uis.layouts.HovedLayout;
import com.eksamen.uis.layouts.RomChat;
import com.eksamen.uis.layouts.RomListeUI;

public class InputSystem {
    private RomChat romChat;
    private MessageSystem message;
    private Bruker bruker;
    private HovedLayout hovedLayout;
    private RomListeUI romListeUI;
    private RomSystem romSystem;


    public InputSystem(RomListeUI romListeUI, Bruker bruker, HovedLayout hovedLayout, MessageSystem message, RomChat romChat) {
        this.romListeUI = romListeUI;
        this.bruker = bruker;
        this.hovedLayout = hovedLayout;
        this.message = message;
        this.romChat = romChat;
        sendMelding();
        bliMedRom();
        opprettRom();
        visOpprettRom();
    }

    public void sendMelding() {
        romChat.getSendKnapp().setOnAction(actionEvent -> {
            String melding = romChat.getMeldingsBoks().getText();
            message = new MessageSystem(bruker.getName(), melding);
            System.out.println(message.getBrukernavn() + " " + message.getMelding() + " " + message.getTimestamp());

        });
    }

    public void opprettRom() {
        romListeUI.getButtonLeggTilRom().setOnAction(actionEvent -> {
            romSystem = new RomSystem(romListeUI, bruker);
            Rom rom = new Rom(romListeUI.getTextField().getText(), bruker.getName());
            romSystem.opprettRom(rom);
            hovedLayout.lagNyTab(romListeUI.getTextField().getText());
            romListeUI.skjulOpprettRom();
        });
    }

    public void visOpprettRom() {
        romListeUI.getButtonNyttRom().setOnAction(actionEvent -> {
            System.out.println("Halla på deg");
            romListeUI.visOpprettRom();
        });
    }

    public void bliMedRom(){
        romListeUI.getButtonBliMed().setOnAction(actionEvent -> {
            RomTabell rom = romListeUI.getRomTableView().getRomTableView().getSelectionModel().getSelectedItem();
            hovedLayout.lagNyTab(rom.getRomNavn());
        });
    }

}
