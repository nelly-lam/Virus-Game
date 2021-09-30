package Controller;


import Model.Animation;
import Model.Player;
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
            c.setStage(primaryStage);
            Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
            myScene.getRoot().requestFocus();
            
            
            c.p  = new Player(c.player);

            
            /*Instanciate viruses*/
            c.virusCloud = new VirusCloud();
            
        	Virus virus = new Virus(c.virus1,20);
            c.virusCloud.addVirus(virus);
            virus = new Virus(c.virus2,20);
            c.virusCloud.addVirus(virus);
            virus = new Virus(c.virus3,20);
            c.virusCloud.addVirus(virus);
            virus = new Virus(c.virus4,20);
            c.virusCloud.addVirus(virus);
            virus = new Virus(c.virus5,20);
            c.virusCloud.addVirus(virus);
            virus = new Virus(c.virus6,20);
            c.virusCloud.addVirus(virus);
            virus = new Virus(c.virus7,20);
            c.virusCloud.addVirus(virus);
            

            
            /* run a timer */
            /*
            long delayTaskViruses = 0;
            long periodTaskViruses = 500;
        	TimerTaskGame task = new TimerTaskGame(c.virusCloud, pane.getPrefWidth(), c.p, c);
        	Timer timerViruses = new Timer();
        	timerViruses.schedule(task, delayTaskViruses, periodTaskViruses);
        	*/
            
            Animation animation = new Animation(c.virusCloud, pane.getPrefWidth(), c.p, c, myPane);
            animation.start();
        	
            
            primaryStage.setScene(myScene);
            primaryStage.show();
        }

    }
}