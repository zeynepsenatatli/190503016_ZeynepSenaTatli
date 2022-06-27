package com.zst.autovermietung;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBautovermietung {
    public  static Connection conn = null;
    public  static Statement stmt;

    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:C:\\Users\\zst\\IdeaProjects\\Autovermietung\\src\\main\\java\\com\\zst\\autovermietung\\autovermietung.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            stmt = conn.createStatement();
            /*ResultSet res = stmt.executeQuery("SELECT * FROM Person");
            if(res.next()){
                System.out.println("username: "+res.getString("Name")+" Pass: "+res.getString("Nachname"));
            }*/

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Benutzer b;
    public static boolean sucheBenutzer(String benutzername, String passwort) {
        try {
            PreparedStatement stmt = DBautovermietung.conn.prepareStatement("SELECT * FROM Benutzer WHERE benutzername=? AND passwort=?");
            stmt.setString(1,benutzername);
            stmt.setString(2,passwort);
            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()) {
                String id = resultSet.getString("trID");
                String rolle = resultSet.getString("rolle");
                b = new Benutzer(id, rolle, benutzername, passwort);
                getThisBenutzer();
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static Benutzer getThisBenutzer() {

        if(b.getRolle().equals("Mitarbeiter")) {
            return getEineMitarbeiter(b.getId());
        }else if(b.getRolle().equals("Manager")){
            return getManager(b.getId());
        }
        return null;
    }

    public static void updateBenutzername(String id, String bn) {

        String bname = "UPDATE Benutzer SET benutzername = '" + bn + "' WHERE trID = '" + id +"'";

        try {
            Statement stm = conn.createStatement();
            stm.execute(bname);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updatePasswort(String id, String pass) {

        String passwort = "UPDATE Benutzer SET passwort = '" + pass + "' WHERE trID = '" + id +"'";

        try {
            Statement stm = conn.createStatement();
            stm.execute(passwort);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Kunde> getKunden() {
        ArrayList<Kunde> kunden = new ArrayList<>();
        SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Statement stmt = DBautovermietung.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Kunde");
            while(rs.next()) {
                String tr_id = rs.getString("TrId");
                String name = rs.getString("Name");
                String nachname = rs.getString("Nachname");
                String sgeburtsdatum = rs.getString("Geburtsdatum");
                Date geburtsdatum = (Date) dformat.parse(sgeburtsdatum);
                String geschlecht = rs.getString("Geschlecht");
                String telefonnummer = rs.getString("Telefonnummer");
                String adresse = rs.getString("Adress");
                String sfuhrerschein = rs.getString("DatumVonFuhrerschein");
                Date datumVonFuhrerschein = (Date) dformat.parse(sfuhrerschein);
                int v = rs.getInt("Vorstrafen");
                boolean vorstrafen =false;
                if(v == 1) {
                    vorstrafen = true;
                }else {
                    vorstrafen = false;
                }
                String vorstrafen_note = rs.getString("VorstrafenNote");
                int h = rs.getInt("HatAuto");
                boolean hat = false;
                if(h == 1) {
                    hat = true;
                }else {
                    hat = false;
                }

                Kunde kunde = new Kunde(tr_id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse, datumVonFuhrerschein, vorstrafen, vorstrafen_note, hat);
                kunden.add(kunde);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return kunden;
    }

    public static boolean checkKunde(String id) {
        try {
            PreparedStatement stmt = DBautovermietung.conn.prepareStatement("SELECT * FROM Kunde WHERE TrId=?");
            stmt.setString(1,id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static void addKunde(Kunde k) {

        String sql = "INSERT INTO Kunde (TrId, Name, Nachname, Geburtsdatum, K_Alter, Geschlecht, Telefonnummer, Adress, DatumVonFuhrerschein) VALUES(?,?,?,?,?,?,?,?,?)";
        SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, k.getId());
            ps.setString(2, k.getName());
            ps.setString(3, k.getNachname());
            String gdatum = dformat.format(k.getGeburtsdatum());
            ps.setString(4, gdatum);
            ps.setString(5, Integer.toString(k.getAlter()));
            ps.setString(6, k.getGeschlecht());
            ps.setString(7, k.getTelefonnummer());
            ps.setString(8, k.getAdresse());
            String fdatum = dformat.format(k.getDatumVonFuehrerschein());
            ps.setString(9, fdatum);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void removeKunde(Kunde kunde) {
        String sql = "DELETE FROM Kunde WHERE TrId=" + kunde.getId();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateKunde(Kunde kunde) {


        String adress = "UPDATE Kunde SET Adress = '" +kunde.getAdresse() + "' WHERE TrId =  " + kunde.getId();
        String telefonnummer = "UPDATE Kunde SET Telefonnummer ='"+ kunde.getTelefonnummer() + "' WHERE TrId=" + kunde.getId();
        String vorstrafen = "UPDATE Kunde SET VorstrafenNote = '"+ kunde.getVorstrafeNote() + "' WHERE TrId=" + kunde.getId();
        String name = "UPDATE Kunde SET Name= '" + kunde.getName() + "' WHERE TrId=" + kunde.getId();
        String nachname = "UPDATE Kunde SET Nachname= '" + kunde.getNachname()+ "' WHERE TrId=" + kunde.getId();
        String bool;
        if(kunde.getVorstrafe()==false) {
            bool = "UPDATE Kunde SET Vorstrafen= '" + 0 + "' WHERE TrId=" + kunde.getId();
        }else {
            bool = "UPDATE Kunde SET Vorstrafen= '" + 1 + "' WHERE TrId=" + kunde.getId();
        }


        try {
            Statement statement = conn.createStatement();
            statement.execute(adress);
            statement.execute(telefonnummer);
            statement.execute(vorstrafen);
            statement.execute(name);
            statement.execute(nachname);
            statement.execute(bool);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Auto> getAuto() {
        ArrayList<Auto> autos = new ArrayList<>();
        try {
            Statement stmt = DBautovermietung.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Autos");
            while(rs.next()) {
                String nummernschild= rs.getString("Nummernschild");
                String marke = rs.getString("Marke");
                String modell = rs.getString("Modell");
                int kilometerstand = rs.getInt("Kilometerstand");
                String getriebetyp = rs.getString("Getriebetyp");
                String farbe = rs.getString("Farbe");
                float mietpreise = rs.getFloat("Mietpreise");
                String baujahr = rs.getString("Baujahr");
                boolean istVerfuegbar = false;
                if(rs.getInt("istVerfuegbar") == 1) {
                    istVerfuegbar = true;
                }else if(rs.getInt("istVerfuegbar") == 0) {
                    istVerfuegbar = false;
                }
                Auto auto = new Auto(nummernschild, kilometerstand, marke, modell, farbe, baujahr, istVerfuegbar, mietpreise, getriebetyp);
                autos.add(auto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autos;
    }

    public static boolean checkAuto(String ns) {
        try {
            PreparedStatement stmt = DBautovermietung.conn.prepareStatement("SELECT * FROM Autos WHERE Nummernschild=?");
            stmt.setString(1,ns);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void addAuto(Auto a) {

        String sql = "INSERT INTO Autos (Nummernschild, Marke, Modell, Kilometerstand, Getriebetyp, Farbe, Mietpreise, Baujahr, istVerfuegbar) VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getNummernschild());
            ps.setString(2, a.getMarke());
            ps.setString(3, a.getModell());
            ps.setInt(4, a.getKilometerstand());
            ps.setString(5, a.getGetriebetyp());
            ps.setString(6, a.getFarbe());
            ps.setFloat(7, a.getMietpreise());
            ps.setString(8, a.getBaujahr());

            int b = 0;
            if(a.checkVerfuegbarkeit() == true) {
                b = 1;
            }else {
                b = 0;
            }
            ps.setInt(9, b);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeAuto(Auto auto) {
        String sql = "DELETE FROM Autos WHERE Nummernschild='" + auto.getNummernschild()+"'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAuto(Auto auto) {

        String farbe = "UPDATE Autos SET Farbe = '" + auto.getFarbe() + "' WHERE Nummernschild = '" + auto.getNummernschild()+"'";
        String km = "UPDATE Autos SET Kilometerstand =  '"+auto.getKilometerstand()+"'  WHERE Nummernschild = '" + auto.getNummernschild()+"'";
        String typ = "UPDATE Autos SET Getriebetyp = '" + auto.getGetriebetyp() + "' WHERE Nummernschild = '" + auto.getNummernschild()+"'";
        String preis = "UPDATE Autos SET Mietpreise =  '" +  auto.getMietpreise() + "'  WHERE Nummernschild = '" + auto.getNummernschild()+"'";

        String verfugbar;
        if(auto.checkVerfuegbarkeit()== true){
            verfugbar = "UPDATE Autos SET istVerfuegbar = '" + 1 + "' WHERE Nummernschild = '" + auto.getNummernschild()+"'";
        }else{
            verfugbar = "UPDATE Autos SET istVerfuegbar =  '" + 0 + "' WHERE Nummernschild = '" + auto.getNummernschild()+"'";
        }

        try {
            Statement stm = conn.createStatement();
            stm.execute(farbe);
            stm.execute(km);
            stm.execute(typ);
            stm.execute(preis);
            stm.execute(verfugbar);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAutoVerfugbar(String ns) {
        String verfugbar;

        for(Mietvertrag m : getMietvertrag() ) {
            if(m.getAuto().getNummernschild().equals(ns) && Mietvertrag.istAktuell(m.getEnddatum()) == true) {
                verfugbar = "UPDATE Autos SET istVerfuegbar = '" + 0 + "' WHERE Nummernschild = '" +ns+"'";
                try{
                    Statement stm = conn.createStatement();
                    stm.execute(verfugbar);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if(m.getAuto().getNummernschild().equals(ns) && Mietvertrag.istAktuell(m.getEnddatum()) == false){
                verfugbar = "UPDATE Autos SET istVerfuegbar =  '" + 1 + "' WHERE Nummernschild = '" + ns+"'";
                try{
                    Statement stm = conn.createStatement();
                    stm.execute(verfugbar);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateKundeStatus(String id) {
        String hatAuto;
        Mietvertrag m2 = null;
        for(Mietvertrag m : getMietvertrag()) {
            if(m.getKunde().getId().equals(id) && Mietvertrag.istAktuell(m.getEnddatum()) == true) {
                hatAuto = "UPDATE Kunde SET HatAuto = '" + 1 + "' WHERE TrId = '" +id+"'";
                try{
                    Statement stm = conn.createStatement();
                    stm.execute(hatAuto);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if(m.getKunde().getId().equals(id) && Mietvertrag.istAktuell(m.getEnddatum())== false) {
                hatAuto = "UPDATE Kunde SET HatAuto =  '" + 0 + "' WHERE TrId = '" + id+"'";
                try{
                    Statement stm = conn.createStatement();
                    stm.execute(hatAuto);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateRemoveMietvertragKunde(String id) {
        String hatAuto = "UPDATE Kunde SET HatAuto =  '" + 0 + "' WHERE TrId = '" + id+"'";
        try{
            Statement stm = conn.createStatement();
            stm.execute(hatAuto);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRemoveMietvertragAuto(String ns) {
        String verfugbar = "UPDATE Autos SET istVerfuegbar =  '" + 1 + "' WHERE Nummernschild = '" + ns+"'";
        try{
            Statement stm = conn.createStatement();
            stm.execute(verfugbar);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Auto getEineAuto(String ns) {
        Auto auto = null;
        try{
            Statement stmt = DBautovermietung.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Autos WHERE Nummernschild = '" + ns +"'");
            while(rs.next()) {
                String marke = rs.getString("Marke");
                String modell = rs.getString("Modell");
                int kilometerstand = rs.getInt("Kilometerstand");
                String getriebetyp = rs.getString("Getriebetyp");
                String farbe = rs.getString("Farbe");
                float mietpreise = rs.getFloat("Mietpreise");
                String baujahr = rs.getString("Baujahr");
                boolean istVerfuegbar = false;
                if(rs.getInt("istVerfuegbar") == 1) {
                    istVerfuegbar = true;
                }else if(rs.getInt("istVerfuegbar") == 0) {
                    istVerfuegbar = false;
                }
                auto = new Auto(ns, kilometerstand, marke, modell, farbe, baujahr, istVerfuegbar, mietpreise, getriebetyp);
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return auto;
    }

    public static Kunde getEineKunde(String id) {

        SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Statement stmt = DBautovermietung.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Kunde WHERE TrId = '" + id +"'");

            while(rs.next()) {

                String name = rs.getString("Name");
                String nachname = rs.getString("Nachname");
                String sgeburtsdatum = rs.getString("Geburtsdatum");
                Date geburtsdatum = (Date) dformat.parse(sgeburtsdatum);
                String geschlecht = rs.getString("Geschlecht");
                String telefonnummer = rs.getString("Telefonnummer");
                String adresse = rs.getString("Adress");
                String sfuhrerschein = rs.getString("DatumVonFuhrerschein");
                Date datumVonFuhrerschein = (Date) dformat.parse(sfuhrerschein);
                int v = rs.getInt("Vorstrafen");
                boolean vorstrafen =false;
                if(v == 1) {
                    vorstrafen = true;
                }else {
                    vorstrafen = false;
                }
                String vorstrafen_note = rs.getString("VorstrafenNote");

                int h = rs.getInt("HatAuto");
                boolean hat = false;
                if(h == 1) {
                    hat = true;
                }else {
                    hat = false;
                }
                Kunde kunde = new Kunde(id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse, datumVonFuhrerschein, vorstrafen, vorstrafen_note, hat);

                return kunde;
            }

        }catch(SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Mitarbeiter getEineMitarbeiter(String id) {
        Mitarbeiter mitarbeiter = null;
        SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Statement stmt = DBautovermietung.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Mitarbeiter, Benutzer WHERE Mitarbeiter.TrId = Benutzer.trID AND Benutzer.trID =  '" + id +"'");

            while (rs.next()) {
                String name = rs.getString("Name");
                String nachname = rs.getString("Nachname");
                String sgeburtsdatum = rs.getString("Geburtsdatum");
                Date geburtsdatum = (Date) dformat.parse(sgeburtsdatum);
                String geschlecht = rs.getString("Geschlecht");
                String telefonnummer = rs.getString("Telefonnummer");
                String adresse = rs.getString("Adress");
                String bname = rs.getString("benutzername");
                String pass = rs.getString("passwort");
                String rolle = rs.getString("rolle");

                mitarbeiter = new Mitarbeiter(id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse, bname, pass, rolle);
            }

        }catch(SQLException | ParseException e) {
            e.printStackTrace();
        }
        return mitarbeiter;
    }

    public static Manager getManager(String id) {
        Manager manager = null;
        SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Statement stmt = DBautovermietung.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Manager, Benutzer WHERE Manager.TrId = Benutzer.trID AND Benutzer.trID =  '" + id +"'");

            while (rs.next()) {
                String name = rs.getString("Name");
                String nachname = rs.getString("Nachname");
                String sgeburtsdatum = rs.getString("Geburtsdatum");
                Date geburtsdatum = (Date) dformat.parse(sgeburtsdatum);
                String geschlecht = rs.getString("Geschlecht");
                String telefonnummer = rs.getString("Telefonnummer");
                String adresse = rs.getString("Adress");
                String bname = rs.getString("benutzername");
                String pass = rs.getString("passwort");
                String rolle = rs.getString("rolle");

                manager = new Manager(id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse, bname, pass, rolle);
            }

        }catch(SQLException | ParseException e) {
            e.printStackTrace();
        }
        return manager;
    }

    public static ArrayList<Mietvertrag> getMietvertrag() {
        ArrayList<Mietvertrag> mvertrage = new ArrayList<>();
        SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Statement stmt = DBautovermietung.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Mietvertrag");
            while(rs.next()) {
                String vId= rs.getString("Vertrag_Id");
                String s = rs.getString("Startdatum");
                Date sdatum = (Date) dformat.parse(s);
                String e = rs.getString("Enddatum");
                Date edatum = (Date) dformat.parse(e);
                float miete = rs.getFloat("Miete");
                String problem = rs.getString("Problembezeichnung");
                String nschild = rs.getString("AutoNummernschild");
                String kundeId = rs.getString("KundeTrId");
                String verantwortlich = rs.getString("VerantwortlicheMitarbeiter");

                boolean bezahlt = false;
                if(rs.getInt("istBezahlt") == 1) {
                    bezahlt = true;
                }else if(rs.getInt("istBezahlt") == 0) {
                    bezahlt = false;
                }

                Mietvertrag mv = new Mietvertrag(vId, sdatum, edatum, getEineAuto(nschild), getEineKunde(kundeId), getEineMitarbeiter(verantwortlich), miete, bezahlt, problem);
                mvertrage.add(mv);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mvertrage;
    }

    public static void addMietvertrag( Date s, Date e, String auto, String kunde, float miete, String mitarbeiter) {

        String sql = "INSERT INTO Mietvertrag (Vertrag_Id, Startdatum, Enddatum, Miete, istBezahlt, Problembezeichnung, Autonummernschild, KundeTrId, VerantwortlicheMitarbeiter) VALUES(?,?,?,?,?,?,?,?,?)";
        Mietvertrag m = new Mietvertrag(Integer.toString(Mietvertrag.mietvertragAnzahl), s, e, getEineAuto(auto), getEineKunde(kunde), getEineMitarbeiter(mitarbeiter), miete, false, "-");
        SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Integer.toString(Mietvertrag.mietvertragAnzahl));
            String sdatum = dformat.format(m.getStartdatum());
            ps.setString(2, sdatum);
            String edatum = dformat.format(m.getEnddatum());
            ps.setString(3, edatum);
            ps.setFloat(4, m.getMiete());

            int b = 0;
            if(m.getBezahlStatus() == true) {
                b = 1;
            }else {
                b = 0;
            }
            ps.setInt(5, b);

            String kk = "-";
            ps.setString(6, kk);
            ps.setString(7, auto);
            ps.setString(8, kunde);
            ps.setString(9, mitarbeiter);

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void removeMietvertrag(Mietvertrag m) {
        String sql = "DELETE FROM Mietvertrag WHERE Vertrag_Id='" + m.getVertraId()+"'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateMietvertrag(Mietvertrag mvertrag) {

        String problem = "UPDATE Mietvertrag SET Problembezeichnung =  '" +  mvertrag.getProblembezeichnung() + "'  WHERE Vertrag_Id = '" + mvertrag.getVertraId()+"'";

        String bezahlt;
        if(mvertrag.getBezahlStatus()== true){
            bezahlt = "UPDATE Mietvertrag SET istBezahlt = '" + 1 + "' WHERE Vertrag_Id = '" + mvertrag.getVertraId() +"'";
        }else{
            bezahlt = "UPDATE Mietvertrag SET istBezahlt =  '" + 0 + "' WHERE Vertrag_Id = '" + mvertrag.getVertraId()+"'";
        }
        try {
            Statement stm = conn.createStatement();
            stm.execute(problem);
            stm.execute(bezahlt);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
