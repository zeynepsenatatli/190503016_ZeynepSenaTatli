package com.zst.autovermietung;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class KundeDetailsItemController {

    @FXML
    private Label adresseLabel;

    @FXML
    private Label alterLabel;

    @FXML
    private Label fuhrerscheinLabel;

    @FXML
    private Label geburtsdatum;

    @FXML
    private Label geschlechtLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label kundeNameLabel;

    @FXML
    private Label telefonnummerLabel;

    @FXML
    private Label vorstrafenLabel;


    private Kunde kunde;

    public void setKunde(Kunde kunde) {
        kundeNameLabel.setText(kunde.getName() + " " + kunde.getNachname());
        idLabel.setText(kunde.getId());
        geschlechtLabel.setText(kunde.getGeschlecht());
        alterLabel.setText(Integer.toString(kunde.getAlter()));
        telefonnummerLabel.setText(kunde.getTelefonnummer());
        adresseLabel.setText(kunde.getAdresse());
        fuhrerscheinLabel.setText(kunde.getDatumVonFuehrerscheinString());
        geburtsdatum.setText(kunde.getGeburtsdatumString());

        if(kunde.getVorstrafe() == false) {
            vorstrafenLabel.setText("-");
        }else {
            vorstrafenLabel.setText(kunde.getVorstrafeNote());
        }
    }

    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //adresseLabel.setText();
    }*/
}
