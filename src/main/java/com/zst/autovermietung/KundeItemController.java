package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class KundeItemController {

    @FXML
    private Label fullnameLabel;

    @FXML
    private Label geschlechtLabel;

    @FXML
    private Label telefonnummerLabel;

    @FXML
    private Button mehrButton;

    public void setKundeItem(Kunde k) {
        fullnameLabel.setText(k.getName() + " " + k.getNachname());
        geschlechtLabel.setText(k.getGeschlecht());
        telefonnummerLabel.setText(k.getTelefonnummer());
    }

}
