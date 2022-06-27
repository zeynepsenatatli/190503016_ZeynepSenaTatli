package com.zst.autovermietung;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
    public static int mietvertragAnzahl = 0;

    public Mietvertrag(String id, Date startdatum, Date enddatum, Auto auto, Kunde kunde, Mitarbeiter mitarbeiter){
        this.vertrag_id = id;
        this.startdatum = startdatum;
        this.enddatum = enddatum;
        this.auto = auto;
        this.kunde = kunde;
        this.verantwortlicheMitarbeiter = mitarbeiter;
        mietvertragAnzahl++;

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
        mietvertragAnzahl++;
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

    SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
    public String getStartdatumString() {
        String fdatum = dformat.format(startdatum);
        return fdatum;
    }

    public void setEnddatum(Date end){
        this.enddatum = end;
    }

    public Date getEnddatum(){
        return enddatum;
    }

    public String getEnddatumString() {
        String fdatum = dformat.format(enddatum);
        return fdatum;
    }

    public static boolean istAktuell(Date edatum) {
        LocalDate ed = edatum.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        int compareValue = LocalDate.now().compareTo(ed);

        if (compareValue < 0) {
            return true;
        } else {
            return false;
        }
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

    public static float berechneMiete(Date s, Date e, float miete) {

        LocalDate slocal = s.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate elocal = e.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        long noOfDaysBetween = ChronoUnit.DAYS.between(slocal, elocal);

        return noOfDaysBetween*miete;
    }

}