package com.zst.autovermietung;

import java.sql.*;


public class DBautovermietung {
    public  static Connection conn = null;
    public static Statement stmt;

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

    /*public ObservableList<Kunde> getKunde() throws SQLException {
        ResultSet kundenList = stmt.executeQuery("SELECT * FROM Kunde");
        ObservableList<Kunde> kunden = FXCollections.observableArrayList();
        if(kundenList.next()) {
            java.util.Date gd = new java.util.Date(kundenList.getString("Geburtsdatum"));
            java.util.Date fschein = new java.util.Date(kundenList.getString("Geburtsdatum"));
            Kunde kunde = new Kunde(kundenList.getString("TrId"), kundenList.getString("Name"), kundenList.getString("Nachname"),
                    kundenList.getString("Telefonnummer"), kundenList.getString("Geschlecht"), gd, kundenList.getString("Adress"), fschein);
            kunden.add(kunde);
        }
        return kunden;
    }*/


}
