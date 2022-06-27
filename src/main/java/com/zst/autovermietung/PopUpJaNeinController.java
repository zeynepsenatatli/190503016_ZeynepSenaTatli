package com.zst.autovermietung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUpJaNeinController {

    @FXML
    private AnchorPane pane;

    @FXML
    private Button jaButton;

    @FXML
    private Label messageLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void setMessageLabel(String s) {
        messageLabel.setText(s);
    }

    public boolean jaButton() throws IOException {
        return true;
    }



}
