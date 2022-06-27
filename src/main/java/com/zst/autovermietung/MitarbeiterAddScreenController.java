package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MitarbeiterAddScreenController implements Initializable {

    @FXML
    private ChoiceBox geschlechtChoice;

    @FXML
    private TextArea adresseText;

    @FXML
    private DatePicker geburtsdatumPicker;

    @FXML
    private TextField benutzernameField;

    @FXML
    private TextField passwortField;

    @FXML
    private TextField nachnameText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField telefonnummerText;

    @FXML
    private TextField trIdText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        geschlechtChoice.getItems().add("männlich");
        geschlechtChoice.getItems().add("weiblich");
    }

    public void addMitarbeiter() throws ParseException, IOException {

        String id = trIdText.getText();
        String name = nameText.getText();
        String nachname = nachnameText.getText();

        Date geburtsdatum = null;
        boolean gdexception = false;
        try {
            geburtsdatum = new SimpleDateFormat("yyyy-MM-dd").parse(geburtsdatumPicker.getValue().toString());
        } catch(NullPointerException e) {
            gdexception = true;
        }

        String geschlecht = null;
        boolean choice = false;
        try {
            geschlecht = geschlechtChoice.getValue().toString();
        } catch(NullPointerException e) {
            choice = true;
        }

        String telefonnummer = telefonnummerText.getText();
        String adresse = adresseText.getText();

        String benutzername = benutzernameField.getText();
        String passwort = passwortField.getText();

        if(id.isEmpty() || name.isEmpty() || nachname.isEmpty() || gdexception || choice
                || telefonnummer.isEmpty() || adresse.isEmpty() || benutzername.isEmpty() || passwort.isEmpty()) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Bitte geben Sie alle Informationen vollständig ein!");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();

        }else if(DBautovermietung.checkMitarbeiter(id)) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Dieser Mitarbeiter ist bereit im System gespeichert!");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();

        }else if(Mitarbeiter.checkId(id) == false) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Das Format von eingegebene TR-ID ist falsch.");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();

        }else if(Mitarbeiter.checkTelefon(telefonnummer) == false){

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Das Format von eingegebene Telefonnummer ist falsch.");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();


        }else {
             Mitarbeiter mitarbeiter = new Mitarbeiter(id, name, nachname, telefonnummer, geschlecht, geburtsdatum , adresse, benutzername, passwort,"Mitarbeiter");

             DBautovermietung.addMitarbeiter(mitarbeiter);
             MitarbeiterScreenController.mitarbeitersc.mitarbeiterListe();
        }
    }
}
