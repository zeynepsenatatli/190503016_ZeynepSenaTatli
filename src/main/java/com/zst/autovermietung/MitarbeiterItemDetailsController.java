package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MitarbeiterItemDetailsController {

    @FXML
    private Label adressLabel;

    @FXML
    private Label bnLabel;

    @FXML
    private Label gdLabel;

    @FXML
    private Label geschlechtLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label rolleLabel;

    @FXML
    private Label telefonnummerLabel;

    @FXML
    private AnchorPane pane;

    private Mitarbeiter m;

    public void setMitarbeiter(Mitarbeiter m) {

        this.m = m;

        bnLabel.setText(m.getBenutzername());
        gdLabel.setText(m.getGeburtsdatumString());
        geschlechtLabel.setText(m.getGeschlecht());
        idLabel.setText(m.getId());
        nameLabel.setText(m.getName() + " " + m.getNachname());
        rolleLabel.setText(m.getRolle());
        adressLabel.setText(m.getAdresse());
        telefonnummerLabel.setText(m.getTelefonnummer());
    }

    public void removeMitarbeiter() throws IOException {
        boolean c = false;

        for(Mietvertrag mv : DBautovermietung.getMietvertrag()) {
            if(mv.getVerantwortlicheMitarbeiter().getId().equals(m.getId())) {
                c = true;
                break;
            }
        }
        if(c) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Dieser Mitarbeiter ist für einen Mietvertrag verantwortlich. Löschen ist nicht möglich.");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();
        }else {
            DBautovermietung.removeMitarbeiter(m);
            MitarbeiterScreenController.mitarbeitersc.mitarbeiterListe();
        }

        Stage s = (Stage) pane.getScene().getWindow();
        s.close();

    }

    Parent root;
    Stage stage;
    Scene scene;

    public void editMitarbeiter(ActionEvent event) throws IOException {

        FXMLLoader mitedit = new FXMLLoader(getClass().getResource("mitarbeiter-edit.fxml"));
        root = mitedit.load();

        MitarbeiterEditController editController = mitedit.getController();
        editController.editInfo(m);

        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
