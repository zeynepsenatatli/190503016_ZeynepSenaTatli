package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstHomeScreenController implements Initializable {

    @FXML
    private Label anzahlAutos;

    @FXML
    private Label anzahlkunden;

    @FXML
    private Label anzahlmietvertrage;

    @FXML
    private Label anzahlverfugautos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anzahlkunden.setText(Integer.toString(DBautovermietung.getKunden().size()));
        anzahlmietvertrage.setText(Integer.toString(DBautovermietung.getMietvertrag().size()));
        anzahlAutos.setText(Integer.toString(DBautovermietung.getAuto().size()));
        int counter = 0;
        for(Auto a : DBautovermietung.getAuto()) {
            if(a.checkVerfuegbarkeit() == true) {
                counter++;
            }
        }
        anzahlverfugautos.setText(Integer.toString(counter));
    }
}
