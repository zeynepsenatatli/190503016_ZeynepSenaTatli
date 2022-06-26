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
            mvertraege.get(i).getKunde().getNachname();
            FXMLLoader mvItem = new FXMLLoader(getClass().getResource("mietvertrag-item.fxml"));
            try {
                mietvertragList.getChildren().add(mvItem.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            MietvertragItemController mvItemController = mvItem.getController();
            mvItemController.setMietvertrag(mvertraege.get(i));
        }
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
}
