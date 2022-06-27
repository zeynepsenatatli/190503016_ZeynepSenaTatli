package com.zst.autovermietung;

import java.util.Date;

public class Benutzer extends Person{
    private String rolle;
    private String benutzername;
    private String passwort;

    public Benutzer(String id, String name, String nachname, String telefonnummer, String geschlecht, Date geburtsdatum, String adresse, String benutzername, String passwort){
        super(id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse);

        //ich könnte für benutzername und passwort check-Funktionen
        //schreiben
        this.benutzername = benutzername;
        this.passwort = passwort;
    }

    public Benutzer(String id, String name, String nachname, String telefonnummer, String geschlecht, Date geburtsdatum, String adresse, String benutzername, String passwort, String rolle){
        super(id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse);

        //ich könnte für benutzername und passwort check-Funktionen
        //schreiben
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.rolle = rolle;
    }

    public Benutzer(String id, String rolle, String benutzername, String passwort) {
        super(id);

        this.benutzername = benutzername;
        this.passwort = passwort;
        this.rolle = rolle;
    }


    public boolean checkRolle(String rolle){
        return true;
    }

    public void setRolle(String rolle){
        this.rolle = rolle;
    }

    public String getRolle(){
        return rolle;
    }

    public void setBenutzername(String bname){
        this.benutzername = bname;
    }

    public String getBenutzername(){
        return benutzername;
    }

    public void setPasswort(String passwort){
        this.passwort = passwort;
    }

    public String getPasswort(){
        return passwort;
    }

    public boolean changePasswort(String p){
        //
        return true;
    }

    public boolean changeBenutzername(String bn){
        //
        return true;
    }

    @Override
    public String toString(){
        //
        return " ";
    }
}
