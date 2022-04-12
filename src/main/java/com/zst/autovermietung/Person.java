package com.zst.autovermietung;

import java.util.*;

public abstract class Person{

    private String tr_id;
    private String name;
    private String nachname;
    private String telefonnummer;
    private String geschlecht;
    private Date geburtsdatum;
    //alter kann durch geburtsdatum berechnet werden, ohne dass am
    //Anfang gegeben ist
    private String adresse;


    public Person(String id, String name, String nachname, String telefonnummer, String geschlecht, Date geburtsdatum, String adresse){
        if(checkId(id)){
            this.tr_id = id;
        }else{
            //
        }
        this.name = name;
        this.nachname = nachname;
        this.telefonnummer = telefonnummer;
        this.geschlecht = geschlecht;
        this.geburtsdatum = geburtsdatum;
        this.adresse = adresse;

    }

    public boolean checkId(String id) {
        if(id.length() == 11 && id.matches("[0-9]+")){
            return true;
        }else{
            return false;
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setNachname(String nname){
        this.nachname = nname;
    }

    public String getNachname(){
        return nachname;
    }

    public void setId(String id){
        if(checkId(id)){
            this.tr_id = id;
        }else{

        }

    }

    public String getId(){
        return tr_id;
    }

    public void setTelefonnummer(String tnummer){
        //checkNummer
        this.telefonnummer = tnummer;
    }

    public String getTelefonnummer(){
        return telefonnummer;
    }

    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    public String getAdresse(){
        return adresse;
    }

    public void setGeburtsdatum(Date gdatum){
        this.geburtsdatum = gdatum;
    }

    public Date getGeburtsdatum(){
        return geburtsdatum;
    }

    public void setGeschlecht(String g){
        this.geschlecht = g;
    }

    public String getGeschlecht(){
        return geschlecht;
    }

    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        Person p = (Person) o;
        if(this.tr_id.equals(p.getId())){
            return true;
        }else {
            return false;
        }
    }
}