package Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ControllerLevel1 {
	
	///////////////////// ATTRIBUTES /////////////////////////
	private Stage stage;
	
    @FXML ImageView imagePlayer;

    @FXML Pane road;
    
    Player player;

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
    

    @FXML Text score;
    
    private ArrayList<ImageView> listRemainingMunition = new ArrayList<ImageView>();
    
    
    @FXML ImageView life1;
    @FXML ImageView life2;
    @FXML ImageView life3;
    
    private ArrayList<ImageView> listRemainingLife = new ArrayList<ImageView>();

    
    ///////////////////// METHODS ///////////////////////

    @FXML protected void play(KeyEvent k){

        if(k.getCode().equals(KeyCode.SPACE)){
        	if(player.getAvailableJet() > 0) {
        		
        		//display the image of the player shooting
	            player.setImagePlayer(player.getImageShoot());
	            
	            //launch a jet
	            Jet jet = new Jet(player.getPosX()+36, player.getPosY()-36,getClass().getResource("../Images/jet.png"));
	            road.getChildren().add(jet.getImageJet());
	            	            
	            player.getListJet().addJet(jet);
	            
	            //remove one munition from the available ones of the player
	            player.setAvailableJet(player.getAvailableJet()-1);
	            
	            //delete a munition from the bottom part
	            listRemainingMunition.get(player.getAvailableJet()).setVisible(false);

        	}  
        }else if(k.getCode().equals(KeyCode.Q)) {
        	stage.close();
        }
        else {
        	//if the player is on his left foot
            if(player.getImagePlayer().equals(player.getImageG())) {
            	
            	//if the player is on the road + pressed LEFT, move to the left
                if (k.getCode().equals(KeyCode.LEFT) && player.getPosX()-10 >= 0) {
                    player.setImagePlayer(player.getImageD());
                    player.setPosX(player.getPosX() - 10);
                    
                }//if the player is on the road + pressed RIGHT, move to the right
                else if (k.getCode().equals(KeyCode.RIGHT) && player.getPosX()+10 < 590) {
                    player.setImagePlayer(player.getImageD());
                    player.setPosX(player.getPosX() + 10);
                }

            }
            else {//if the player is on his right foot
            	//same
                if(k.getCode().equals(KeyCode.LEFT) && player.getPosX()-10 >= 0){
                    player.setImagePlayer(player.getImageG());
                    player.setPosX(player.getPosX() - 10);
                }//same
                else if (k.getCode().equals(KeyCode.RIGHT) && player.getPosX()+10 < 590){
                    player.setImagePlayer(player.getImageG());
                    player.setPosX(player.getPosX() + 10);
                }
            }
        }
    }

    /**
     * removeLife(): remove one life to the player
     */
    public void removeLife(){
        listRemainingLife.get(player.getLife()-1).setVisible(false);
        player.setLife(player.getLife() -1);
    }

    
    /**
     * setScore(): set the new score of the player
     * @param point int, the score that is added to the current score of the player
     */
    public void setScore(int point){
        int t = Integer.parseInt(score.getText()) + point ;
        score.setText(String.valueOf(t));
        player.setScore(t);
    }
	
	/**
	 * addMunitionToList(): add one munition to the player's list of munition
	 */
	public void addMunitionToList() {
		this.listRemainingMunition.get(player.getAvailableJet()).setVisible(true);
		player.setAvailableJet(player.getAvailableJet()+1);
	}

    public ImageView getVirus1() {
        return virus1;
    }

    public ImageView getVirus2() {
        return virus2;
    }

    public ImageView getVirus3() {
        return virus3;
    }

    public ImageView getVirus4() { return virus4; }
    public ImageView getVirus5() { return virus5; }
    public ImageView getVirus6() { return virus6; }
    public ImageView getVirus7() { return virus7; }

    public Pane getRoad() { return road; }

    /**
     * setListRemainingLife(): recharge all three life to the player
     */
    public void setListRemainingLife(){
        listRemainingLife.add(life1);
        listRemainingLife.add(life2);
        listRemainingLife.add(life3);
    }
    
    /**
     * setListRemainingMunition(): recharge all ten munition to the player
     */
    public void setListRemainingMunition() {
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
    }

}
