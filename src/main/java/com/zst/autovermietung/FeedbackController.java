package com.zst.autovermietung;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FeedbackController {

    @FXML
    private Label messageLabel;

    public void setMessage(String s) {
        messageLabel.setText(s);
    }
}
