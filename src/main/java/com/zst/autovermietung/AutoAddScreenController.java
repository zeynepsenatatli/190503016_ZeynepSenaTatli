package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AutoAddScreenController implements Initializable {

    @FXML
    private ChoiceBox verfugbarkeit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        verfugbarkeit.getItems().add("ja");
        verfugbarkeit.getItems().add("nein");
    }
}
