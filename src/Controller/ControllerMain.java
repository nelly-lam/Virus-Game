package Controller;

import java.net.URL;

import Model.Player;
import Model.VirusCloud;
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
    
    VirusCloud virusCloud;
    
	@FXML ImageView virus1;
    @FXML ImageView virus2;
    @FXML ImageView virus3;
    @FXML ImageView virus4;
    @FXML ImageView virus5;
    @FXML ImageView virus6;
    @FXML ImageView virus7;


    @FXML protected void play(KeyEvent k){
        if(k.getCode().equals(KeyCode.SPACE)){
            p.setImagePlayer(p.getImageShoot());
            
            URL imageJeturl = getClass().getResource("../Images/jet.png");
            ImageView i = new ImageView(new Image(imageJeturl.toExternalForm()));
            
            i.setLayoutX(p.getPosX()+36);
            i.setLayoutY(p.getPosY()-36);
            p.addShoot(i);
            road.getChildren().add(i);
            
            System.out.println(virusCloud.getSize());
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
    
    
    protected void setVirus() {
    	
    }
    
    
}
