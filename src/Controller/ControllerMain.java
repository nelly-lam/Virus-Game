package Controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ControllerMain {
    @FXML
    ImageView player;
    @FXML
    Pane road;




    @FXML protected void play(KeyEvent k){
        if(player.getImage().getUrl().equals("file:/C:/Users/minic/OneDrive/Documents/M1/Advanced%20Programming%20of%20Interactive%20Systems/Projet/Virus-Game/out/production/Virus-Game/Images/player.png")){
            if(k.getCode().equals(KeyCode.LEFT)){
                player.setLayoutX(player.getLayoutX() - 20);
                player.setImage(new Image("file:/C:/Users/minic/OneDrive/Documents/M1/Advanced%20Programming%20of%20Interactive%20Systems/Projet/Virus-Game/out/production/Virus-Game/Images/playerD.png"));

            }
            else if(k.getCode().equals(KeyCode.RIGHT)){
                player.setLayoutX(player.getLayoutX() + 20);
                player.setImage(new Image("file:/C:/Users/minic/OneDrive/Documents/M1/Advanced%20Programming%20of%20Interactive%20Systems/Projet/Virus-Game/out/production/Virus-Game/Images/playerD.png"));
            }
            else if(k.getCode().equals(KeyCode.SPACE)){
                player.setImage(new Image("file:/C:/Users/minic/OneDrive/Documents/M1/Advanced%20Programming%20of%20Interactive%20Systems/Projet/Virus-Game/out/production/Virus-Game/Images/playerShooting.png"));
            }
        }
        else {
            if (k.getCode().equals(KeyCode.LEFT)) {
                player.setLayoutX(player.getLayoutX() - 20);
                player.setImage(new Image("file:/C:/Users/minic/OneDrive/Documents/M1/Advanced%20Programming%20of%20Interactive%20Systems/Projet/Virus-Game/out/production/Virus-Game/Images/player.png"));
            }
            else if (k.getCode().equals(KeyCode.RIGHT)) {
                player.setLayoutX(player.getLayoutX() + 20);
                player.setImage(new Image("file:/C:/Users/minic/OneDrive/Documents/M1/Advanced%20Programming%20of%20Interactive%20Systems/Projet/Virus-Game/out/production/Virus-Game/Images/player.png"));
            }
            else if(k.getCode().equals(KeyCode.SPACE)){
                player.setImage(new Image("file:/C:/Users/minic/OneDrive/Documents/M1/Advanced%20Programming%20of%20Interactive%20Systems/Projet/Virus-Game/out/production/Virus-Game/Images/playerShooting.png"));
            }
        }

    }
}
