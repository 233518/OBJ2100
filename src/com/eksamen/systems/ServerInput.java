package com.eksamen.systems;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.networking.ServerNetworking;
import com.eksamen.systems.chatsystem.DeltakerTabell;
import com.eksamen.systems.chatsystem.InndataTabell;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.uis.layouts.HovedLayout;
import com.eksamen.uis.layouts.RomChat;
import com.eksamen.uis.layouts.RomListeUI;

import java.util.ArrayList;

public class ServerInput extends InputSystem {
    private ServerNetworking serverNetworking;

    public ServerInput(RomListeUI romListeUI, Bruker bruker, HovedLayout hovedLayout, MessageSystem message, RomChat romChat, RomSystem romSystem, ArrayList<Rom> roomsMainList, ServerNetworking serverNetworking) {
        super(romListeUI, bruker, hovedLayout, message, romChat,romSystem, roomsMainList);
        this.serverNetworking = serverNetworking;
    }

    @Override
    public void sendMelding() {
        romChat.getSendKnapp().setOnAction(actionEvent -> {
            String melding = romChat.getMeldingsBoks().getText();
            inndataTabell = new InndataTabell(bruker.getName(), melding);
            message.nyMelding(rom, inndataTabell);
            romChat.oppdaterMeldingListe(message.getMeldinger(rom));
            serverNetworking.sendNewMessage(rom.getRomNavn(), bruker.getName(), melding);
        });
    }

    @Override
    public void opprettRom() {
        romListeUI.getButtonLeggTilRom().setOnAction(actionEvent -> {
            Rom rom = new Rom(romListeUI.getTextField().getText(), bruker.getName());
            mainRoomList.add(rom);
            romSystem.opprettRom(rom);
            hovedLayout.lagNyTab(romListeUI.getTextField().getText());
            deltakerTabell = new DeltakerTabell(bruker.getName());
            rom.leggTilDeltaker(deltakerTabell);
            romChat.oppdaterDeltakerListe(romSystem.getDeltakere(rom));
            romListeUI.skjulOpprettRom();
            bruker.setRom(rom);
            setRom();
            serverNetworking.sendNewRoom(rom.getRomNavn(), bruker.getName());
        });
    }

    @Override
    public void OkKnappAlleredeOpprettetRom() {
        romListeUI.getAlleredeRomButton().setOnAction(actionEvent -> {
            romListeUI.skjulAlleredeOpprettetRom();
        });
    }

    @Override
    public void bliMedRom() {
        romListeUI.getButtonBliMed().setOnAction(actionEvent -> {
            Rom rom = romListeUI.getRomTableView().getRomTableView().getSelectionModel().getSelectedItem();
            hovedLayout.lagNyTab(rom.getRomNavn());
            deltakerTabell = new DeltakerTabell(bruker.getName());
            rom.leggTilDeltaker(deltakerTabell);
            romChat.oppdaterDeltakerListe(romSystem.getDeltakere(rom));
            bruker.setRom(rom);
            setRom();
        });
    }
}
