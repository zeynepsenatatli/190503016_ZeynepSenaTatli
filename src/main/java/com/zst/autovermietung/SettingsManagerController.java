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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        m = (Mitarbeiter) DBautovermietung.getThisBenutzer();

        setInfos();
    }

    public void setInfos() {
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
            DBautovermietung.updateBenutzername(m.getId(), bname);
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

        m.setName(name);
        m.setNachname(nachname);
        m.setTelefonnummer(telefon);
        m.setAdresse(adress);

        if(m.getRolle().equals("Mitarbeiter")) {
            DBautovermietung.updateMitarbeiter(m);
        }else {
            DBautovermietung.updateManager(DBautovermietung.getManager(m.getId()));
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
            DBautovermietung.updatePasswort(m.getId(), passwort);
        }
    }
}
