package com.eksamen.systems;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.systems.chatsystem.DeltakerTabell;
import com.eksamen.systems.chatsystem.InndataTabell;
import com.eksamen.systems.romsystem.RomSystem;
import com.eksamen.uis.layouts.HovedLayout;
import com.eksamen.uis.layouts.RomChat;
import com.eksamen.uis.layouts.RomListeUI;
import java.util.ArrayList;

/**
 * Klassen InputSystem initaliserer alle ActionEvents metodene som er laget i ServerInput og ClientInput.
 */
public abstract class InputSystem {
    protected RomChat romChat;
    protected MessageSystem message;
    protected InndataTabell inndataTabell;
    protected Bruker bruker;
    protected HovedLayout hovedLayout;
    protected RomListeUI romListeUI;
    protected RomSystem romSystem;
    protected Rom rom;
    protected DeltakerTabell deltakerTabell;
    protected ArrayList<Rom> mainRoomList;

    /**
     * Constructor for Inputsystem
     * @param romListeUI UI til romliste
     * @param bruker Brukeren
     * @param hovedLayout   Layouten som inneholder all UI
     * @param message Meldingssystemet
     * @param romChat Chatterommets UI
     * @param romSystem Romsystem
     * @param mainRoomList Romliste
     */
    public InputSystem(RomListeUI romListeUI, Bruker bruker, HovedLayout hovedLayout, MessageSystem message, RomChat romChat, RomSystem romSystem, ArrayList<Rom> mainRoomList){
        this.romListeUI = romListeUI;
        this.bruker = bruker;
        this.hovedLayout = hovedLayout;
        this.message = message;
        this.romChat = romChat;
        this.romSystem = romSystem;
        this.mainRoomList = mainRoomList;
        rom = bruker.getRom();
        sendMelding();
        bliMedRom();
        opprettRom();
        visOpprettRom();
        OkKnappAlleredeOpprettetRom();
    }

    /**
     * Lager ActionEvent på knappen "sendKnapp" i RomChat
     * Den henter tekst fra textfield "meldingsBoks" og lager et nytt InndataTabell
     * og oppdaterer selve meldingslisten.
     */
    public abstract void sendMelding();

    /**
     * Lager ActionEvent på "opprettRom" knappen i RomListeUI
     * Denne henter teksten som brukeren kaller rommet, og lager ett nytt romobjekt og sender dette romobjektet videre,
     * den "joiner" også brukeren i chatterommet.
     */
    public abstract void opprettRom();

    /**
     * ActionEvent for å vise romnavn textfield
     */
    public void visOpprettRom() {
        romListeUI.getButtonNyttRom().setOnAction(actionEvent -> {
            if(this.bruker.getRom() == null) {
                romListeUI.visOpprettRom();
            }else if(this.bruker.getRom().getBrukerNavn() == bruker.getName()) {
                romListeUI.visAlleredeOpprettetRom();
            }
        });
    }

    /**
     *  Lager ActionEvent for å skjule meldingen som kommer opp om du allerede har opprettet et rom
     */
    public abstract void OkKnappAlleredeOpprettetRom();

    /**
     * Lager ActionEvent for å bli med i ett chatterom.
     * Den setter brukeren til det rommet som er valgt listen, og "joiner" brukeren i rommet.
     * Den henter også tidligere meldinger og aktive brukere i rommet.
     */
    public abstract void bliMedRom();

    /**
     * Setter rom til brukerens sitt rom
     */
    public void setRom(){
        rom = bruker.getRom();
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
    public abstract void forlatRom(DeltakerTabell deltakerTabell, Rom rom, ArrayList romarray);
}
