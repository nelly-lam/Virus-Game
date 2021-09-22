package Controller;

import com.sun.source.doctree.AttributeTree;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAcceuil  {

    @FXML Button button;

/*
       Stage primaryStage = (Stage) acceuil.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Fxml/dimension.fxml"));
        Pane myPane = loader.load();
        Controller c = loader.getController();
        c.setLongueur(200);
        c.setLarg(200);
        Scene myScene = new Scene(myPane, myPane.getPrefWidth()*1.5,myPane.getPrefHeight()*1.5);
        primaryStage.setScene(myScene);
        primaryStage.setX(primaryStage.getX()-250);
        primaryStage.setY(primaryStage.getY()-75);
        primaryStage.show();*/

    @FXML
    public void here(KeyEvent keyEvent) throws IOException {
        Stage primaryStage = (Stage) button.getScene().getWindow();
        if(keyEvent.getCode().equals(KeyCode.SPACE)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../game.fxml"));
            Pane myPane = loader.load();
            //Controller c = loader.getController();
            Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
            primaryStage.setScene(myScene);
            primaryStage.show();
        }
        else if(keyEvent.getCode().equals(KeyCode.Q)){
            primaryStage.close();
        }
    }
}