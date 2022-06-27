package com.zst.autovermietung;

import java.util.Date;

public class Manager extends Benutzer{

    public Autovermietung leiterIn;

    public Manager(String id, String name, String nachname, String telefonnummer, String geschlecht, Date geburtsdatum, String adresse, String benutzername, String passwort){
        super(id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse, benutzername, passwort);

    }

    public Manager(String id, String name, String nachname, String telefonnummer, String geschlecht, Date geburtsdatum, String adresse, String benutzername, String passwort, String rolle){
        super(id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse, benutzername, passwort, rolle);

    }

}