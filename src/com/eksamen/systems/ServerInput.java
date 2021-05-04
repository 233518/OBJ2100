package com.eksamen.systems;

import com.eksamen.components.Bruker;
import com.eksamen.networking.ServerNetworking;
import com.eksamen.uis.layouts.HovedLayout;
import com.eksamen.uis.layouts.RomChat;
import com.eksamen.uis.layouts.RomListeUI;

public class ServerInput extends InputSystem {
    private ServerNetworking serverNetworking;

    public ServerInput(RomListeUI romListeUI, Bruker bruker, HovedLayout hovedLayout, MessageSystem message, RomChat romChat, ServerNetworking serverNetworking) {
        super(romListeUI, bruker, hovedLayout, message, romChat);
        this.serverNetworking = serverNetworking;
    }

    @Override
    public void sendMelding() {

    }

    @Override
    public void opprettRom() {

    }

    @Override
    public void bliMedRom() {

    }
}
