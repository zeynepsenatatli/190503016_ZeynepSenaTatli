package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MietvertragAddScreenController implements Initializable {

    @FXML
    private ChoiceBox kundeChoice;

    @FXML
    private ChoiceBox autoChoice;

    @FXML
    private Button addMietvertragButton;

    @FXML
    private DatePicker enddatumPicker;

    @FXML
    private DatePicker startdatumPicker;

    @FXML
    private Label mieteLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Kunde k : DBautovermietung.getKunden()) {
            if(k.getVorstrafe() == false && k.getHatAuto() == false) {
                kundeChoice.getItems().add(k.getId() + " - " + k.getName() + " " + k.getNachname());
            }
        }

        for(Auto a : DBautovermietung.getAuto()) {
            if(a.checkVerfuegbarkeit() == true) {
                autoChoice.getItems().add(a.getNummernschild());
            }
        }
    }

    public void berechneMieteButton(ActionEvent event) throws IOException {

        Date startdatum = null;
        boolean exceptions = false;
        try {
            startdatum = new SimpleDateFormat("yyyy-MM-dd").parse(startdatumPicker.getValue().toString());
        } catch(NullPointerException | ParseException e) {
            exceptions = true;
        }

        String auto = null;
        boolean autochoice = false;
        try {
            auto = autoChoice.getValue().toString();
        } catch(NullPointerException e) {
            autochoice = true;
        }

        String kunde = null;
        boolean kundechoice = false;
        try {
            kunde = kundeChoice.getValue().toString();
        } catch(NullPointerException e) {
            kundechoice = true;
        }

        Date enddatum = null;
        boolean exceptione = false;
        try {
            enddatum = new SimpleDateFormat("yyyy-MM-dd").parse(enddatumPicker.getValue().toString());
        } catch(NullPointerException e) {
            exceptione = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(exceptione || exceptions || kundechoice || autochoice) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Bitte geben Sie alle Informationen vollständig ein!");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();
        }else {
            float miete = Mietvertrag.berechneMiete(startdatum, enddatum, DBautovermietung.getEineAuto(auto).getMietpreise());
            mieteLabel.setText(Float.toString(miete) + " TL" );
        }

    }

    public void addMietvertrag() throws IOException {

        Date startdatum = null;
        boolean exceptions = false;
        try {
            startdatum = new SimpleDateFormat("yyyy-MM-dd").parse(startdatumPicker.getValue().toString());
        } catch(NullPointerException | ParseException e) {
            exceptions = true;
        }

        String auto = null;
        boolean autochoice = false;
        try {
            auto = autoChoice.getValue().toString();
        } catch(NullPointerException e) {
            autochoice = true;
        }

        String kunde = null;
        boolean kundechoice = false;
        try {
            String a[] = kundeChoice.getValue().toString().split(" ");
            kunde = a[0];
        } catch(NullPointerException e) {
            kundechoice = true;
        }

        Date enddatum = null;
        boolean exceptione = false;
        try {
            enddatum = new SimpleDateFormat("yyyy-MM-dd").parse(enddatumPicker.getValue().toString());
        } catch(NullPointerException e) {
            exceptione = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(exceptione || exceptions || kundechoice || autochoice) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Bitte geben Sie alle Informationen vollständig ein!");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();
        }else {
            float miete = Mietvertrag.berechneMiete(startdatum, enddatum, DBautovermietung.getEineAuto(auto).getMietpreise());
            String mitarbeiter = DBautovermietung.getThisBenutzer().getId();
            DBautovermietung.updateAutoVerfugbar(auto);
            DBautovermietung.updateKundeStatus(kunde);
            DBautovermietung.addMietvertrag(startdatum, enddatum, auto, kunde, miete, mitarbeiter);
            MietvertragScreenController.msc.mietvertragList();
        }

    }
}
