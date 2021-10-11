package View;

import Controller.ControllerWelcome;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class View extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/welcomeGame.fxml"));
        Pane start = loader.load();
        Scene welcome = new Scene(start,start.getPrefWidth(),start.getPrefHeight());
        welcome.getRoot().requestFocus();
       
        ControllerWelcome controllerWelcome = loader.getController();
        controllerWelcome.setScoreMax(0);
        
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Virus Game");
        primaryStage.setScene(welcome);
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);

    }
}

