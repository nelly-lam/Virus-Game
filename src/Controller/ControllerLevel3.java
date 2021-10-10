package Controller;


import javafx.fxml.FXML;
import javafx.scene.image.ImageView;



public class ControllerLevel3 extends ControllerLevel{

    ///////////////////// ATTRIBUTES /////////////////////////
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


    ///////////////////// CONSTRUCTOR ///////////////////////
    public ControllerLevel3() {
        super();
        setLevelNb(3);
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
        listViruses.add(virus8);
        listViruses.add(virus9);
        listViruses.add(virus10);
    }



}