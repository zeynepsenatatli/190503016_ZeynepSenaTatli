package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    public void addKundeButton() throws ParseException {

        String id = trIdText.getText();
        String name = nameText.getText();
        String nachname = nachnameText.getText();
        Date geburtsdatum = new SimpleDateFormat("yyyy-MM-dd").parse(geburtsdatumPicker.getValue().toString());
        String geschlecht = geschlechtChoice.getValue().toString();
        String telefonnummer = telefonnummerText.getText();
        //System.out.println(telefonnummer);
        String adresse = adresseText.getText();
        Date ausstellungsdatum = new SimpleDateFormat("yyyy-MM-dd").parse(austellungsdatumPicker.getValue().toString());

        DBautovermietung.addKunde(id, name, nachname, geburtsdatum, geschlecht, telefonnummer, adresse, ausstellungsdatum);
    }
}
