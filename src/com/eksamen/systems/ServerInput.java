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

    /**
     * Lager ActionEvent på knappen "sendKnapp" i RomChat
     * Den henter tekst fra textfield "meldingsBoks" og lager et nytt InndataTabell
     * og oppdaterer selve meldingslisten.
     */
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

    /**
     * Lager ActionEvent på "opprettRom" knappen i RomListeUI
     * Denne henter teksten som brukeren kaller rommet, og lager ett nytt romobjekt og sender dette romobjektet videre,
     * den "joiner" også brukeren i chatterommet.
     */
    @Override
    public void opprettRom() {
        romListeUI.getButtonLeggTilRom().setOnAction(actionEvent -> {
            try{
                Rom rom = new Rom(romListeUI.getTextField().getText(), bruker.getName());
                if(romListeUI.getTextField().getText() != ""){
                    bruker.setRom(rom);
                    setRom();
                    mainRoomList.add(rom);
                    romSystem.opprettRom(rom);
                    hovedLayout.lagNyTab(romListeUI.getTextField().getText());
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
                System.out.println(e);
                Feilmelding.visFeilmelding("Noe gikk galt");
            }
        });
    }

    /**
     *  Lager ActionEvent for å skjule meldingen som kommer opp om du allerede har opprettet et rom
     */
    @Override
    public void OkKnappAlleredeOpprettetRom() {
        romListeUI.getAlleredeRomButton().setOnAction(actionEvent -> {
            romListeUI.skjulAlleredeOpprettetRom();
        });
    }

    /**
     * Lager ActionEvent for å bli med i ett chatterom.
     * Den setter brukeren til det rommet som er valgt listen, og "joiner" brukeren i rommet.
     * Den henter også tidligere meldinger og aktive brukere i rommet.
     */
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
                //mainRoomList.add(rom);
                hovedLayout.lagNyTab(rom.getRomNavn());
                deltakerTabell = new DeltakerTabell(bruker.getName());
                rom.leggTilDeltaker(deltakerTabell);
                romChat.oppdaterDeltakerListe(romSystem.getDeltakere(rom));
                romChat.oppdaterMeldingListe(message.getMeldinger(rom));
                serverNetworking.sendNewUser(rom.getRomNavn(), bruker.getName());
                forlatRom(deltakerTabell, rom, mainRoomList);
            }catch (Exception e){
                System.out.println(e);
                Feilmelding.visFeilmelding("Noe gikk galt");
            }
        });
    }

    /**
     * Metode for når en bruker forlater et rom
     * Andre brukere som er i samme rom vil få en oppdatert deltakerliste og
     * brukeren vil slettes fra rommets deltakerliste
     *
     * Den sjekker også om rommet har deltakere, om rommet når 0 deltakere vil
     * rommet slettes
     * @param deltakerTabell Deltakeren
     * @param rom Rommet
     * @param romarray Array med rom
     */
    public void forlatRom(DeltakerTabell deltakerTabell, Rom rom, ArrayList romarray) {
        hovedLayout.getTab().setOnCloseRequest(event -> {
            serverNetworking.removeBruker(rom.getRomNavn(), bruker.getName());
            romChat.getDeltakere().getItems().remove(deltakerTabell);
            rom.slettDeltaker(deltakerTabell);
            bruker.setRom(null);
            setRom();
            if(romChat.getDeltakere().getItems().size() == 0){
                romSystem.slettRom(rom, romarray);
                serverNetworking.removeRoom("removeRoom", rom, bruker.getName());
            }
        });
    }
}
