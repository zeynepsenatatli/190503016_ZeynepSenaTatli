package com.zst.autovermietung;

import java.util.Date;
import java.util.ArrayList;

public class Kunde extends Person {

    private int alter;
    private Date datumVonFuehrerschein;
    private boolean vorstrafen;
    private String vorstrafen_note;
    private ArrayList<Mietvertrag> alleMietvertraege;
    public int kunde_anzahl;

    public Kunde(String id, String name, String nachname, String telefonnummer, String geschlecht, Date geburtsdatum, String adresse, Date fuehrerschein) {
        super(id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse);

        this.datumVonFuehrerschein = fuehrerschein;

        if (checkAlter(berechneAlter(geburtsdatum))) {
            setAlter(berechneAlter(geburtsdatum));
        } else {
            //exception;
        }

        kunde_anzahl++;
    }


    public boolean checkAlter(int alter) {
        if (alter >= 21) {
            return true;
        } else {
            return false;
        }

    }

    public int berechneAlter(Date geburtsdatum) {
        //alter = heute-gdatum;
        return 21;
    }

    //soll es private sein?
    public void setAlter(int alter) {
        this.alter = alter;
    }

    public int getAlter() {
        return alter;
    }

    public boolean checkFuehrerschein(Date date) {
        return true;
    }

    public void setDatumVonFuehrerschein(Date datum) {
        this.datumVonFuehrerschein = datum;
    }

    public Date getDatumVonFuehrerschein() {
        return datumVonFuehrerschein;
    }

    public void setVorstrafe(boolean vortrafen) {
        this.vorstrafen = vorstrafen;
    }

    public boolean getVorstrafe() {
        return vorstrafen;
    }

    public void setVorstrafeNote(String note) {
        this.vorstrafen_note = note;
    }

    public String getVorstrafeNote() {
        return vorstrafen_note;
    }

    public void addMietvertrag(Mietvertrag m) {
        //boolean olabilir
        alleMietvertraege.add(m);
    }

    public boolean removeMietvertrag(Mietvertrag m) {
        alleMietvertraege.remove(m);
        return true;
    }

    public ArrayList<Mietvertrag> getMietvertraege() {
        return alleMietvertraege;
    }

    /*public Mietvertrag findeMietvertagNachId(String id) {
        ////
    }*/

    @Override
    public String toString() {
        return " ";
    }


}



