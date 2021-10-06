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
	
	//////////////////////////// ATTRIBUTES /////////////////////////////
    @FXML Text won;
    @FXML Text point;
    @FXML Text level;
    @FXML Pane pane;
    Stage stage;

	//////////////////////////// METHODS /////////////////////////////
    public Pane getPane() { return pane; }
    public void setStage(Stage primaryStage) { this.stage = primaryStage; }
    
    /**
     * setPoint(): display the score of the player
     * @param score String, the final score of the player
     */
    public void setScore(String score) {
        this.point.setText(this.point.getText() + score);
    }


    /**
     * setNextLevel(): display the next level
     * @param level 
     */
    public void setNextLevel(String level) {
        this.level.setText(this.level.getText() + level);
    }
    
    /**
     * setWon() : display the level of the player
     * @param level String, the final level of the player
     */
    public void setLevel(String level) {
        this.won.setText(this.won.getText() + level);
    }


    @FXML
    public void start(KeyEvent k) throws IOException{
    	//TODO: get the level the player is currently at to charge the good fxml file
        if(k.getCode() == KeyCode.R){
        	//TODO; change to level2.fxml
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

            this.stage.setScene(myScene);
            this.stage.show();
        }

    }
}
