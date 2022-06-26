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

public class AutoScreenController implements Initializable {

    @FXML
    private VBox autoList;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static AutoScreenController asc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        asc = this;
        autoList();
    }

    public void autoList() {
        autoList.getChildren().clear();
        ArrayList<Auto> autos = DBautovermietung.getAuto();
        for(int i = 0; i < autos.size(); i++) {
            FXMLLoader autoItem = new FXMLLoader(getClass().getResource("auto-item.fxml"));
            try {
                autoList.getChildren().add(autoItem.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            AutoItemController autoItemController = autoItem.getController();
            autoItemController.setAutoItem(autos.get(i));
        }
    }


    public void openAddScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("auto-add-screen.fxml"));
        root = (Parent) fxmlLoader.load();
        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("neue Auto hinzufügen");
        stage.setScene(scene);
        stage.show();
    }


}
