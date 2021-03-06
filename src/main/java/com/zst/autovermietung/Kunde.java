package com.zst.autovermietung;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;

public class Kunde extends Person {

    private int alter;
    //private Date datumVonFuehrerschein;
    private Date datumVonFuehrerschein;
    private boolean vorstrafen;
    private String vorstrafen_note;
    private ArrayList<Mietvertrag> alleMietvertraege;
    private boolean hatAuto;
    public int kunde_anzahl;

    public Kunde(String id, String name, String nachname, String telefonnummer, String geschlecht, Date geburtsdatum, String adresse, Date fuehrerschein, boolean vorstrafen, String vorstrafen_note, boolean hAuto) {
        super(id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse);

        this.datumVonFuehrerschein = fuehrerschein;
        this.vorstrafen = vorstrafen;
        this.vorstrafen_note = vorstrafen_note;

        this.alter = berechneAlter();
        this.hatAuto = hAuto;
        kunde_anzahl++;
    }

    public Kunde(String id, String name, String nachname, String telefonnummer, String geschlecht, Date geburtsdatum, String adresse, Date fuehrerschein) {
        super(id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse);

        this.datumVonFuehrerschein = fuehrerschein;
        this.alter = berechneAlter();

        kunde_anzahl++;
    }


    public static boolean checkAlter(int alter) {
        if (alter >= 21) {
            return true;
        } else {
            return false;
        }
    }

    public void setAlter() {
        this.alter = berechneAlter();
    }

    public int getAlter() {
        return alter;
    }

    public int berechneAlter() {

        LocalDate gb = getGeburtsdatum().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Period.between(gb, LocalDate.now()).getYears();
    }

    public static boolean checkFuhrerschein(Date fuhrerschein) {
        LocalDate fs = fuhrerschein.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int a = Period.between(fs, LocalDate.now()).getYears();

        if(a >= 1) {
            return true;
        }else {
            return false;
        }
    }

    public void setHatAuto(boolean b) {
        this.hatAuto = b;
    }

    public boolean getHatAuto() {
        return this.hatAuto;
    }

    public boolean checkFuehrerschein(Date date) {
        return true;
    }

    public void setDatumVonFuehrerschein(Date datum) {
        this.datumVonFuehrerschein = datum;
    }

    SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
    public String getDatumVonFuehrerscheinString() {
        String fdatum = dformat.format(datumVonFuehrerschein);
        return fdatum;
    }

    public Date getDatumVonFuehrerschein() {
        return datumVonFuehrerschein;
    }

    public void setVorstrafe(boolean vorstrafen) {

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



