package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import Model.Player;

public class ControllerEnd {

	//////////////////////////// ATTRIBUTES /////////////////////////////
	@FXML ToggleButton playAgain;
	@FXML ToggleButton quit;

    @FXML Text levelNumber;
    @FXML Text scoreNumber;
    
    private Player player;
    private Stage stage;
    
	//////////////////////////// METHODS /////////////////////////////
    public void setStage(Stage primaryStage) { this.stage = primaryStage; }
    public void setPlayer(Player p) { this.player = p; }

    /**
     * setScoreNumber(): display the score of the player
     * @param score String, the final score of the player
     */
    public void setScoreNumber(String score) {
        this.scoreNumber.setText(score);
    }

    /**
     * setLevelNumber(): display the level of the player
     * @param level String, the final level of the player
     */
    public void setLevelNumber(String level) {
        this.levelNumber.setText(level);
    }

    
    @FXML public void start(KeyEvent k) throws IOException {
    	if(k.getCode().equals(KeyCode.UP)) {
    		playAgain.setSelected(true);
    		playAgain.setStyle("-fx-background-color: transparent; -fx-border-color: orange;");
    		quit.setSelected(false);
    		quit.setStyle("-fx-background-color: transparent;");
    		
    	}else if(k.getCode().equals(KeyCode.DOWN)) {
    		quit.setSelected(true);
    		quit.setStyle("-fx-background-color: transparent; -fx-border-color: orange;");
    		playAgain.setSelected(false);
    		playAgain.setStyle("-fx-background-color: transparent;");

    	}else if(k.getCode().equals(KeyCode.ENTER)) {
    		if(playAgain.isSelected()) {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/welcomeGame.fxml"));
    			Pane myPane = loader.load();                
    			Scene welcome = new Scene(myPane, myPane.getPrefWidth(), myPane.getPrefHeight());
                welcome.getRoot().requestFocus();
                
                //TODO: save best score doesn't work, may be because call new ControllerWelcome each time ??
                ControllerWelcome controllerWelcome = loader.getController();
                System.out.println("best score " + Integer. parseInt(controllerWelcome.getBestScore().getText()));
                System.out.println("player score " + player.getScore());

                if(Integer. parseInt(controllerWelcome.getBestScore().getText()) < player.getScore()) {
                	controllerWelcome.setBestScore(Integer.toString(player.getScore()));
                }
                
                stage.setResizable(false);
                stage.setTitle("Virus Game");
                stage.setScene(welcome);
                stage.show();
                
    		}else if(quit.isSelected()) {
                stage.close();
    		}
    	}
    }
    

}
