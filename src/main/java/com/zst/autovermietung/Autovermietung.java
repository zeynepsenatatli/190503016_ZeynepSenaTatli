package com.zst.autovermietung;

import java.util.ArrayList;

public class Autovermietung{

    private String name;
    private String adresse;
    private String telefonnummer;
    private Manager manager;
    private ArrayList<Mitarbeiter> mitarbeiter;
    private ArrayList<Auto> autos;
    private ArrayList<Kunde> kunden;
    private ArrayList<Mietvertrag> mietvertraege;
    private ArrayList<Mietvertrag> vorgangeneMietvertraege;


    public Autovermietung(String name, String adresse, String telefonnummer, Manager manager){
        this.name = name;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
        this.manager = manager;
    }


    public boolean addKunde(Kunde k){
        kunden.add(k);
        return true;
    }

    public boolean addAuto(Auto a){
        autos.add(a);
        return true;
    }

    public boolean addMietvertrag(Mietvertrag m){
        mietvertraege.add(m);
        return true;
    }

    public boolean addMitarbeiter(Mitarbeiter m){
        mitarbeiter.add(m);
        return true;
    }

    public Kunde getKunde(String id){
        // es soll eine Funktion heisst findeKunde und index
        // von Kunde zurückgeben. hier wird diese Funktion
        // verwendet. danach kann getKunde richtige Kunde
        //zurückgeben

        //return kunde;
    }

    public Auto getAuto(String nummernschild){
        // es soll eine Funktion heisst findeAuto und index
        // von Auto zurückgeben. hier wird diese Funktion
        // verwendet. danach kann getAuto richtige Auto
        //zurückgeben

        //return auto;
    }

    public Mietvertrag getVertraege(String id){
        // gleiche Gedanke wie oben

        //return mietvertrag;
    }

    public Mietvertrag getVorgangeneVertraege(String id){
        // gleiche Gedanke wie oben

        //return m;
    }

    public Mitarbeiter getMitarbeiter(String id){
        // gleiche Gedanke wie oben

        //return mitarbeiter;
    }

    public Manager getManager(String id){
        // gleiche Gedanke wie oben

        //return m;
    }

    public boolean removeKunde(String id){
        //boolean a = false;
        //findeKunde(id);
        //kunden.rm(//);
        return true;
    }

    public boolean removeAuto(String nummernschild){
        //boolean a = false;
        //findeAuto(nummernschild);
        //autos.rm(   );
        return true;
    }

    public boolean removeVertrag(String id){
        //boolean a = false;
        //findeVertrag(id);
        //vorgangeneMietvertraege.add( );
        //mietvertraege.rm( );
        return true;
    }

    public boolean removeMietarbeiter(String id){
        //boolean a = false;
        //findeVertrag(id);
        //mitarbeiter.rm( );
        return true;
    }

    public int findeAuto(String nummernschild){
        return 0; //index_of_auto;
    }

    public int findeKunde(String id){
        return 0; //index_of_kunde;
    }

    public int findeMitarbeiter(String id){
        return 0; // index_of_mitarbeiter;
    }

    public int findeMietvertrag(String id){
        return 0; //index_of_mietvertrag;
    }


}
