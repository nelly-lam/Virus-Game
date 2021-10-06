package Controller;


import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerWelcome  {

    @FXML Pane pane;

    @FXML public void start(KeyEvent k) throws IOException {
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        if(k.getCode().equals(KeyCode.Q)){
            primaryStage.close();
        }
        else if (k.getCode().equals(KeyCode.R)){
            System.out.println("rules");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../rules.fxml"));
            Pane myPane = loader.load();
//            ControllerLevel1 controllerLevel1 = loader.getController();

            Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
            myScene.getRoot().requestFocus();
            primaryStage.setScene(myScene);
            primaryStage.show();
        }
        else if (k.getCode().equals(KeyCode.S)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../level1.fxml"));
            Pane myPane = loader.load();
            ControllerLevel1 controllerLevel1 = loader.getController();
            
            Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
            myScene.getRoot().requestFocus();

            controllerLevel1.player  = new Player(controllerLevel1.imagePlayer);
            controllerLevel1.setListRemainingLife();
            controllerLevel1.setListRemainingMunition();

            Animation animation = new Animation(pane.getPrefWidth(), controllerLevel1.player, controllerLevel1, myPane);
            animation.start();

            primaryStage.setScene(myScene);
            primaryStage.show();
        }

    }

    @FXML public void goBack(KeyEvent k) throws IOException{
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        if(k.getCode() == KeyCode.R){
            Pane start = (Pane) FXMLLoader.load(getClass().getResource("../welcome.fxml"));
            Scene welcome = new Scene(start,start.getPrefWidth(),start.getPrefHeight());
            welcome.getRoot().requestFocus();

            primaryStage.setResizable(false);
          //  primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setTitle("Virus Game");
            primaryStage.setScene(welcome);
            primaryStage.show();
        }
    }
}