package Controller;


import Model.Player;
import Model.TimerTaskGame;
import Model.Virus;
import Model.VirusCloud;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;

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
            ControllerGame c = loader.getController();
            Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
            myScene.getRoot().requestFocus();
            
            
            c.p  = new Player(c.player);
            c.road.getChildren().add(c.p.getHitbox());
            
            /*Instanciate viruses*/
            c.virusCloud = new VirusCloud();
            
        	Virus virus = new Virus(c.virus1);
            c.virusCloud.addVirus(virus);
            virus = new Virus(c.virus2);
            c.virusCloud.addVirus(virus);
            virus = new Virus(c.virus3);
            c.virusCloud.addVirus(virus);
            virus = new Virus(c.virus4);
            c.virusCloud.addVirus(virus);
            virus = new Virus(c.virus5);
            c.virusCloud.addVirus(virus);
            virus = new Virus(c.virus6);
            c.virusCloud.addVirus(virus);
            virus = new Virus(c.virus7);
            c.virusCloud.addVirus(virus);
            
            
            long delayTaskViruses = 0;
            long periodTaskViruses = 500;
        	TimerTaskGame task = new TimerTaskGame(c.virusCloud, pane.getPrefWidth(), c.p);
        	Timer timerViruses = new Timer();
        	timerViruses.schedule(task, delayTaskViruses, periodTaskViruses);
        	
            
            primaryStage.setScene(myScene);
            primaryStage.show();
        }

    }
}