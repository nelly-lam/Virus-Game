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
        this.point.setText(this.point.getText() + point);
    }


    public void setLevel(String level) {
        this.level.setText(this.level.getText() + level);
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }


    @FXML public void start(KeyEvent k) throws IOException {
        if (k.getCode().equals(KeyCode.Q)) {
            stage.close();
        }
        else if(k.getCode().equals(KeyCode.S)){
            Pane start = (Pane) FXMLLoader.load(getClass().getResource("../acceuil.fxml"));
            Scene welcome = new Scene(start,start.getPrefWidth(),start.getPrefHeight());
            welcome.getRoot().requestFocus();
            stage.setResizable(false);
            stage.setTitle("Virus Game");
            stage.setScene(welcome);
            stage.show();
        }
    }
}
