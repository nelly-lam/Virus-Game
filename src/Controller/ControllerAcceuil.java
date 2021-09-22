package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class ControllerAcceuil {

    @FXML private Button start;
    @FXML private Button exit;
    private Boolean close = false;




    @FXML
    protected void Start(KeyEvent e) throws Exception{
           if(e.getCode().equals(KeyCode.DOWN)){
                start.setStyle("-fx-border-color:black");
                start.setStyle("-fx-background-color:black");
                close = true;
        }
           else
            System.out.println("hello");



        //System.out.println("start pressed");
    //    Stage primaryStage = (Stage) Start.getScene().getWindow();
       // FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/dimension.fxml"));
       // Pane myPane = loader.load();
       // Controller c = loader.getController();
       // c.setLongueur(200);
       // c.setLarg(200);
       // Scene myScene = new Scene(myPane, myPane.getPrefWidth()*1.5,myPane.getPrefHeight()*1.5);
        //primaryStage.setScene(myScene);
        //primaryStage.setX(primaryStage.getX()-250);
        //primaryStage.setY(primaryStage.getY()-75);
        //primaryStage.show();
    }

    @FXML
    protected void Exit() throws Exception{
        if(close) {
            exit.setStyle("-fx-border-color: yellow; -fx-background-color:black");
            //exit.setStyle("-fx-background-color:black");
            Stage primaryStage = (Stage) exit.getScene().getWindow();
            System.out.println("here");
        }
       // primaryStage.close();
    }
}
