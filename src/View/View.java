package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class View extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane start = (Pane) FXMLLoader.load(getClass().getResource("../acceuil.fxml"));
        Scene welcome = new Scene(start,start.getPrefWidth(),start.getPrefHeight());
        welcome.getRoot().requestFocus();
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

