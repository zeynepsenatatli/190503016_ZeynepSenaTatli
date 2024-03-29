package com.zst.autovermietung;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KundeDetailsItemController {

    @FXML
    private AnchorPane pane;

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

    @FXML
    private Label hatautoLabel;

    private Kunde kunde;

    public void setKunde(Kunde kunde) {

        this.kunde = kunde;

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

        if(kunde.getHatAuto()) {
            hatautoLabel.setText("ja");
        }else {
            hatautoLabel.setText("nein");
        }
    }

    public void removeKunde() throws IOException {
        boolean c = false;

        for(Mietvertrag mv : DBautovermietung.getMietvertrag()) {
            if(mv.getKunde().getId().equals(kunde.getId())) {
                c = true;
                break;
            }
        }
        if(c) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Dieser Kunde hat einen Mietvertrag. Löschen ist nicht möglich.");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();
        }else {
            DBautovermietung.removeKunde(kunde);
            KundeScreenController.ksc.kundeList();
        }

        Stage s = (Stage) pane.getScene().getWindow();
        s.close();

    }

    Parent root;
    Stage stage;
    Scene scene;

    public void editKunde(ActionEvent event) throws IOException {

        if(DBautovermietung.getThisBenutzer().getRolle().equals("Manager")) {
            FXMLLoader kundeEdit = new FXMLLoader(getClass().getResource("kunde-edit.fxml"));
            root = kundeEdit.load();

            KundeEditController editController = kundeEdit.getController();
            editController.editInfo(kunde);
        }else if(DBautovermietung.getThisBenutzer().getRolle().equals("Mitarbeiter")) {
            FXMLLoader kundeEdit = new FXMLLoader(getClass().getResource("kunde-edit-fur-mitarbeiter.fxml"));
            root = kundeEdit.load();
            KundeEditFurMietarbeiterController editController = kundeEdit.getController();
            editController.editInfo(kunde);
        }

        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
