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
    @FXML Text currentLevelText;
    @FXML Text scoreText;
    @FXML Text nextLevelText;
    @FXML Pane pane;
    Stage stage;
    Player player;
    private int nextLevel;


    //////////////////////////// METHODS /////////////////////////////
    public Pane getPane() { return pane; }
    public void setStage(Stage primaryStage) { this.stage = primaryStage; }
    public void setNextLevel(int i) { this.nextLevel = i; }
    public void setPlayer(Player p) { this.player = p;}

    /**
     * setScore(): display the score of the player
     * @param score String, the final score of the player
     */
    public void setScoreText(String score) {
        this.scoreText.setText(this.scoreText.getText() + score);
    }

    /**
     * setNextLevel(): display the next level
     * @param level
     */
    public void setNextLevelText(String level) {
        this.nextLevelText.setText(this.nextLevelText.getText() + level);
    }

    /**
     * setCurrentLevel() : display the current level of the player
     * @param level String, the final level of the player
     */
    public void setCurrentLevelText(String level) {
        this.currentLevelText.setText(this.currentLevelText.getText() + level);
    }


    @FXML
    public void start(KeyEvent k) throws IOException{
        //TODO: get the level the player is currently at to charge the good fxml file
        if(k.getCode() == KeyCode.R){
            //TODO: change to level2.fxml

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/level" + this.nextLevel + ".fxml"));
            Pane myPane = loader.load();

            ControllerLevel controllerLevel = loader.getController();

            Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
            myScene.getRoot().requestFocus();

            controllerLevel.setStage(stage);
            controllerLevel.setPane(myPane);
            controllerLevel.setPlayer(new Player(controllerLevel.imagePlayer));
            controllerLevel.setListRemainingLife();
            controllerLevel.setListRemainingMunition();
            controllerLevel.setListViruses();

            Animation animation = new Animation(pane.getPrefWidth(), pane.getPrefHeight(),
                    controllerLevel, myPane, "../Images/jet_lv" + nextLevel + ".png");
            animation.start();

            this.stage.setScene(myScene);
            this.stage.show();
        }

    }
}