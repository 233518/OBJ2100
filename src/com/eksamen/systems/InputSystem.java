package com.eksamen.systems;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.systems.chatsystem.InndataTabell;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.systems.romsystem.RomTabell;
import com.eksamen.uis.layouts.HovedLayout;
import com.eksamen.uis.layouts.RomChat;
import com.eksamen.uis.layouts.RomListeUI;

public class InputSystem {
    private RomChat romChat;
    private MessageSystem message;
    private InndataTabell inndataTabell;
    private Bruker bruker;
    private HovedLayout hovedLayout;
    private RomListeUI romListeUI;
    private RomSystem romSystem;
    private Rom rom;


    public InputSystem(RomListeUI romListeUI, Bruker bruker, HovedLayout hovedLayout, MessageSystem message, RomChat romChat){
        this.romListeUI = romListeUI;
        this.bruker = bruker;
        this.hovedLayout = hovedLayout;
        this.message = message;
        this.romChat = romChat;
        rom = bruker.getRom();
        sendMelding();
        bliMedRom();
        opprettRom();
        visOpprettRom();
    }

    /**
     * ActionEvent for å sende melding
     */
    public void sendMelding(){
        romChat.getSendKnapp().setOnAction(actionEvent -> {
            String melding = romChat.getMeldingsBoks().getText();
            inndataTabell = new InndataTabell(bruker.getName(), melding);
            System.out.println(inndataTabell);
            message.nyMelding(rom, inndataTabell);
            romChat.oppdaterMeldingListe(message.getMeldinger(rom));
        });
    }

    /**
     * ActionEvent for å opprette rom
     */
    public void opprettRom(){
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

    public void visOpprettRom() {
        romListeUI.getButtonNyttRom().setOnAction(actionEvent -> {
            romListeUI.visOpprettRom();
        });
    }

    /**
     * ActionEvent for å bli med i ett chatterom
     */
    public void bliMedRom(){
        romListeUI.getButtonBliMed().setOnAction(actionEvent -> {
            RomTabell romTabell = romListeUI.getRomTableView().getRomTableView().getSelectionModel().getSelectedItem();
            hovedLayout.lagNyTab(romTabell.getRomNavn());

            Rom rom = new Rom(romTabell.getRomNavn(), romTabell.getOpprettetNavn());
            bruker.setRom(rom);
            setRom();
            //romChat.oppdaterDeltakerListe(rom.getBrukere());
        });
    }

    public void setRom(){
        rom = bruker.getRom();
    }
}
