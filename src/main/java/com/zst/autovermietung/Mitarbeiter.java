package com.zst.autovermietung;

import java.util.ArrayList;
import java.util.Date;

public class Mitarbeiter extends Benutzer{

    public Autovermietung arbeitetIn;
    private ArrayList<Mietvertrag> erstellteMietvertraege;

    public Mitarbeiter(String id, String name, String nachname, String telefonnummer, String geschlecht, Date geburtsdatum, String adresse, String benutzername, String passwort, Autovermietung autovermietung){
        super(id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse, benutzername, passwort);

        this.arbeitetIn = autovermietung;
    }

    public void addMietvertrag(Mietvertrag m){
        //boolean olabilir
        erstellteMietvertraege.add(m);
    }

    public boolean removeMietvertrag(Mietvertrag m){
        erstellteMietvertraege.remove(m);
        return true;
    }

    public ArrayList<Mietvertrag> getMietvertraege(){
        return erstellteMietvertraege;
    }

    public Mietvertrag findeMietvertagNachId(String id){
        ////
    }

}
