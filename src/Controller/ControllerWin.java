package Controller;

import Model.Animation;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerWin {
    @FXML Text won;
    @FXML Text point;
    @FXML Text level;
    @FXML
    Pane pane;

    public void setPoint(String point) {
        this.point.setText(this.point.getText() + point);
    }

    public void setWon(String won) {
        this.won.setText(this.won.getText() + won);
    }

    public Pane getPane() {
        return pane;
    }

    public void setLevel(String level) {
        this.level.setText(this.level.getText() + level);
    }

    public void setStage(Stage primaryStage) {
    }

    @FXML
    public void start(KeyEvent k) throws IOException{
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        if(k.getCode() == KeyCode.R){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../level1.fxml"));
            Pane myPane = loader.load();
            ControllerGame c = loader.getController();
            c.setStage(primaryStage);
            Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
            myScene.getRoot().requestFocus();

            c.p  = new Player(c.player);
            c.setListRemainingLife();

            Animation animation = new Animation(pane.getPrefWidth(), c.p, c, myPane);
            animation.start();

            primaryStage.setScene(myScene);
            primaryStage.show();
        }

    }
}
