package com.eksamen.systems;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;
import com.eksamen.uis.layouts.RomListe.RomListe;
import com.eksamen.uis.layouts.RomListe.RomTabell;


public class RomSystem {
    private Rom rom;
    private Bruker bruker;
    private RomListe romliste;

    public RomSystem(Rom rom){
        this.rom = rom;
    }

    public void lagRom(){
        //rom = new Rom(1, "Hey", bruker.getName());
    }

    public void bliMedIRom(){
        RomTabell rom = romliste.getRomTableView().getSelectionModel().getSelectedItem();
    }
}
