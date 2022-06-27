package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private AnchorPane screen;

    @FXML
    private Label usernameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader firsthomeScreen = new FXMLLoader(HelloApplication.class.getResource("first-home-screen.fxml"));
        screen.getChildren().removeAll();
        try {
            screen.getChildren().add(firsthomeScreen.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        usernameLabel.setText(DBautovermietung.getThisBenutzer().getName() + " " + DBautovermietung.getThisBenutzer().getNachname());
    }

    public void autoVerwaltung() throws IOException {
        if(DBautovermietung.getThisBenutzer().getRolle().equals("Manager")) {
            FXMLLoader autoScreen = new FXMLLoader(HelloApplication.class.getResource("auto-screen.fxml"));
            screen.getChildren().removeAll();
            screen.getChildren().add(autoScreen.load());
        }else if(DBautovermietung.getThisBenutzer().getRolle().equals("Mitarbeiter")) {
            FXMLLoader autoScreen = new FXMLLoader(HelloApplication.class.getResource("auto-screen-fur-mitarbeiter.fxml"));
            screen.getChildren().removeAll();
            screen.getChildren().add(autoScreen.load());
        }
    }

    public void mietvertragVerwaltung() throws IOException {
        if(DBautovermietung.getThisBenutzer().getRolle().equals("Manager")) {
            FXMLLoader mietvertragScreen = new FXMLLoader(HelloApplication.class.getResource("mietvertrag-screen-fur-manager.fxml"));
            screen.getChildren().removeAll();
            screen.getChildren().add(mietvertragScreen.load());
        }else {
            FXMLLoader mietvertragScreen = new FXMLLoader(HelloApplication.class.getResource("mietvertrag-screen.fxml"));
            screen.getChildren().removeAll();
            screen.getChildren().add(mietvertragScreen.load());
        }
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

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void logOut(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("login-page.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void settingButton() throws IOException {
        FXMLLoader managersettings = new FXMLLoader(HelloApplication.class.getResource("settings-manager.fxml"));
        screen.getChildren().removeAll();
        screen.getChildren().add(managersettings.load());

    }


}
