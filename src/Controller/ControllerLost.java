package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerLost {

    @FXML Text level;

    @FXML Text point;

    @FXML
    Pane pane;

    private Stage stage;


    public void setPoint(String point) {
        this.point.setText(point);
    }


    public void setLevel(String level) {
        this.level.setText(level);
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }


    @FXML public void start(KeyEvent k) throws IOException {
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        if (k.getCode().equals(KeyCode.Q)) {
            primaryStage.close();
        }
        else if(k.getCode().equals(KeyCode.S)){
            Pane start = (Pane) FXMLLoader.load(getClass().getResource("../acceuil.fxml"));
            Scene welcome = new Scene(start,start.getPrefWidth(),start.getPrefHeight());
            welcome.getRoot().requestFocus();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Virus Game");
            primaryStage.setScene(welcome);
            primaryStage.show();
        }
    }
}
