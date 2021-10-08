package Controller;

import Model.Jet;
import Model.Player;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ControllerLevel2 {

    @FXML ImageView imagePlayer;

    @FXML Pane road;

    @FXML ImageView virus1;
    @FXML ImageView virus2;
    @FXML ImageView virus3;
    @FXML ImageView virus4;
    @FXML ImageView virus5;
    @FXML ImageView virus6;
    @FXML ImageView virus7;
    @FXML ImageView virus8;
    @FXML ImageView virus9;
    @FXML ImageView virus10;

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

    @FXML ImageView life1;
    @FXML ImageView life2;
    @FXML ImageView life3;

    Player player;
    ArrayList<ImageView> listRemainingMunition = new ArrayList<ImageView>();
    ArrayList<ImageView> listRemainingLife = new ArrayList<ImageView>();
    Stage stage;


    public Pane getRoad() { return road; }
    public void setStage(Stage stage) { this.stage = stage; }

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
