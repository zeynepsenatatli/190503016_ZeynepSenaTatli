package com.zst.autovermietung;

import java.util.Date;

public class Mietvertrag{

    private String vertrag_id;
    private Date startdatum;
    private Date enddatum;
    private float miete;
    private boolean istBezahlt;
    private String problembezeichnung;
    private Auto auto;
    private Kunde kunde;
    private Mitarbeiter verantwortlicheMitarbeiter;

    public Mietvertrag(String id, Date startdatum, Date enddatum, Auto auto, Kunde kunde, Mitarbeiter mitarbeiter){
        this.vertrag_id = id;
        this.startdatum = startdatum;
        this.enddatum = enddatum;
        this.auto = auto;
        this.kunde = kunde;
        this.verantwortlicheMitarbeiter = mitarbeiter;
    }
    public Mietvertrag(String id, Date startdatum, Date enddatum, Auto auto, Kunde kunde, Mitarbeiter mitarbeiter, float miete, boolean istBezahlt, String problembezeichnung){
        this.vertrag_id = id;
        this.startdatum = startdatum;
        this.enddatum = enddatum;
        this.auto = auto;
        this.kunde = kunde;
        this.verantwortlicheMitarbeiter = mitarbeiter;
        this.miete = miete;
        this.istBezahlt = istBezahlt;
        this.problembezeichnung = problembezeichnung;
    }

    //private de olabilir
    public void berechneMiete(float taglicheMiete, Date start, Date end) {
        //miete =  (enddatum-startdatum)* auto.getMiete();
    }

    public void setVertragId(String id){
        this.vertrag_id = id;
    }

    public String getVertraId(){
        return vertrag_id;
    }

    public void setStartdatum(Date start){
        this.startdatum = start;
    }

    public Date getStartdatum(){
        return startdatum;
    }

    public void setEnddatum(Date end){
        this.enddatum = end;
    }

    public Date getEnddatum(){
        return enddatum;
    }

    public void setMiete(float miete){
        this.miete = miete;
    }

    public float getMiete(){
        return miete;
    }

    public void setBezahlStatus(boolean s){
        this.istBezahlt= s;
    }

    public boolean getBezahlStatus(){
        return istBezahlt;
    }

    public void setAuto(Auto auto){
        this.auto = auto;
    }

    public Auto getAuto(){
        return this.auto;
    }

    public void setKunde(Kunde kunde){
        this.kunde = kunde;
    }

    public Kunde getKunde(){
        return this.kunde;
    }

    public void setVerantwortlicheMitarbeiter(Mitarbeiter mitarbeiter){
        this.verantwortlicheMitarbeiter = mitarbeiter;
    }

    public Mitarbeiter getVerantwortlicheMitarbeiter(){
        return this.verantwortlicheMitarbeiter;
    }

    public void setProblembezeichnung(String problem){
        this.problembezeichnung = problem;
    }

    public String getProblembezeichnung(){
        return problembezeichnung;
    }
    //toString ?

}