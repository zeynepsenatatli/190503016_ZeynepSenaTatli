package com.zst.autovermietung;
import java.io.IOException;
import java.sql.*;
import java.util.Set;


public class DBautovermietung {
    public  static Connection conn = null;


    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:C:\\Users\\zst\\IdeaProjects\\Autovermietung\\src\\main\\java\\com\\zst\\autovermietung\\autovermietung.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM Person");
            if(res.next()){
                System.out.println("username: "+res.getString("Name")+" Pass: "+res.getString("Nachname"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
