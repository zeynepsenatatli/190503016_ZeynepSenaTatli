package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MietvertragEditController implements Initializable {

    @FXML
    private Label edatumLabel;

    @FXML
    private ChoiceBox istBezahltChoice;

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
    private Label sdatumLabel;

    @FXML
    private Label vertragidLabel;

    @FXML
    private TextArea problemField;

    private Mietvertrag mietvetrag;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        istBezahltChoice.getItems().add("bezahlt");
        istBezahltChoice.getItems().add("nicht bezahlt!!!");
    }

    public void editInfo(Mietvertrag mv) {
        mietvetrag = mv;
        setInfos(mv);
    }
    public void setInfos(Mietvertrag mv) {

        sdatumLabel.setText(mv.getStartdatumString());
        edatumLabel.setText(mv.getEnddatumString());
        vertragidLabel.setText(mv.getVertraId());
        problemField.setText(mv.getProblembezeichnung());
        nsLabel.setText(mv.getAuto().getNummernschild());
        mitnameLabel.setText(mv.getVerantwortlicheMitarbeiter().getName() + " " + mv.getVerantwortlicheMitarbeiter().getNachname());
        mitidLabel.setText(mv.getVerantwortlicheMitarbeiter().getId());
        mieteLabel.setText(Float.toString(mv.getMiete()) + " TL");
        markemodelljahrLabel.setText(mv.getAuto().getMarke() + " " + mv.getAuto().getModell()+ " " + mv.getAuto().getBaujahr());
        kundeidLabel.setText(mv.getKunde().getId());
        kundenameLabel.setText(mv.getKunde().getName() + " " + mv.getKunde().getNachname());
        if(mv.getBezahlStatus()) {
            istBezahltChoice.setValue("bezahlt");
        }else {
            istBezahltChoice.setValue("nicht bezahlt!!!");
        }
    }

    public void saveInfos() throws IOException {
        String istBezahlt = istBezahltChoice.getValue().toString();
        String problem = problemField.getText();

        if(istBezahlt.equals("bezahlt")) {
            mietvetrag.setBezahlStatus(true);
        }else {
            mietvetrag.setBezahlStatus(false);
        }

        mietvetrag.setProblembezeichnung(problem);

        DBautovermietung.updateMietvertrag(mietvetrag);
        MietvertragScreenController.msc.mietvertragList();

        Stage s = (Stage) pane.getScene().getWindow();
        s.close();
    }
}
