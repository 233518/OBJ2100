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
import com.eksamen.utils.Feilmelding;
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
            romChat.getMeldingsBoks().setText("");
        });
    }

    @Override
    public void opprettRom() {
        romListeUI.getButtonLeggTilRom().setOnAction(actionEvent -> {
            try{
                hovedLayout.slettTab();
                Rom rom = new Rom(romListeUI.getTextField().getText(), bruker.getName());
                if(romListeUI.getTextField().getText() != ""){
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
                } else {
                    Feilmelding.visFeilmelding("Dette feltet kan ikke være tomt");
                }
            } catch(Exception e){
                Feilmelding.visFeilmelding("Noe gikk galt");
            }

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
            try{
                for(Object o : romChat.getDeltakere().getItems()){
                    if(bruker.getName() == romChat.getDeltakerKolonne1().getCellData(o)){
                        Feilmelding.visFeilmelding("Du må forlate det andre rommet din nisse");
                        return;
                    }
                }
                Rom rom = romListeUI.getRomTableView().getRomTableView().getSelectionModel().getSelectedItem();
                if(rom == null){
                    Feilmelding.visFeilmelding("Du må velge ett rom");
                    return;
                }
                hovedLayout.slettTab();
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
            } catch(Exception e){
                System.out.println(e);
            }

        });
    }

    public void forlatRom(DeltakerTabell deltakerTabell, Rom rom, ArrayList romarray) {
        hovedLayout.getTab().setOnCloseRequest(event -> {
            clientNetworking.removeBruker("removeBruker", rom, bruker.getName());
            romChat.getDeltakere().getItems().remove(deltakerTabell);
            rom.slettDeltaker(deltakerTabell);
            bruker.setRom(null);
            setRom();
            if(romChat.getDeltakere().getItems().size() == 0){
                romSystem.slettRom(rom, romarray);
                clientNetworking.removeRoom("removeRoom", rom);
            }
        });
    }
}
