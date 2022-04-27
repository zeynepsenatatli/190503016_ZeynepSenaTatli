package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomePageController {

    @FXML
    private AnchorPane screen;

    public void autoVerwaltung() throws IOException {
        FXMLLoader autoScreen = new FXMLLoader(HelloApplication.class.getResource("auto-screen.fxml"));
        screen.getChildren().removeAll();
        screen.getChildren().add(autoScreen.load());
    }

    public void rechnungVerwaltung() throws IOException {
        FXMLLoader rechnungScreen = new FXMLLoader(HelloApplication.class.getResource("rechnung-screen.fxml"));
        screen.getChildren().removeAll();
        screen.getChildren().add(rechnungScreen.load());
    }

    public void kundeVerwaltung() throws IOException {
        FXMLLoader kundeScreen = new FXMLLoader(HelloApplication.class.getResource("kunde-screen.fxml"));
        screen.getChildren().removeAll();
        screen.getChildren().add(kundeScreen.load());
    }

    public void firstHomePage() throws IOException {
        FXMLLoader firstHomeScreen = new FXMLLoader(HelloApplication.class.getResource("first-home-screen.fxml"));
        screen.getChildren().removeAll();
        screen.getChildren().add(firstHomeScreen.load());
    }
}
