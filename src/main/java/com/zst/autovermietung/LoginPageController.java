package com.zst.autovermietung;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private TextField benutzernameText;

    @FXML
    private TextField passwortText;

    @FXML
    private Label messageLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) {

        String benutzername = benutzernameText.getText();
        String passwort = passwortText.getText();

        if(benutzername.isEmpty() || passwort.isEmpty()) {
            messageLabel.setText("Bitte geben Sie ihre Benutzername und Passwort ein!");
            messageLabel.setStyle("-fx-text-fill: #ef2727; -fx-font-size: 12px;");
        }else{
            if(DBautovermietung.sucheBenutzer(benutzername, passwort)) {
                messageLabel.setText("Anmeldung erfolgreich!");
                messageLabel.setStyle("-fx-text-fill: #058c05; -fx-font-size: 12px;");
                try {
                    root = FXMLLoader.load(getClass().getResource("home-page.fxml"));
                    stage =(Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                        System.out.println(e.getMessage());
                }
            } else {
                messageLabel.setText("Bitte geben Sie korrekte Benutzername/Passwort ein!");
                messageLabel.setStyle("-fx-text-fill: #ef2727; -fx-font-size: 12px;");
            }
        }
    }
}
