package Controller;


import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;


public class ControllerLevel2 extends ControllerLevel{


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
    public ControllerLevel2() {
        super();
        setLevelNb(2);
        maxTimerSendMunition = 30;
        maxTimerAntiVaxAttack = 40;
        maxTimerVirusShoot = 20;
        numberOfAntiVax = 5;
    }

    ///////////////////// METHODS ///////////////////////
    /**
     * setListViruses(): add all viruses to the list of viruses displayed in the game
     */
    public void setListViruses() {
        listVirusesFirstRow.add(virus1);
        listVirusesFirstRow.add(virus2);
        listVirusesFirstRow.add(virus3);
        listVirusesFirstRow.add(virus4);
        listVirusesFirstRow.add(virus5);
        listVirusesFirstRow.add(virus6);
        listVirusesFirstRow.add(virus7);
        listVirusesFirstRow.add(virus8);
        listVirusesFirstRow.add(virus9);
        listVirusesFirstRow.add(virus10);

    }

}
