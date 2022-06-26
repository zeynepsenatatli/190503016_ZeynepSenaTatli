package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MietvertragItemController {

    @FXML
    private Label enddatumLabel;

    @FXML
    private Label kundeLabel;

    @FXML
    private Label nschildLabel;

    @FXML
    private Label startdatumLabel;

    @FXML
    private Label vertragidLabel;

    private Mietvertrag m;
    SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
    public void setMietvertrag(Mietvertrag m) {

        this.m = m;

        String s = dformat.format(m.getEnddatum());
        enddatumLabel.setText(s);
        kundeLabel.setText(m.getKunde().getName() + " " + m.getKunde().getNachname());
        nschildLabel.setText(m.getAuto().getNummernschild());
        s = dformat.format(m.getStartdatum());
        startdatumLabel.setText(s);
        vertragidLabel.setText(m.getVertraId());
    }

    Parent root;
    Stage stage;
    Scene scene;

    public void mehrInfos(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mietvertrag-details-item.fxml"));

        root = (Parent) fxmlLoader.load();
        MietvertragDetailsItemController detailsController = fxmlLoader.getController();
        detailsController.setMietvertrag(m);

        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("Mietvertrag-Informationen");
        stage.setScene(scene);
        stage.show();
    }


}
