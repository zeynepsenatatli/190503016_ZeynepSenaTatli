package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class KundeScreenController implements Initializable{

    @FXML
    private AnchorPane kundeScreen;

    @FXML
    private VBox kundenList;

    private Stage stage;
    private Scene scene;
    private Parent root;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Kunde> kunden = DBautovermietung.getKunden();
        for(int i = 0; i < kunden.size(); i++) {
            FXMLLoader kundeItem = new FXMLLoader(getClass().getResource("kunde-item.fxml"));
            try {
                kundenList.getChildren().add(kundeItem.load());
                KundeItemController kundeItemController = kundeItem.getController();
                kundeItemController.setKundeItem(kunden.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void openAddScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kunde-add-screen.fxml"));
        root = (Parent) fxmlLoader.load();
        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("neue Kunde hinzufügen");
        stage.setScene(scene);
        stage.show();

    }



}
