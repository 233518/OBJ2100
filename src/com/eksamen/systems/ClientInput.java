package com.eksamen.systems;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.networking.ClientNetworking;
import com.eksamen.scenes.ClientScene;
import com.eksamen.systems.chatsystem.DeltakerTabell;
import com.eksamen.systems.chatsystem.InndataTabell;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.uis.layouts.HovedLayout;
import com.eksamen.uis.layouts.RomChat;
import com.eksamen.uis.layouts.RomListeUI;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class ClientInput extends InputSystem{
    private ClientNetworking clientNetworking;

    public ClientInput(RomListeUI romListeUI, Bruker bruker, HovedLayout hovedLayout, MessageSystem message, RomChat romChat,RomSystem romSystem,ArrayList<Rom> mainRoomList, ClientNetworking clientNetworking) {
        super(romListeUI, bruker, hovedLayout, message, romChat, romSystem, mainRoomList);
        this.clientNetworking = clientNetworking;
    }

    @Override
    public void sendMelding() {
        romChat.getSendKnapp().setOnAction(actionEvent -> {
            String melding = romChat.getMeldingsBoks().getText();
            inndataTabell = new InndataTabell(bruker.getName(), melding);
            message.nyMelding(rom, inndataTabell);
            romChat.oppdaterMeldingListe(message.getMeldinger(rom));
            System.out.println("Kjører en gang");
            clientNetworking.newMessage("newMessage", rom, bruker.getName(), melding);
        });
    }

    @Override
    public void opprettRom() {
        romListeUI.getButtonLeggTilRom().setOnAction(actionEvent -> {
            hovedLayout.slettTab();
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
            clientNetworking.newRoom("newRoom", rom);
            clientNetworking.newBruker("newBruker", rom, bruker.getName());
            romChat.oppdaterMeldingListe(message.getMeldinger(rom));
            forlatRom(deltakerTabell, rom, mainRoomList);
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
            hovedLayout.slettTab();
            Rom rom = romListeUI.getRomTableView().getRomTableView().getSelectionModel().getSelectedItem();
            //mainRoomList.add(rom);
            hovedLayout.lagNyTab(rom.getRomNavn());
            deltakerTabell = new DeltakerTabell(bruker.getName());
            rom.leggTilDeltaker(deltakerTabell);
            romChat.oppdaterDeltakerListe(romSystem.getDeltakere(rom));
            bruker.setRom(rom);
            setRom();
            romChat.oppdaterMeldingListe(message.getMeldinger(rom));
            clientNetworking.newBruker("newBruker", rom, bruker.getName());
            forlatRom(deltakerTabell, rom, mainRoomList);
        });
    }

    public void forlatRom(DeltakerTabell deltakerTabell, Rom rom, ArrayList romarray) {
        hovedLayout.getTab().setOnCloseRequest(event -> {
            romChat.getDeltakere().getItems().remove(deltakerTabell);
            mainRoomList.remove(rom);
            if(romChat.getDeltakere().getItems().size() == 0){
                romSystem.slettRom(rom, romarray);
            }
        });
    }

}
