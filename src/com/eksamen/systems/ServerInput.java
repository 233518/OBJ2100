package com.eksamen.systems;

import com.eksamen.Main;
import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.networking.ServerNetworking;
import com.eksamen.systems.chatsystem.DeltakerTabell;
import com.eksamen.systems.chatsystem.InndataTabell;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.uis.layouts.HovedLayout;
import com.eksamen.uis.layouts.RomChat;
import com.eksamen.uis.layouts.RomListeUI;
import com.eksamen.utils.Feilmelding;

import java.util.ArrayList;

/**
 * Klasse for å håndtere ActionEvents, når brukeren trykker en knapp
 */
public class ServerInput extends InputSystem {
    private ServerNetworking serverNetworking;

    /**
     * Constructor for buttons
     * @param romListeUI UI for listen over rom
     * @param bruker Brukeren
     * @param hovedLayout Layout som holder på alle andre UI
     * @param message Meldingssystem
     * @param romChat Chatterom UI
     * @param romSystem RomSystem
     * @param roomsMainList Romliste
     * @param serverNetworking Nettverksdelen for serveren
     */
    public ServerInput(RomListeUI romListeUI, Bruker bruker, HovedLayout hovedLayout, MessageSystem message, RomChat romChat, RomSystem romSystem, ArrayList<Rom> roomsMainList, ServerNetworking serverNetworking) {
        super(romListeUI, bruker, hovedLayout, message, romChat,romSystem, roomsMainList);
        this.serverNetworking = serverNetworking;
    }

    @Override
    public void sendMelding() {
        romChat.getSendKnapp().setOnAction(actionEvent -> {
            String melding = romChat.getMeldingsBoks().getText();
            melding = melding.replace(":", "");
            if(melding.equals("")) {
                Feilmelding.visFeilmelding("Du kan ikke sende en tom melding");
                return;
            };
            inndataTabell = new InndataTabell(bruker.getName(), melding);
            message.nyMelding(rom, inndataTabell);
            romChat.oppdaterMeldingListe(message.getMeldinger(rom));
            serverNetworking.sendNewMessage(rom.getRomNavn(), bruker.getName(), melding);
            romChat.getMeldingsBoks().setText("");
        });
    }

    @Override
    public void opprettRom() {
        romListeUI.getButtonLeggTilRom().setOnAction(actionEvent -> {
            try{

                String brukerInput = romListeUI.getTextField().getText().replace(":", "");
                for(Rom romSjekk : mainRoomList) {
                    if(brukerInput.equals(romSjekk.getRomNavn())) {
                        Feilmelding.visFeilmelding("Navnet eksisterer allerede! Velg et annet!");
                        return;
                    }
                }
                Rom rom = new Rom(brukerInput, bruker.getName());
                if(romListeUI.getTextField().getText() != ""){
                    bruker.setRom(rom);
                    setRom();
                    mainRoomList.add(rom);
                    romSystem.opprettRom(rom);
                    hovedLayout.lagNyTab(romListeUI.getTextField().getText().replace(":", ""));
                    deltakerTabell = new DeltakerTabell(bruker.getName());
                    rom.leggTilDeltaker(deltakerTabell);
                    romChat.oppdaterDeltakerListe(romSystem.getDeltakere(rom));
                    romListeUI.skjulOpprettRom();
                    serverNetworking.sendNewRoom(rom.getRomNavn(), bruker.getName());
                    serverNetworking.sendNewUser(rom.getRomNavn(), bruker.getName());
                    romChat.oppdaterMeldingListe(message.getMeldinger(rom));
                    forlatRom(deltakerTabell, rom, mainRoomList);
                } else{
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
            try {
                for (Object o : romChat.getDeltakere().getItems()) {
                    if (bruker.getName() == romChat.getDeltakerKolonne1().getCellData(o)) {
                        Feilmelding.visFeilmelding("Du må forlate det andre rommet");
                        return;
                    }
                }
                Rom rom = romListeUI.getRomTableView().getRomTableView().getSelectionModel().getSelectedItem();
                if (rom == null) {
                    Feilmelding.visFeilmelding("Du må velge ett rom");
                    return;
                }
                bruker.setRom(rom);
                setRom();
                hovedLayout.lagNyTab(rom.getRomNavn());
                deltakerTabell = new DeltakerTabell(bruker.getName());
                rom.leggTilDeltaker(deltakerTabell);
                romChat.oppdaterDeltakerListe(romSystem.getDeltakere(rom));
                romChat.oppdaterMeldingListe(message.getMeldingerBliMed(rom));
                serverNetworking.sendNewUser(rom.getRomNavn(), bruker.getName());
                forlatRom(deltakerTabell, rom, mainRoomList);
            }catch (Exception e){
                Feilmelding.visFeilmelding("Noe gikk galt");
            }
        });
    }

    @Override
    public void forlatRom(DeltakerTabell deltakerTabell, Rom rom, ArrayList romarray) {
        hovedLayout.getTab().setOnCloseRequest(event -> {
            serverNetworking.removeBruker(rom.getRomNavn(), bruker.getName());
            romChat.getDeltakere().getItems().remove(deltakerTabell);
            rom.slettDeltaker(deltakerTabell);
            bruker.setRom(null);
            setRom();
            if(romChat.getDeltakere().getItems().size() == 0){
                romSystem.slettRom(rom, romarray);
                serverNetworking.removeRoom(rom.getRomNavn(), bruker.getName());
            }
        });
    }
}
