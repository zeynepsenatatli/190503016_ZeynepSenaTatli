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



public class KundeItemController {

    @FXML
    private Label fullnameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label telefonnummerLabel;

    @FXML
    private Button mehrButton;

    private Kunde kunde;

    public void setKundeItem(Kunde k) {
        fullnameLabel.setText(k.getName() + " " + k.getNachname());
        idLabel.setText(k.getId());
        telefonnummerLabel.setText(k.getTelefonnummer());
        kunde = k;
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void mehrInformationen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kunde-details-item.fxml"));

        root = (Parent) fxmlLoader.load();
        KundeDetailsItemController detailsController = fxmlLoader.getController();
        detailsController.setKunde(kunde);

        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("Kunde-Informationen");
        stage.setScene(scene);
        stage.show();
    }
}
