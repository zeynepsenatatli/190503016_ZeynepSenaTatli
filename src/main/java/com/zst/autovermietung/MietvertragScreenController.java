package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MietvertragScreenController implements Initializable {

    @FXML
    private VBox mietvertragList;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static MietvertragScreenController msc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msc = this;
        mietvertragList();
    }

    public void mietvertragList() {
        mietvertragList.getChildren().clear();
        ArrayList<Mietvertrag> mvertraege = DBautovermietung.getMietvertrag();
        for(int i = 0; i < mvertraege.size(); i++) {

            if(Mietvertrag.istAktuell(mvertraege.get(i).getEnddatum()) || mvertraege.get(i).getBezahlStatus() == false) {
                FXMLLoader mvItem = new FXMLLoader(getClass().getResource("mietvertrag-item.fxml"));
                try {
                    mietvertragList.getChildren().add(mvItem.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MietvertragItemController mvItemController = mvItem.getController();
                mvItemController.setMietvertrag(mvertraege.get(i));
            }

            //if(Mietvertrag.istAktuell(mvertraege.get(i).getEnddatum()) == false && mvertraege.get(i).getBezahlStatus() == true) {
                updateAutoStatus(mvertraege.get(i));
                updateKundeStatus(mvertraege.get(i));
            //}
        }
    }


    public void updateAutoStatus(Mietvertrag m) {
        DBautovermietung.updateAutoVerfugbar(m.getAuto().getNummernschild());
    }
    public void updateKundeStatus(Mietvertrag m) {
        DBautovermietung.updateKundeStatus(m.getKunde().getId());
    }

    public void openAddScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mietvertrag-add-screen.fxml"));
        root = (Parent) fxmlLoader.load();
        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("neue Mietvertrag erstellen");
        stage.setScene(scene);
        stage.show();
    }

    public void abgelaufendeMietvertrage() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("abgelaufende-mietvertrag.fxml"));
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("abgelaufende Mietverträge");
        stage.setScene(scene);
        stage.show();
    }
}
