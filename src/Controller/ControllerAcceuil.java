package Controller;


import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAcceuil  {

    @FXML Pane pane;

    @FXML public void start(KeyEvent k) throws IOException {
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        if(k.getCode().equals(KeyCode.Q)){
            primaryStage.close();
        }
        else if (k.getCode().equals(KeyCode.SPACE)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../game.fxml"));
            Pane myPane = loader.load();
            ControllerMain c = loader.getController();
            Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
            myScene.getRoot().requestFocus();
            c.p  = new Player(c.player);
            primaryStage.setScene(myScene);
            primaryStage.show();
        }

    }
}