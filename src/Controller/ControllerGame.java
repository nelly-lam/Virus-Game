package Controller;


import java.util.ArrayList;

import Model.Jet;
import Model.Player;
import Model.VirusCloud;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class ControllerGame {
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
    
    @FXML ImageView munition1;
    @FXML ImageView munition2;
    @FXML ImageView munition3;
    @FXML ImageView munition4;
    @FXML ImageView munition5;
    @FXML ImageView munition6;
    @FXML ImageView munition7;
    @FXML ImageView munition8;
    @FXML ImageView munition9;
    @FXML ImageView munition10;
    
    private ArrayList<ImageView> listRemainingMunition = new ArrayList<ImageView>();
    
    
    @FXML ImageView life1;
    @FXML ImageView life2;
    @FXML ImageView life3;
    
    private ArrayList<ImageView> listRemainingLife = new ArrayList<ImageView>();

    
    /////////////////////// CONSTRUCTOR //////////////////////////


    @FXML protected void play(KeyEvent k){
    	listRemainingMunition.add(munition1);
    	listRemainingMunition.add(munition2);
    	listRemainingMunition.add(munition3);
    	listRemainingMunition.add(munition4);
    	listRemainingMunition.add(munition5);
    	listRemainingMunition.add(munition6);
    	listRemainingMunition.add(munition7);
    	listRemainingMunition.add(munition8);
    	listRemainingMunition.add(munition9);
    	listRemainingMunition.add(munition10);
    	
        if(k.getCode().equals(KeyCode.SPACE)){
        	if(p.getAvailableJet() > 0) {
        		
        		/* display the image of the player shooting*/
	            p.setImagePlayer(p.getImageShoot());
	            
	            /* launch a jet */
	            Jet jet = new Jet(p.getPosX()+36, p.getPosY()-36);
	            road.getChildren().add(jet.getImageJet());
	            
	            p.getListJet().addJet(jet);
	            p.setAvailableJet(p.getAvailableJet()-1);

	            /* delete a munition from the bottom part */
	            listRemainingMunition.get(p.getAvailableJet()).setVisible(false);
	            
        	}  
        }else {
            if(p.getImagePlayer().equals(p.getImageG())) {
                if (k.getCode().equals(KeyCode.LEFT) && p.getPosX()-10 >= -53.0) {
                    p.setImagePlayer(p.getImageD());
                    p.setPosX(p.getPosX() - 10);
                    
                    /* update the position of the hitbox */
                    p.getHitbox().setX(p.getPosX());
                    p.getHitbox().setY(p.getPosY());
                }
                else if (k.getCode().equals(KeyCode.RIGHT) && p.getPosX()+10 < 514) {
                    p.setImagePlayer(p.getImageD());
                    p.setPosX(p.getPosX() + 10);
                    
                    /* update the position of the hitbox */
                    p.getHitbox().setX(p.getPosX());
                    p.getHitbox().setY(p.getPosY());
                }

            }
            else {
                if(k.getCode().equals(KeyCode.LEFT) && p.getPosX()-10 >= -53.0){
                    p.setImagePlayer(p.getImageG());
                    p.setPosX(p.getPosX() - 10);
                    
                    /* update the position of the hitbox */
                    p.getHitbox().setX(p.getPosX());
                    p.getHitbox().setY(p.getPosY());
                }
                else if (k.getCode().equals(KeyCode.RIGHT) && p.getPosX()+10 < 514){
                    p.setImagePlayer(p.getImageG());
                    p.setPosX(p.getPosX() + 10);
                    
                    /* update the position of the hitbox */
                    p.getHitbox().setX(p.getPosX());
                    p.getHitbox().setY(p.getPosY());
                }
            }
        }
    }
    
    
    
}
