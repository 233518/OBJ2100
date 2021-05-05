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
     * ActionEvent for å sende melding
     */
    public abstract void sendMelding();

    /**
     * ActionEvent for å opprette rom
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

    public abstract void OkKnappAlleredeOpprettetRom();

    /**
     * ActionEvent for å bli med i ett chatterom
     */
    public abstract void bliMedRom();

    /**
     * Setter rom til brukerens sitt rom
     */
    public void setRom(){
        rom = bruker.getRom();
    }
}
