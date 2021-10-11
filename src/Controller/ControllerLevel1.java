package Controller;


import javafx.fxml.FXML;
import javafx.scene.image.ImageView;



public class ControllerLevel1 extends ControllerLevel{

    ///////////////////// ATTRIBUTES /////////////////////////
    @FXML ImageView virus1;
    @FXML ImageView virus2;
    @FXML ImageView virus3;
    @FXML ImageView virus4;
    @FXML ImageView virus5;
    @FXML ImageView virus6;
    @FXML ImageView virus7;


    ///////////////////// CONSTRUCTOR ///////////////////////
    public ControllerLevel1() {
        super();
        setLevelNb(1);
        maxTimerSendMunition = 30;
        maxTimerAntiVaxAttack = 50;
        maxTimerVirusShoot = 25;
        numberOfAntiVax = 3;
    }


    ///////////////////// METHODS ///////////////////////
    

    /**
     * setListViruses(): add all viruses to the list of viruses displayed in the game
     */
    public void setListViruses() {
        listViruses.add(virus1);
        listViruses.add(virus2);
        listViruses.add(virus3);
        listViruses.add(virus4);
        listViruses.add(virus5);
        listViruses.add(virus6);
        listViruses.add(virus7);
    }




}