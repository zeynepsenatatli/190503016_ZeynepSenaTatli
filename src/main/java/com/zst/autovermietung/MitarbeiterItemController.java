package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class MitarbeiterItemController {
    @FXML
    private Label bnameLabel;

    @FXML
    private Label fullnameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Button mehrButton;

    @FXML
    private Label telefonnummerLabel;

    private Mitarbeiter m;
    SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
    public void setMitarbeiter(Mitarbeiter m) {

        this.m = m;

        bnameLabel.setText(m.getBenutzername());
        idLabel.setText(m.getId());
        fullnameLabel.setText(m.getName() + " " + m.getNachname());
        telefonnummerLabel.setText(m.getTelefonnummer());
    }

    Parent root;
    Stage stage;
    Scene scene;

    public void mehrInformationen(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mitarbeiter-item-details.fxml"));

        root = (Parent) fxmlLoader.load();
        MitarbeiterItemDetailsController detailsController = fxmlLoader.getController();
        detailsController.setMitarbeiter(m);

        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("Mitarbeiter-Informationen");
        stage.setScene(scene);
        stage.show();
    }

}
