package Controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAcceuil  {

    @FXML Button button;



    @FXML
    public void here(KeyEvent keyEvent) throws IOException {
        //System.out.println(keyEvent.getCode() );
        Stage primaryStage = (Stage) button.getScene().getWindow();
        if(keyEvent.getCode().equals(KeyCode.SPACE)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../game.fxml"));
            Pane myPane = loader.load();
            ControllerMain c = loader.getController();
           // System.out.println(c.player.getImage().getHeight());
            Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
            primaryStage.setScene(myScene);
            primaryStage.show();
        }
        else if(keyEvent.getCode().equals(KeyCode.Q)){
            primaryStage.close();
        }
    }

    @FXML public void test(KeyEvent k) {
        System.out.println(k.getCode() );
       /* Stage primaryStage = (Stage) button.getScene().getWindow();
        if(k.getCode().equals(KeyCode.Q)){
            primaryStage.close();
        }*/
        /*else if(k.getCharacter().equals(" ")){
            System.out.println("here");
        }*/
        /*Stage primaryStage = (Stage) button.getScene().getWindow();
        if(k.getCode().equals(KeyCode.SPACE)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../game.fxml"));
            Pane myPane = loader.load();
            ControllerMain c = loader.getController();
            // System.out.println(c.player.getImage().getHeight());
            Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
            primaryStage.setScene(myScene);
            primaryStage.show();
        }
        else if(k.getCode().equals(KeyCode.Q)){
            primaryStage.close();
        }*/
    }
}