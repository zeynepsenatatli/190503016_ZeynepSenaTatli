package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsManagerController implements Initializable {

    @FXML
    private Label adressLabel;

    @FXML
    private PasswordField benutzernameField;

    @FXML
    private Label bnLabel;

    @FXML
    private AnchorPane bnpane;

    @FXML
    private AnchorPane firstHomeScreen;

    @FXML
    private Label gdLabel;

    @FXML
    private Label geschlechtLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private PasswordField passField;

    @FXML
    private AnchorPane passPane;

    @FXML
    private Label rolleLabel;

    @FXML
    private Label telefonnummerLabel;

    @FXML
    private Button passButton;

    @FXML
    private Button bnButton;

    private Benutzer m;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        m = DBautovermietung.getThisBenutzer();

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

    public void passwortAndern(ActionEvent event) {
        passPane.setVisible(true);
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
