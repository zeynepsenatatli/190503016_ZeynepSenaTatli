package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AbgelaufendeMietvertragController implements Initializable {

    @FXML
    private VBox abgelaufendemietvertragList;

    public static AbgelaufendeMietvertragController abgelaufendemc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        abgelaufendemc = this;
        abgelaufendemietvertragList();
    }

    public void abgelaufendemietvertragList() {
        abgelaufendemietvertragList.getChildren().clear();
        ArrayList<Mietvertrag> mvertraege = DBautovermietung.getMietvertrag();

        for(int i = 0; i < mvertraege.size(); i++) {
            if(Mietvertrag.istAktuell(mvertraege.get(i).getEnddatum()) == false && mvertraege.get(i).getBezahlStatus() == true) {
                FXMLLoader mvItem = new FXMLLoader(getClass().getResource("mietvertrag-item.fxml"));
                try {
                    abgelaufendemietvertragList.getChildren().add(mvItem.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MietvertragItemController mvItemController = mvItem.getController();
                mvItemController.setMietvertrag(mvertraege.get(i));
            }
        }
    }
}
