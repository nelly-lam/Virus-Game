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

public class ControllerWelcome  {

    //////////////////////////// ATTRIBUTES /////////////////////////////
    @FXML Pane pane;

    //////////////////////////// METHODS /////////////////////////////

    @FXML public void start(KeyEvent k) throws IOException {
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        if(k.getCode().equals(KeyCode.Q)){
            primaryStage.close();
        }
        else if (k.getCode().equals(KeyCode.R)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/rules.fxml"));
            Pane myPane = loader.load();

            Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
            myScene.getRoot().requestFocus();

            primaryStage.setScene(myScene);
            primaryStage.show();
        }
        else if (k.getCode().equals(KeyCode.S)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/level1.fxml"));
            Pane paneLevel1 = loader.load();
            ControllerLevel controllerLevel = loader.getController();

            Scene myScene = new Scene(paneLevel1, paneLevel1.getPrefWidth(), paneLevel1.getPrefHeight());
            myScene.getRoot().requestFocus();

            controllerLevel.setStage(primaryStage);
            controllerLevel.setPane(paneLevel1);
            controllerLevel.setPlayer(new Player(controllerLevel.imagePlayer));
            controllerLevel.setListRemainingLife();
            controllerLevel.setListRemainingMunition();
            controllerLevel.setListViruses();

            Animation animation = new Animation(pane.getPrefWidth(), pane.getPrefHeight(),
                    controllerLevel, paneLevel1,"../Images/jet_lv1.png");
            animation.start();

            primaryStage.setScene(myScene);
            primaryStage.show();
        }

    }

    @FXML public void goBack(KeyEvent k) throws IOException{
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        if(k.getCode() == KeyCode.R){
            Pane start = (Pane) FXMLLoader.load(getClass().getResource("../FXML/welcome.fxml"));
            Scene welcome = new Scene(start,start.getPrefWidth(),start.getPrefHeight());
            welcome.getRoot().requestFocus();

            primaryStage.setResizable(false);
            primaryStage.setTitle("Virus Game");
            primaryStage.setScene(welcome);
            primaryStage.show();
        }
    }
}