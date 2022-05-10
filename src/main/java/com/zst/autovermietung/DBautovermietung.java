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

}
