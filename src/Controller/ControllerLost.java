package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLost {

	//////////////////////////// ATTRIBUTES /////////////////////////////
    @FXML Text level;
    @FXML Text score;
    @FXML Pane pane;
    private Stage stage;
    
	//////////////////////////// METHODS /////////////////////////////
    public void setStage(Stage primaryStage) { this.stage = primaryStage; }

    /**
     * setPoint(): display the score of the player
     * @param score String, the final score of the player
     */
    public void setScore(String score) {
        this.score.setText(this.score.getText() + score);
    }

    /**
     * setLevel(): display the level of the player
     * @param level String, the final level of the player
     */
    public void setLevel(String level) {
        this.level.setText(this.level.getText() + level);
    }


    @FXML public void start(KeyEvent k) throws IOException {
        if (k.getCode().equals(KeyCode.Q)) {
            stage.close();
        }
        else if(k.getCode().equals(KeyCode.S)){
            Pane start = (Pane) FXMLLoader.load(getClass().getResource("../FXML/welcome.fxml"));
            Scene welcome = new Scene(start,start.getPrefWidth(),start.getPrefHeight());
            welcome.getRoot().requestFocus();
            stage.setResizable(false);
            stage.setTitle("Virus Game");
            stage.setScene(welcome);
            stage.show();
        }
    }
}
