package Controller;

import Model.Score;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;

public class ControllerRules {
    @FXML Pane pane;

    @FXML
    public void goBack(KeyEvent k) throws IOException {
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        if(k.getCode().equals(KeyCode.ENTER)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/welcomeGame.fxml"));
            Pane start = loader.load();
            Scene welcome = new Scene(start,start.getPrefWidth(),start.getPrefHeight());
            welcome.getRoot().requestFocus();

            Score score = new Score();

            ControllerWelcome controllerWelcome = loader.getController();
            controllerWelcome.setBestScoreText(Integer.toString(score.getBestScore()));
            controllerWelcome.setScore(score);

            primaryStage.setResizable(false);
            primaryStage.setTitle("Virus Game");
            primaryStage.setScene(welcome);
            primaryStage.show();
        }
    }
}
