package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsManagerController implements Initializable {

    @FXML
    private TextArea adressField;

    @FXML
    private Label adressLabel;

    @FXML
    private PasswordField benutzernameField;

    @FXML
    private Button bnButton;

    @FXML
    private Label bnLabel;

    @FXML
    private Label bnLabel1;

    @FXML
    private AnchorPane bnpane;

    @FXML
    private AnchorPane firstHomeScreen;

    @FXML
    private Label gdLabel;

    @FXML
    private Label gdLabel1;

    @FXML
    private Label geschlechtLabel;

    @FXML
    private Label geschlechtLabel1;

    @FXML
    private Label idLabel;

    @FXML
    private Label idLabel1;

    @FXML
    private TextField nachnameField;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel;

    @FXML
    private AnchorPane paneInfos;

    @FXML
    private AnchorPane paneInfos1;

    @FXML
    private Button passButton;

    @FXML
    private PasswordField passField;

    @FXML
    private AnchorPane passPane;

    @FXML
    private Label rolleLabel;

    @FXML
    private Label rolleLabel1;

    @FXML
    private TextField telefonField;

    @FXML
    private Label telefonnummerLabel;

    private Mitarbeiter m;
    private Manager manager;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(DBautovermietung.getThisBenutzer().getRolle().equals("Mitarbeiter")) {
            m = (Mitarbeiter) DBautovermietung.getThisBenutzer();
        }else {
            manager = (Manager) DBautovermietung.getThisBenutzer();
        }

        setInfos();
    }

    public void setInfos() {

        if(DBautovermietung.getThisBenutzer().getRolle().equals("Mitarbeiter")) {
            nameLabel.setText(m.getName()+ " " + m.getNachname());
            rolleLabel.setText(m.getRolle());
            telefonnummerLabel.setText(m.getTelefonnummer());
            idLabel.setText(m.getId());
            adressLabel.setText(m.getAdresse());
            geschlechtLabel.setText(m.getGeschlecht());
            gdLabel.setText(m.getGeburtsdatumString());
            bnLabel.setText(m.getBenutzername());
            passPane.setVisible(false);
            bnpane.setVisible(false);

            if(m.getRolle().equals("Mitarbeiter")) {
                bnButton.setVisible(false);
            }

            nameField.setText(m.getName());
            nachnameField.setText(m.getNachname());
            rolleLabel1.setText(m.getRolle());
            telefonField.setText(m.getTelefonnummer());
            idLabel1.setText(m.getId());
            adressField.setText(m.getAdresse());
            geschlechtLabel1.setText(m.getGeschlecht());
            gdLabel1.setText(m.getGeburtsdatumString());
            bnLabel1.setText(m.getBenutzername());
        }else {
            nameLabel.setText(manager.getName()+ " " + manager.getNachname());
            rolleLabel.setText(manager.getRolle());
            telefonnummerLabel.setText(manager.getTelefonnummer());
            idLabel.setText(manager.getId());
            adressLabel.setText(manager.getAdresse());
            geschlechtLabel.setText(manager.getGeschlecht());
            gdLabel.setText(manager.getGeburtsdatumString());
            bnLabel.setText(manager.getBenutzername());
            passPane.setVisible(false);
            bnpane.setVisible(false);


            nameField.setText(manager.getName());
            nachnameField.setText(manager.getNachname());
            rolleLabel1.setText(manager.getRolle());
            telefonField.setText(manager.getTelefonnummer());
            idLabel1.setText(manager.getId());
            adressField.setText(manager.getAdresse());
            geschlechtLabel1.setText(manager.getGeschlecht());
            gdLabel1.setText(manager.getGeburtsdatumString());
            bnLabel1.setText(manager.getBenutzername());
        }

    }

    public void benutzernameAndern(ActionEvent event) {
        bnpane.setVisible(true);
    }

    public void saveBenutzername() throws IOException {
        String bname = benutzernameField.getText();

        if(bname.isEmpty()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Bitte geben Sie neue Benutzername ein!");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();
        }else {
            if(DBautovermietung.getThisBenutzer().getRolle().equals("Mitarbeiter")) {
                DBautovermietung.updateBenutzername(m.getId(), bname);
            }else {
                DBautovermietung.updateBenutzername(manager.getId(),bname);
            }

        }
    }

    public void editInfos() {
        paneInfos.setVisible(false);
        paneInfos1.setVisible(true);
    }



    public void passwortAndern(ActionEvent event) {
        passPane.setVisible(true);
    }

    public void updateBenutzer(ActionEvent event) {

        String name = nameField.getText();
        String nachname = nachnameField.getText();
        String adress = adressField.getText();
        String telefon = telefonField.getText();

        if(DBautovermietung.getThisBenutzer().getRolle().equals("Mitarbeiter")) {
            m.setName(name);
            m.setNachname(nachname);
            m.setTelefonnummer(telefon);
            m.setAdresse(adress);

            DBautovermietung.updateMitarbeiter(m);
        }else {
            manager.setName(name);
            manager.setNachname(nachname);
            manager.setTelefonnummer(telefon);
            manager.setAdresse(adress);
            DBautovermietung.updateManager(manager);
        }
        setInfos();
        paneInfos1.setVisible(false);
        paneInfos.setVisible(true);
    }

    public void savePasswort() throws IOException {
        String passwort = passField.getText();

        if(passwort.isEmpty()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Bitte geben Sie neue Passwort ein!");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();
        }else {
            if(DBautovermietung.getThisBenutzer().getRolle().equals("Mitarbeiter")) {
                DBautovermietung.updatePasswort(m.getId(), passwort);
            }else {
                DBautovermietung.updatePasswort(manager.getId(), passwort);
            }

        }
    }
}
