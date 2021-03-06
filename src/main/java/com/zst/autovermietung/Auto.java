package com.zst.autovermietung;

public class Auto{
    private String nummernschild;
    private String marke;
    private String modell;
    private int kilometerstand;
    private String farbe;
    private String baujahr;
    private float miet_preise;
    private boolean istVerfuegbar;
    private String getriebetyp;

    public int auto_anzahl;

    public Auto(String nummernschild, int km, String marke, String modell, String farbe, String baujahr, String getriebetyp){

        if(checkNummernschild(nummernschild)){
            this.nummernschild = nummernschild;
        }else{
            //
        }
        this.kilometerstand = km;
        this.marke = marke;
        this.modell = modell;
        this.farbe = farbe;
        this.baujahr = baujahr;
        this.getriebetyp = getriebetyp;

        auto_anzahl++;
    }

    public Auto(String nummernschild, int km, String marke, String modell, String farbe, String baujahr, boolean verfuegbar, String getriebetyp){

        if(checkNummernschild(nummernschild)){
            this.nummernschild = nummernschild;
        }else{
            //
        }
        this.kilometerstand = km;
        this.marke = marke;
        this.modell = modell;
        this.farbe = farbe;
        this.baujahr = baujahr;
        this.istVerfuegbar = verfuegbar;
        this.getriebetyp = getriebetyp;

        auto_anzahl++;
    }
    public Auto(String nummernschild, int km, String marke, String modell, String farbe, String baujahr, boolean verfuegbar, float mietpreise, String getriebetyp){

        if(checkNummernschild(nummernschild)){
            this.nummernschild = nummernschild;
        }else{
            //
        }
        this.kilometerstand = km;
        this.marke = marke;
        this.modell = modell;
        this.farbe = farbe;
        this.baujahr = baujahr;
        this.istVerfuegbar = verfuegbar;
        this.miet_preise = mietpreise;
        this.getriebetyp = getriebetyp;

        auto_anzahl++;
    }

    public static boolean checkNummernschild(String n){
        String a[] = n.split(" ");

        if(a[0].length() == 2 && a[0].matches("[0-9]+") && a[1].matches("[A-Z]+") && a[2].matches("[0-9]+")){
            return true;
        }else {
            return false;
        }
    }
    public void setNummernschild(String n){
        this.nummernschild = n;
    }

    public String getNummernschild(){
        return nummernschild;
    }

    public void setMarke(String marke){
        this.marke = marke;
    }

    public String getMarke(){
        return marke;
    }

    public void setModell(String modell){
        this.modell = modell;
    }

    public String getModell(){
        return modell;
    }

    public void setFarbe(String farbe){
        this.farbe = farbe;
    }

    public String getFarbe(){
        return farbe;
    }

    public void setBaujahr(String baujahr){
        this.baujahr = baujahr;
    }

    public String getBaujahr(){
        return baujahr;
    }

    public void setGetriebetyp(String typ) {
        this.getriebetyp = typ;
    }

    public String getGetriebetyp() { return getriebetyp; }

    public void setKilometerstand(int km){
        this.kilometerstand = km;
    }

    public int getKilometerstand(){
        return kilometerstand;
    }

    public void setMietpreise(float m_preise){
        this.miet_preise = m_preise;
    }

    public float getMietpreise(){
        return miet_preise;
    }

    public void setVerfuegbarkeit(boolean verfuegbarkeit){
        this.istVerfuegbar = verfuegbarkeit;
    }

    public boolean checkVerfuegbarkeit(){
        return istVerfuegbar;
    }

    @Override
    public String toString(){
        //
        return " ";
    }

    @Override
    public boolean equals(Object o){
        return true;
    }

}
