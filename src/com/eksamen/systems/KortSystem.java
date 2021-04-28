package com.eksamen.systems;

import com.eksamen.components.Kort;
import com.sun.tools.jdeprscan.scan.Scan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class KortSystem {
    private ArrayList<Kort> kortFerdig;
    private ArrayList<Kort> kortLedig;

    public KortSystem() {
        this.kortFerdig = new ArrayList<>();
        this.kortLedig = new ArrayList<>();
        lesInnOrd();
    }
    private void addKort(Kort kort) {
        this.kortLedig.add(kort);
    }
    private void kortTatt(Kort kort) {
        int kortIndex = kortLedig.indexOf(kort);
        kortFerdig.add(kort);
        kortLedig.remove(kortIndex);
    }

    private void lesInnOrd() {
        try {
            File myFile = new File(getClass().getResource("ord.txt").toString());
            Scanner myScanner = new Scanner(myFile);
            while(myScanner.hasNextLine()) {
                String ord = myScanner.nextLine();
                addKort(new Kort(ord));
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Kort skaffNyttKort() {
        return kortLedig.get(0);
    }
}
