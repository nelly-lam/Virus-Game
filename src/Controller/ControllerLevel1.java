package Controller;

import java.util.ArrayList;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


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
    }


    ///////////////////// METHODS ///////////////////////

    public ImageView getVirus1() { return virus1; }
    public ImageView getVirus2() { return virus2; }
    public ImageView getVirus3() { return virus3; }
    public ImageView getVirus4() { return virus4; }
    public ImageView getVirus5() { return virus5; }
    public ImageView getVirus6() { return virus6; }
    public ImageView getVirus7() { return virus7; }



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