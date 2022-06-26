package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AutoAddScreenController implements Initializable {

    @FXML
    private ChoiceBox verfugbarkeit;

    @FXML
    private TextField ModelField;

    @FXML
    private TextField farbeField;

    @FXML
    private TextField jahrField;

    @FXML
    private TextField kmField;

    @FXML
    private TextField markeField;

    @FXML
    private TextField nummernschildField;

    @FXML
    private TextField mieteField;

    @FXML
    private TextField getriebetypField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        verfugbarkeit.getItems().add("ja");
        verfugbarkeit.getItems().add("nein");
    }

    public void autoAddButton() {

        String modell = ModelField.getText();
        String marke = markeField.getText();
        String jahr = jahrField.getText();
        String farbe = farbeField.getText();
        int km = Integer.parseInt(kmField.getText());
        String nummernschild = nummernschildField.getText();
        float miete = Float.parseFloat(mieteField.getText());
        String getriebtyp = getriebetypField.getText();

        boolean verfugbar = false;
        boolean choice = false;
        try {
            if(verfugbarkeit.getValue().toString().equals("ja")) {
                verfugbar = true;
            }else {
                verfugbar = false;
            }
        } catch(NullPointerException e) {
            choice = true;
        }

        DBautovermietung.addAuto(new Auto(nummernschild, km, marke, modell, farbe, jahr,verfugbar, miete, getriebtyp));
        AutoScreenController.asc.autoList();
    }
}
