package Controller;

import Model.Player;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class ControllerMain {
    @FXML
    ImageView player;

    @FXML
    Pane road;

    Player p;


    @FXML protected void play(KeyEvent k){
        if(k.getCode().equals(KeyCode.SPACE)){
            p.setImagePlayer(p.getImageShoot());
            ImageView i = new ImageView(new Image("file:/C:/Users/minic/OneDrive/Documents/M1/Advanced%20Programming%20of%20Interactive%20Systems/Projet/Virus-Game/out/production/Virus-Game/Images/jet.png"));
            i.setLayoutX(p.getPosX()+36);
            i.setLayoutY(p.getPosY()-36);
            p.addShoot(i);
            road.getChildren().add(i);
        }
        else {
            if(p.getImagePlayer().equals(p.getImageG())) {
                if (k.getCode().equals(KeyCode.LEFT) && p.getPosX()-10 >= -53.0) {
                    p.setImagePlayer(p.getImageD());
                    p.setPosX(p.getPosX() - 10);
                }
                else if (k.getCode().equals(KeyCode.RIGHT) && p.getPosX()+10 < 514) {
                    p.setImagePlayer(p.getImageD());
                    p.setPosX(p.getPosX() + 10);
                }
            }
            else {
                if(k.getCode().equals(KeyCode.LEFT) && p.getPosX()-10 >= -53.0){
                    p.setImagePlayer(p.getImageG());
                    p.setPosX(p.getPosX() - 10);
                }
                else if (k.getCode().equals(KeyCode.RIGHT) && p.getPosX()+10 < 514){
                    p.setImagePlayer(p.getImageG());
                    p.setPosX(p.getPosX() + 10);
                }
            }
        }
    }
}
