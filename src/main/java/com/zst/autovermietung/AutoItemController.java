package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AutoItemController {

    @FXML
    private Label markemodellLabel;

    @FXML
    private Label mietpreisLabel;

    @FXML
    private Label nummernschildLabel;

    @FXML
    private Label verfügbar;


    private Auto auto;

    public void setAutoItem(Auto auto) {
        markemodellLabel.setText(auto.getMarke() + " " + auto.getModell() + " " + auto.getBaujahr());
        mietpreisLabel.setText(Float.toString(auto.getMietpreise()));
        nummernschildLabel.setText(auto.getNummernschild());
        if(auto.checkVerfuegbarkeit()) {
            verfügbar.setText("Verfügbar");
        }else {
            verfügbar.setText("nicht Verfügbar");
        }
        this.auto = auto;
    }

    Parent root;
    Stage stage;
    Scene scene;
    public void mehrInfos(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("auto-details-item.fxml"));

        root = (Parent) fxmlLoader.load();
        AutoDetailsItemController detailsController = fxmlLoader.getController();
        detailsController.setAuto(auto);

        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("Auto-Informationen");
        stage.setScene(scene);
        stage.show();
    }
}
