package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
    private ChoiceBox getriebetypChoice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        verfugbarkeit.getItems().add("ja");
        verfugbarkeit.getItems().add("nein");

        getriebetypChoice.getItems().add("Automatisch");
        getriebetypChoice.getItems().add("Manuel");

    }

    public void autoAddButton() throws IOException {

        String modell = ModelField.getText();
        String marke = markeField.getText();
        String jahr = jahrField.getText();
        String farbe = farbeField.getText();
        int km;
        if(kmField.getText().equals("")){
            km = 0;
        }else {
            km = Integer.parseInt(kmField.getText());
        }
        String nummernschild = nummernschildField.getText();
        float miete;
        if(mieteField.getText().equals("")){
            miete = 0;
        }else {
            miete = Float.parseFloat(mieteField.getText());
        }

        String getriebtyp = getriebetypChoice.getValue().toString();

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


        if(modell.isEmpty() || marke.isEmpty() ||jahr.isEmpty() ||farbe.isEmpty() || km == 0 || nummernschild.isEmpty() ||
        miete == 0 || getriebtyp.isEmpty() || choice) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Bitte geben Sie alle Informationen vollständig ein!");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();

        }else if(DBautovermietung.checkAuto(nummernschild)) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Dies Auto ist bereit im System gespeichert!");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();


        }else if(Auto.checkNummernschild(nummernschild) == false) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Das Format von eingegebene Nummernschild ist falsch.");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();

        }else {
            DBautovermietung.addAuto(new Auto(nummernschild, km, marke, modell, farbe, jahr,verfugbar, miete, getriebtyp));
            AutoScreenController.asc.autoList();
        }
    }
}
