package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class KundeAddScreenController implements Initializable {

    @FXML
    private ChoiceBox geschlechtChoice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        geschlechtChoice.getItems().add("männlich");
        geschlechtChoice.getItems().add("weiblich");
    }
}
