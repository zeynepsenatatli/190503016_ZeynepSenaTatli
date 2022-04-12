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

        int index = findeKunde(id);

        return kunden.get(index);
    }

    public Auto getAuto(String nummernschild){
        // es soll eine Funktion heisst findeAuto und index
        // von Auto zurückgeben. hier wird diese Funktion
        // verwendet. danach kann getAuto richtige Auto
        //zurückgeben

        int index = findeAuto(nummernschild);
        return autos.get(index);
    }

    public Mietvertrag getVertraege(String id){
        // gleiche Gedanke wie oben

        int index = findeMietvertrag(id);
        return mietvertraege.get(index);
    }

    public Mietvertrag getVorgangeneVertraege(String id){
        // gleiche Gedanke wie oben

        int index = findeVorgegangeneMietvertrag(id);
        return vorgangeneMietvertraege.get(index);
    }

    public Mitarbeiter getMitarbeiter(String id){
        // gleiche Gedanke wie oben

        int index = findeMitarbeiter(id);
        return mitarbeiter.get(index);
    }

    public Manager getManager(){
        return manager;
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
        int index = -1;
        for(int i = 0; i < autos.size(); i++){
            if(autos.get(i).getNummernschild().equals(nummernschild)){
                index = i;
                i = autos.size();
            }
        }
        if(index < 0){
            // exception - Es gibt keine Auto mit diesem Nummernschild
        }
        return index;
    }

    public int findeKunde(String id){
        int index = -1;
        for(int i = 0; i < kunden.size(); i++){
            if(kunden.get(i).getId().equals(id)){
                index = i;
                i = kunden.size();
            }
        }
        if(index < 0){
            // exception - Es gibt keine Kunde mit diesem ID
        }
        return index;
    }

    public int findeMitarbeiter(String id){
        int index = -1;
        for(int i = 0; i < mitarbeiter.size(); i++){
            if(mitarbeiter.get(i).getId().equals(id)){
                index = i;
                i = mitarbeiter.size();
            }
        }
        if(index < 0){
            // exception - Es gibt keine Mitarbeiter mit diesem ID
        }
        return index;
    }

    public int findeMietvertrag(String id){
        int index = -1;
        for(int i = 0; i < mietvertraege.size(); i++){
            if(mietvertraege.get(i).getVertraId().equals(id)){
                index = i;
                i = mietvertraege.size();
            }
        }
        if(index < 0){
            // Warning- es gibt keine Mietvertrag mit diesem Vertrag-ID
        }
        return index;
    }

    public int findeVorgegangeneMietvertrag(String id){
        int index = -1;
        for(int i = 0; i < vorgangeneMietvertraege.size(); i++){
            if(vorgangeneMietvertraege.get(i).getVertraId().equals(id)){
                index = i;
                i = vorgangeneMietvertraege.size();
            }
        }
        if(index < 0){
            // Warning- es gibt keine Mietvertrag mit diesem Vertrag-ID
        }
        return index;
    }

}
