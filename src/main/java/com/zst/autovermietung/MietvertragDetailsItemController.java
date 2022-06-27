package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MietvertragDetailsItemController implements Initializable {
    @FXML
    private Label edatumLabel;

    @FXML
    private Label istBezahltLabel;

    @FXML
    private Label kundeidLabel;

    @FXML
    private Label kundenameLabel;

    @FXML
    private Label markemodelljahrLabel;

    @FXML
    private Label mieteLabel;

    @FXML
    private Label mitidLabel;

    @FXML
    private Label mitnameLabel;

    @FXML
    private Label nsLabel;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label problemLabel;

    @FXML
    private Label sdatumLabel;

    @FXML
    private Label vertragidLabel;

    @FXML
    private Button removeMietvertragButton;

    @FXML
    private Button editMietvertragButton;

    private Mietvertrag m;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(DBautovermietung.getThisBenutzer().getRolle().equals("Manager")) {
            removeMietvertragButton.setVisible(false);
            editMietvertragButton.setVisible(false);

        }
    }

    public void setMietvertrag(Mietvertrag mv) {

        this.m = mv;

        sdatumLabel.setText(mv.getStartdatumString());
        edatumLabel.setText(mv.getEnddatumString());
        vertragidLabel.setText(mv.getVertraId());
        problemLabel.setText(mv.getProblembezeichnung());
        nsLabel.setText(mv.getAuto().getNummernschild());
        mitnameLabel.setText(mv.getVerantwortlicheMitarbeiter().getName() + " " + mv.getVerantwortlicheMitarbeiter().getNachname());
        mitidLabel.setText(mv.getVerantwortlicheMitarbeiter().getId());
        mieteLabel.setText(Float.toString(mv.getMiete()) + " TL");
        markemodelljahrLabel.setText(mv.getAuto().getMarke() + " " + mv.getAuto().getModell()+ " " + mv.getAuto().getBaujahr());
        kundeidLabel.setText(mv.getKunde().getId());
        kundenameLabel.setText(mv.getKunde().getName() + " " + mv.getKunde().getNachname());
        if(mv.getBezahlStatus()) {
            istBezahltLabel.setText("bezahlt");
        }else {
            istBezahltLabel.setText("nicht bezahlt!!");
        }

    }
    public void removeVertrag() {

        DBautovermietung.updateRemoveMietvertragAuto(m.getAuto().getNummernschild());
        DBautovermietung.updateRemoveMietvertragKunde(m.getKunde().getId());
        DBautovermietung.removeMietvertrag(m);
        MietvertragScreenController.msc.mietvertragList();

        Stage s = (Stage) pane.getScene().getWindow();
        s.close();

    }

    Parent root;
    Stage stage;
    Scene scene;

    public void editVertrag(ActionEvent event) {

        FXMLLoader mietvertragEdit = new FXMLLoader(getClass().getResource("mietvertrag-edit.fxml"));
        try {
            root = mietvertragEdit.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MietvertragEditController editController = mietvertragEdit.getController();
        editController.editInfo(m);
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
