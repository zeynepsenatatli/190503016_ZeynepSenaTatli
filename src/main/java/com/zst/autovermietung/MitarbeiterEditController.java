package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MitarbeiterEditController {

    @FXML
    private TextArea adressField;

    @FXML
    private TextField bnField;

    @FXML
    private TextField telefonField;

    @FXML
    private Label gdLabel;

    @FXML
    private Label geschlechtLabel;

    @FXML
    private Label idLabel;

    @FXML
    private TextField nachnameField;

    @FXML
    private TextField nameField;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label rolleLabel;


    private Mitarbeiter mit;

    public void editInfo(Mitarbeiter m) {
        mit = m;
        setInfos(mit);
    }

    public void setInfos(Mitarbeiter m) {
        bnField.setText(m.getBenutzername());
        gdLabel.setText(m.getGeburtsdatumString());
        geschlechtLabel.setText(m.getGeschlecht());
        idLabel.setText(m.getId());
        nameField.setText(m.getName());
        nachnameField.setText(m.getNachname());
        rolleLabel.setText(m.getRolle());
        adressField.setText(m.getAdresse());
        telefonField.setText(m.getTelefonnummer());
    }

    public void saveInfos() throws IOException {

        String bn = bnField.getText();
        String name = nameField.getText();
        String nname = nachnameField.getText();
        String telefon = telefonField.getText();
        String adress = adressField.getId();


        if(bn.isEmpty() || name.isEmpty() || nname.isEmpty() || telefon.isEmpty() || adress.isEmpty()) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Bitte geben Sie Infos vollständig ein!");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();

        }else if(Person.checkTelefon(telefon) == false) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Das Format von eingegebene Telefonnummer ist falsch.");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();
        } else {

            mit.setAdresse(adress);
            mit.setTelefonnummer(telefon);
            mit.setName(name);
            mit.setNachname(nname);
            mit.setBenutzername(bn);

            DBautovermietung.updateBenutzername(mit.getId(), bn);
            DBautovermietung.updateMitarbeiter(mit);
            MitarbeiterScreenController.mitarbeitersc.mitarbeiterListe();

            Stage s = (Stage) pane.getScene().getWindow();
            s.close();
        }
    }
}
