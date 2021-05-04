package com.eksamen.systems;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.networking.ClientNetworking;
import com.eksamen.systems.chatsystem.InndataTabell;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.uis.layouts.HovedLayout;
import com.eksamen.uis.layouts.RomChat;
import com.eksamen.uis.layouts.RomListeUI;

public class ClientInput extends InputSystem{
    private ClientNetworking clientNetworking;

    public ClientInput(RomListeUI romListeUI, Bruker bruker, HovedLayout hovedLayout, MessageSystem message, RomChat romChat, ClientNetworking clientNetworking) {
        super(romListeUI, bruker, hovedLayout, message, romChat);
        this.clientNetworking = clientNetworking;
    }

    @Override
    public void sendMelding() {
        romChat.getSendKnapp().setOnAction(actionEvent -> {
            String melding = romChat.getMeldingsBoks().getText();
            inndataTabell = new InndataTabell(bruker.getName(), melding);
            System.out.println(inndataTabell);
            message.nyMelding(rom, inndataTabell);
            romChat.oppdaterMeldingListe(message.getMeldinger(rom));
        });
    }

    @Override
    public void opprettRom() {
        romListeUI.getButtonLeggTilRom().setOnAction(actionEvent -> {
            romSystem = new RomSystem(romListeUI, bruker);
            Rom rom = new Rom(romListeUI.getTextField().getText(), bruker.getName());
            romSystem.opprettRom(rom);
            hovedLayout.lagNyTab(romListeUI.getTextField().getText());
            romListeUI.skjulOpprettRom();
            bruker.setRom(rom);
            setRom();
        });
    }

    @Override
    public void bliMedRom() {
        romListeUI.getButtonBliMed().setOnAction(actionEvent -> {
            Rom rom = romListeUI.getRomTableView().getRomTableView().getSelectionModel().getSelectedItem();
            hovedLayout.lagNyTab(rom.getRomNavn());
            bruker.setRom(rom);
            setRom();
            //romChat.oppdaterDeltakerListe(rom.getBrukere());
        });
    }
}
