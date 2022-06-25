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

    public static boolean sucheBenutzer(String benutzername, String passwort) {
        try {
            PreparedStatement stmt = DBautovermietung.conn.prepareStatement("SELECT * FROM Benutzer WHERE benutzername=? AND passwort=?");
            stmt.setString(1,benutzername);
            stmt.setString(2,passwort);
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
                boolean vorstrafen = rs.getBoolean("Vorstrafen");
                String vorstrafen_note = rs.getString("VorstrafenNote");

                Kunde kunde = new Kunde(tr_id, name, nachname, telefonnummer, geschlecht, geburtsdatum, adresse, datumVonFuhrerschein, vorstrafen, vorstrafen_note);
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


        try {
            Statement statement = conn.createStatement();
            statement.execute(adress);
            statement.execute(telefonnummer);
            statement.execute(vorstrafen);
            statement.execute(name);
            statement.execute(nachname);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
