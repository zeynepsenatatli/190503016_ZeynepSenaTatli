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

public class KundeAddScreenController implements Initializable {

    @FXML
    private ChoiceBox geschlechtChoice;

    @FXML
    private TextArea adresseText;

    @FXML
    private DatePicker austellungsdatumPicker;

    @FXML
    private DatePicker geburtsdatumPicker;

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

    public void addKundeButton() throws ParseException, IOException {

        String id = trIdText.getText();
        String name = nameText.getText();
        String nachname = nachnameText.getText();
        Date geburtsdatum = new SimpleDateFormat("yyyy-MM-dd").parse(geburtsdatumPicker.getValue().toString());
        String geschlecht = geschlechtChoice.getValue().toString();
        String telefonnummer = telefonnummerText.getText();
        //System.out.println(telefonnummer);
        String adresse = adresseText.getText();
        Date ausstellungsdatum = new SimpleDateFormat("yyyy-MM-dd").parse(austellungsdatumPicker.getValue().toString());

        if(DBautovermietung.checkKunde(id)) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();

        }else{
            DBautovermietung.addKunde(id, name, nachname, geburtsdatum, geschlecht, telefonnummer, adresse, ausstellungsdatum);
            KundeScreenController.ksc.kundeList();
        }
    }
}
