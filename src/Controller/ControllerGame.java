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


public class ControllerGame {
	
	///////////////////// ATTRIBUTES /////////////////////////

	private Stage stage;
	
    @FXML
    ImageView player;



    @FXML
    Pane road;

    Player p;
    
    Level1 level1;


    @FXML  ImageView virus1;
    @FXML  ImageView virus2;
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
    

    @FXML
    Text score;
    
    private ArrayList<ImageView> listRemainingMunition = new ArrayList<ImageView>();
    
    
    @FXML ImageView life1;
    @FXML ImageView life2;
    @FXML ImageView life3;
    
    private ArrayList<ImageView> listRemainingLife = new ArrayList<ImageView>();

    
    ///////////////////// METHODS ///////////////////////

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
	            Jet jet = new Jet(p.getPosX()+36, p.getPosY()-36,getClass().getResource("../Images/jet.png"));
	            road.getChildren().add(jet.getImageJet());
	            	            
	            p.getListJet().addJet(jet);
	            
	            /* remove one munition from the available ones of the player */
	            p.setAvailableJet(p.getAvailableJet()-1);
	            
	            /* delete a munition from the bottom part */
	            listRemainingMunition.get(p.getAvailableJet()).setVisible(false);

        	}  
        }else if(k.getCode().equals(KeyCode.Q)) {
        	stage.close();
        }
        else {
            if(p.getImagePlayer().equals(p.getImageG())) {
                if (k.getCode().equals(KeyCode.LEFT) && p.getPosX()-10 >= 0) {
                    p.setImagePlayer(p.getImageD());
                    p.setPosX(p.getPosX() - 10);
                }
                else if (k.getCode().equals(KeyCode.RIGHT) && p.getPosX()+10 < 590) {
                    p.setImagePlayer(p.getImageD());
                    p.setPosX(p.getPosX() + 10);
                }

            }
            else {
                if(k.getCode().equals(KeyCode.LEFT) && p.getPosX()-10 >= 0){
                    p.setImagePlayer(p.getImageG());
                    p.setPosX(p.getPosX() - 10);
                }
                else if (k.getCode().equals(KeyCode.RIGHT) && p.getPosX()+10 < 590){
                    p.setImagePlayer(p.getImageG());
                    p.setPosX(p.getPosX() + 10);
                }
            }
        }
    }

    
    public void removeLife(){
        listRemainingLife.get(p.getLife()-1).setVisible(false);
        p.setLife(p.getLife() -1);
    }

    

    public void setScore(int point){
        int t = Integer.parseInt(score.getText()) + point ;
        score.setText(String.valueOf(t));
        p.setScore(t);
    }

	public Stage getStage() { return stage; }
	public void setStage(Stage stage) { this.stage = stage; }
	
	public ArrayList<ImageView> getListRemainingMunition() { return listRemainingMunition; }
	public void setListRemainingMunition(ArrayList<ImageView> list) { listRemainingMunition = list; }
	
	public void addMunitionToList() {
		this.listRemainingMunition.get(p.getAvailableJet()).setVisible(true);
		p.setAvailableJet(p.getAvailableJet()+1);
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

    public ImageView getVirus4() {
        return virus4;
    }

    public ImageView getVirus5() {
        return virus5;
    }

    public ImageView getVirus6() {
        return virus6;
    }

    public ImageView getVirus7() {
        return virus7;
    }

    public Pane getRoad() {
        return road;
    }

    public void setListRemainingLife(){
        listRemainingLife.add(life1);
        listRemainingLife.add(life2);
        listRemainingLife.add(life3);

    }

    public ArrayList<ImageView> getListRemainingLife() {
        return listRemainingLife;
    }


    public void launchLoosePane() throws IOException {
        Stage primaryStage = (Stage) road.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../lose.fxml"));
        Pane myPane = loader.load();
        //ControllerGame c = loader.getController();
       // c.setStage(primaryStage);
        Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
        myScene.getRoot().requestFocus();
        primaryStage.setScene(myScene);
        primaryStage.show();
    }


}
