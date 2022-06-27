package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MitarbeiterScreenController implements Initializable {
    @FXML
    private AnchorPane firstHomeScreen;

    @FXML
    private Button mitarbeiterAddButton;

    @FXML
    private VBox mitarbeiterList;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static MitarbeiterScreenController mitarbeitersc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mitarbeitersc = this;
        mitarbeiterListe();
    }

    public void mitarbeiterListe() {
        mitarbeiterList.getChildren().clear();
        ArrayList<Mitarbeiter> mitarbeiters = DBautovermietung.getMitarbeiter();
        for(int i = 0; i < mitarbeiters.size(); i++) {

            FXMLLoader mitItem = new FXMLLoader(getClass().getResource("mitarbeiter-item.fxml"));
            try {
                mitarbeiterList.getChildren().add(mitItem.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            MitarbeiterItemController mitItemController = mitItem.getController();
            mitItemController.setMitarbeiter(mitarbeiters.get(i));
        }
    }

    public void openAddScreen() {

    }

}
