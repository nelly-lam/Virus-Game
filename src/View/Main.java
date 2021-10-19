package View;

import Controller.ControllerWelcome;
import Model.Score;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/welcomeGame.fxml"));
        Pane paneWelcomeGame = loader.load();
        Scene sceneWelcomeGame = new Scene(paneWelcomeGame, paneWelcomeGame.getPrefWidth(), paneWelcomeGame.getPrefHeight());
        sceneWelcomeGame.getRoot().requestFocus();

        //score of the entire game
        Score score = new Score();

        ControllerWelcome controllerWelcome = loader.getController();
        controllerWelcome.setBestScoreText(Integer.toString(score.getBestScore()));
        controllerWelcome.setScore(score);
        
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Virus Game");
        primaryStage.setScene(sceneWelcomeGame);
        primaryStage.show();
    }


    public static void main(String[] args){
        launch(args);
    }
}

