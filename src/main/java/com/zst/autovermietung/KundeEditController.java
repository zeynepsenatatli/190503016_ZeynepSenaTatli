package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

public class KundeEditController {

    @FXML
    private TextArea adresseField;

    @FXML
    private Label alterLabel;

    @FXML
    private Label fuhrerscheinLabel;

    @FXML
    private Label gbLabel;

    @FXML
    private TextField geschlechtField;

    @FXML
    private Label idLabel;

    @FXML
    private TextField kundeNameField;

    @FXML
    private TextField kundeNNameField;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField telefonField;

    @FXML
    private TextField vorstrafenField;

    @FXML
    private Label hatautoLabel;

    private Kunde kunde;

    public void editInfo(Kunde k) {
        kunde = k;
        setInfos(k);
    }

    public void saveInfos() throws IOException {
        String adress = adresseField.getText();
        String telefonnummer = telefonField.getText();
        String vorstrafen = vorstrafenField.getText();

        String geschlecht = geschlechtField.getText();
        String name = kundeNameField.getText();
        String nachname = kundeNNameField.getText();


        if(Person.checkTelefon(telefonnummer)) {
            kunde.setTelefonnummer(telefonnummer);
            kunde.setAdresse(adress);

            if(vorstrafen.equals("-")){
                kunde.setVorstrafe(false);
            }else {
                kunde.setVorstrafe(true);

            }
            kunde.setVorstrafeNote(vorstrafen);
            kunde.setGeschlecht(geschlecht);
            kunde.setName(name);
            kunde.setNachname(nachname);

            DBautovermietung.updateKunde(kunde);
            KundeScreenController.ksc.kundeList();

            Stage s = (Stage) pane.getScene().getWindow();
            s.close();

        }else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Das Format von eingegebene Telefonnummer ist falsch.");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();
        }
    }
    public void setInfos(Kunde k) {
        kundeNameField.setText(kunde.getName());
        kundeNNameField.setText(kunde.getNachname());
        gbLabel.setText(kunde.getGeburtsdatumString());
        alterLabel.setText(Integer.toString(kunde.getAlter()));
        fuhrerscheinLabel.setText(kunde.getDatumVonFuehrerscheinString());
        adresseField.setText(kunde.getAdresse());
        geschlechtField.setText(kunde.getGeschlecht());
        idLabel.setText(kunde.getId());
        telefonField.setText(kunde.getTelefonnummer());
        if(kunde.getVorstrafe()) {
            vorstrafenField.setText(kunde.getVorstrafeNote());
        }else {
            vorstrafenField.setText("-");
        }

        if(kunde.getHatAuto()) {
            hatautoLabel.setText("ja");
        }else {
            hatautoLabel.setText("nein");
        }
    }
   /* @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }*/
}
