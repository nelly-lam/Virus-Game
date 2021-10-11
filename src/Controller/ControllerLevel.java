package Controller;

import java.util.ArrayList;

import Model.Jet;
import Model.Player;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * we will have an issue because we need to change the controller for every fxml but in the Animation Class we have a controllerGame parameter who is a
 * ControllerLevel1.
 * So we need to make a class who will handle all of the controller methods that we need to use in animation
 */

public class ControllerLevel {
    protected Pane road;
    protected Stage stage;
    protected Player player;
    @FXML ImageView imagePlayer;
    
	public long maxTimerSendMunition;
	public long maxTimerAntiVaxAttack;
	public long maxTimerVirusShoot;
	public int numberOfAntiVax;

    protected ArrayList<ImageView> listViruses = new ArrayList<ImageView>();
    protected ArrayList<ImageView> listVirusesSecondRow = new ArrayList<ImageView>();
    protected ArrayList<ImageView> listVirusesThirdRow = new ArrayList<ImageView>();

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
    protected ArrayList<ImageView> listRemainingMunition = new ArrayList<ImageView>();

    @FXML ImageView life1;
    @FXML ImageView life2;
    @FXML ImageView life3;
    protected ArrayList<ImageView> listRemainingLife = new ArrayList<ImageView>();

    @FXML Text score;
    
    int LevelNb;

    ///////////////////// METHODS ///////////////////////
    public void setStage(Stage stage) { this.stage = stage; }
    public Pane getRoad() { return road; }
    public void setPane(Pane pane) { this.road = pane; }
    public Player getPlayer() { return this.player; }
    public void setPlayer(Player p) {  this.player = p; }
    public void setLevelNb(int nb) { this.LevelNb = nb; }
    public int getLevelNb() { return this.LevelNb;}

    public ArrayList<ImageView> getListViruses() { return listViruses; }
    public void setListViruses(ArrayList<ImageView> listViruses) { this.listViruses = listViruses; }

    public ArrayList<ImageView> getListVirusesSecondRow(){ return this.listVirusesSecondRow; }
    public ArrayList<ImageView> getListVirusesThirdRow(){ return this.listVirusesThirdRow; }
    
    
    public long getMaxTimerSendMunition() { return this.maxTimerSendMunition; }
    public long getMaxTimerAntiVaxAttack() { return this.maxTimerAntiVaxAttack; }
    public long getMaxTimerVirusShoot() { return this.maxTimerVirusShoot; }
    public int getNumberOfAntiVax() { return this.numberOfAntiVax; }
    
    
    @FXML protected void play(KeyEvent k){
        if(k.getCode().equals(KeyCode.SPACE)){
            if(player.getAvailableJet() > 0) {

                //display the image of the player shooting
                player.setImagePlayer(player.getImageShoot());

                //launch a jet
                Jet jet = new Jet(player.getPosX()+36, player.getPosY()-36,getClass().getResource("../Images/jet.png"));
                road.getChildren().add(jet.getImageJet());

                player.getListJet().addJet(jet);

                removeAvailableJet();
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
     * removeJet(): remove one jet to the player
     */
    public void removeAvailableJet() {
        //remove one munition from the available ones of the player
        player.setAvailableJet(player.getAvailableJet()-1);
        //delete a munition from the bottom part
        listRemainingMunition.get(player.getAvailableJet()).setVisible(false);
    }

    /**
     * removeLife(): remove one life to the player
     */
    public void removeLife(){
        player.setLife(player.getLife()-1);
        listRemainingLife.get(player.getLife()).setVisible(false);
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

    /**
     * addExtraLifeToList(): add one life to the player's list of life
     */
    public void addExtraLifeToList() {
        this.listRemainingLife.get(player.getLife()).setVisible(true);
        player.setLife(player.getLife()+1);
    }

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

    /**
     * setListViruses(): add all viruses to the list of viruses displayed in the game
     */
    public void setListViruses() {

    }

}
