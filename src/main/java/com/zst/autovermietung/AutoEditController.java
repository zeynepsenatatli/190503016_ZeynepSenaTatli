package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AutoEditController implements Initializable {

    @FXML
    private TextField farbeField;

    @FXML
    private Label jahrLabel;

    @FXML
    private TextField kmField;

    @FXML
    private Label markemodellLabel;

    @FXML
    private TextField mieteField;

    @FXML
    private Label nummernschildLabel;

    @FXML
    private AnchorPane pane;

    @FXML
    private ChoiceBox typChoice;

    @FXML
    private ChoiceBox verfugbarChoice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        verfugbarChoice.getItems().add("ja");
        verfugbarChoice.getItems().add("nein");

        typChoice.getItems().add("Manuel");
        typChoice.getItems().add("Automatisch");
    }

    private Auto auto;
    public void editInfo(Auto auto) {
        this.auto = auto;
        setInfos(auto);
    }

    public void setInfos(Auto a) {
        farbeField.setText(a.getFarbe());
        nummernschildLabel.setText(a.getNummernschild());
        markemodellLabel.setText(a.getMarke() + " " + a.getModell());
        jahrLabel.setText(a.getBaujahr());
        kmField.setText(Integer.toString(a.getKilometerstand()));
        mieteField.setText(Float.toString(a.getMietpreise()));
        typChoice.setValue(a.getGetriebetyp());
        if(a.checkVerfuegbarkeit()) {
            verfugbarChoice.setValue("ja");
        }else {
            verfugbarChoice.setValue("nein");
        }
    }

    public void saveInfos() {
        String farbe = farbeField.getText();
        int km = Integer.parseInt(kmField.getText());
        float miete = Float.parseFloat(mieteField.getText());
        String verfuegbar = verfugbarChoice.getValue().toString();
        String typ = typChoice.getValue().toString();


        auto.setFarbe(farbe);
        auto.setKilometerstand(km);
        auto.setMietpreise(miete);
        auto.setGetriebetyp(typ);

        if(verfuegbar.equals("ja")) {
            auto.setVerfuegbarkeit(true);
        }else {
            auto.setVerfuegbarkeit(false);
        }

        DBautovermietung.updateAuto(auto);
        AutoScreenController.asc.autoList();

        Stage s = (Stage) pane.getScene().getWindow();
        s.close();

    }
}
