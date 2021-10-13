package Controller;

import Model.Animation;
import Model.Player;
import Model.Score;
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

public class ControllerWinLevel {

    //////////////////////////// ATTRIBUTES /////////////////////////////
	@FXML ToggleButton nextLevel;
	@FXML ToggleButton quit;
	
    @FXML Text scoreNumber;
    @FXML Text levelNumber;
    @FXML Pane pane;
    
    private Stage stage;
    private Player player;
    private Score score;
    private int nextLevelNumber;


    //////////////////////////// METHODS /////////////////////////////
    public Pane getPane() { return pane; }
    public void setStage(Stage primaryStage) { this.stage = primaryStage; }
    public void setPlayer(Player p) { this.player = p;}
    public void setNextLevelNumber(int i) { this.nextLevelNumber = i; }
    public Score getScore() { return score; }
    public void setScore(Score score) { this.score = score; }

    /**
     * setScore(): display the score of the player
     * @param score String, the final score of the player
     */
    public void setScoreNumber(String score) {
        this.scoreNumber.setText(score);
    }

    /**
     * setNextLevel(): display the next level
     * @param level
     */
    public void setLevelNumber(String level) {
        this.levelNumber.setText(level);
    }


    /*
    @FXML public void start(KeyEvent k) throws IOException{
        if(k.getCode() == KeyCode.R){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/level" + this.nextLevelNumber + ".fxml"));
            Pane myPane = loader.load();

            ControllerLevel controllerLevel = loader.getController();

            Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
            myScene.getRoot().requestFocus();

            controllerLevel.setStage(stage);
            controllerLevel.setPane(myPane);
            controllerLevel.setPlayer(new Player(controllerLevel.imagePlayer));
            controllerLevel.getPlayer().setScore(player.getScore());
            controllerLevel.setScore(controllerLevel.getPlayer().getScore());
            controllerLevel.setListRemainingLife();
            controllerLevel.setListRemainingMunition();
            controllerLevel.setListViruses();

            Animation animation = new Animation(pane.getPrefWidth(), pane.getPrefHeight(),
                    					controllerLevel, myPane, "../Images/jet_lv" + nextLevelNumber + ".png");
            animation.start();

            this.stage.setScene(myScene);
            this.stage.show();
        }

    }*/
    
   @FXML public void startNextLevel(KeyEvent k) throws IOException {
    	if(k.getCode().equals(KeyCode.UP)) {
    		nextLevel.setSelected(true);
    		nextLevel.setStyle("-fx-background-color: transparent; -fx-border-color: orange;");
    		quit.setSelected(false);
    		quit.setStyle("-fx-background-color: transparent;");
    		
    	}else if(k.getCode().equals(KeyCode.DOWN)) {
    		quit.setSelected(true);
    		quit.setStyle("-fx-background-color: transparent; -fx-border-color: orange;");
    		nextLevel.setSelected(false);
    		nextLevel.setStyle("-fx-background-color: transparent;");

    	}else if(k.getCode().equals(KeyCode.ENTER)) {
    		if(nextLevel.isSelected()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/level" + this.nextLevelNumber + ".fxml"));
                Pane myPane = loader.load();

                ControllerLevel controllerLevel = loader.getController();

                Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
                myScene.getRoot().requestFocus();

                controllerLevel.setStage(stage);
                controllerLevel.setPane(myPane);
                controllerLevel.setPlayer(new Player(controllerLevel.imagePlayer));
                //controllerLevel.getPlayer().setScore(player.getScore());
                controllerLevel.setScore(score);
                controllerLevel.setScoreText(score.getCurrentScore());
                controllerLevel.setListRemainingLife();
                controllerLevel.setListRemainingMunition();
                controllerLevel.setListViruses();

                Animation animation = new Animation(pane.getPrefWidth(), pane.getPrefHeight(),
                        					controllerLevel, myPane, "../Images/jet_lv" + nextLevelNumber + ".png", score);
                animation.start();

                this.stage.setScene(myScene);
                this.stage.show();
                
    		}else if(quit.isSelected()) {
                stage.close();
    		}
    	}
    }

   
   
}