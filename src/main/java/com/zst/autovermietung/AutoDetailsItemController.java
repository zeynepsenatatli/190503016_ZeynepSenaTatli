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

public class AutoDetailsItemController {

    @FXML
    private Label farbeLabel;

    @FXML
    private Label getriebeLabel;

    @FXML
    private Label jahrLabel;

    @FXML
    private Label kmLabel;

    @FXML
    private Label markemodellLabel;

    @FXML
    private Label mieteLabel;

    @FXML
    private Label nummernschildLabel;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label verfugbarLabel;

    private Auto auto;

    public void setAuto(Auto auto) {
        this.auto = auto;

        farbeLabel.setText(auto.getFarbe());
        getriebeLabel.setText(auto.getGetriebetyp());
        jahrLabel.setText(auto.getBaujahr());
        kmLabel.setText(Integer.toString(auto.getKilometerstand()));
        markemodellLabel.setText(auto.getMarke() + " " + auto.getModell());
        mieteLabel.setText(Float.toString(auto.getMietpreise()));
        nummernschildLabel.setText(auto.getNummernschild());
        if(auto.checkVerfuegbarkeit()) {
            verfugbarLabel.setText("verfügbar");
        }else {
            verfugbarLabel.setText("nicht verfügbar");
        }
    }

    Parent root;
    Stage stage;
    Scene scene;

    public void removeAuto() {
        boolean c = false;
        for(Mietvertrag mv : DBautovermietung.getMietvertrag()) {
            if(mv.getAuto().getNummernschild().equals(auto.getNummernschild())) {
                c = true;
                break;
            }
        }
        if(c) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pop-up.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PopUpController popUpController = fxmlLoader.getController();
            popUpController.setMessage("Dies Auto hat einen Mietvertrag. Löschen ist nicht möglich.");
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Warnung!");
            stage.setScene(scene);
            stage.show();
        }else {
            DBautovermietung.removeAuto(auto);
            AutoScreenController.asc.autoList();
        }

        Stage s = (Stage) pane.getScene().getWindow();
        s.close();

    }

    public void editAuto(ActionEvent event) throws IOException {

        FXMLLoader autoEdit = new FXMLLoader(getClass().getResource("auto-edit.fxml"));
        root = autoEdit.load();

        AutoEditController editController = autoEdit.getController();
        editController.editInfo(auto);
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
