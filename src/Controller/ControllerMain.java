package Controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ControllerMain {
    @FXML
    ImageView player;
    @FXML
    Pane road;

    @FXML
    StackPane stackpane;


    @FXML protected void test(KeyEvent k){
        System.out.println("here");
    }


}