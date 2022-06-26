package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
    public void setMietvertrag(Mietvertrag m) {

        String s = dformat.format(m.getEnddatum());
        enddatumLabel.setText(s);
        kundeLabel.setText(m.getKunde().getName() + " " + m.getKunde().getNachname());
        nschildLabel.setText(m.getAuto().getNummernschild());
        s = dformat.format(m.getStartdatum());
        startdatumLabel.setText(s);
        vertragidLabel.setText(m.getVertraId());
    }

    public void mehrInfos() {

    }
}
