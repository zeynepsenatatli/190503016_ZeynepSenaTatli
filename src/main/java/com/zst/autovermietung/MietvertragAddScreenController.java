package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MietvertragAddScreenController implements Initializable {

    @FXML
    private ChoiceBox kundeChoice;

    @FXML
    private ChoiceBox autoChoice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Kunde k : DBautovermietung.getKunden()) {
            if(k.getVorstrafe() == false) {
                kundeChoice.getItems().add(k.getId() + " - " + k.getName() + " " + k.getNachname());
            }
        }

        for(Auto a : DBautovermietung.getAuto()) {
            if(a.checkVerfuegbarkeit() == true) {
                autoChoice.getItems().add(a.getNummernschild());
            }
        }
    }
}
