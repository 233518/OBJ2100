package com.eksamen.systems.romsystem;

import com.eksamen.components.Bruker;
import com.eksamen.components.Rom;


public class RomSystem {
    private Rom rom;
    private Bruker bruker;

    public RomSystem(Rom rom){
        this.rom = rom;
    }

    public void lagRom(){
        //rom = new Rom(1, "Hey", bruker.getName());
    }

    public void bliMedIRom(){
        //RomTabell rom = romliste.getRomTableView().getSelectionModel().getSelectedItem();
    }
}
