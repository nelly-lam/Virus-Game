package Controller;


import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        else if (k.getCode().equals(KeyCode.R)){
            System.out.println("rules");
            //faire un fxml rules
        }
        else if (k.getCode().equals(KeyCode.S)){
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