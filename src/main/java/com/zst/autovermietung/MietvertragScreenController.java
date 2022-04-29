package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MietvertragScreenController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void openAddScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mietvertrag-add-screen.fxml"));
        root = (Parent) fxmlLoader.load();
        stage = new Stage();
        scene = new Scene(root);
        stage.setTitle("neue Mietvertrag erstellen");
        stage.setScene(scene);
        stage.show();
    }
}
