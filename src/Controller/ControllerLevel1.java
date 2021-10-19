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
        levelNb = 1;
        maxTimerSendMunition = 30;
        maxTimerAntiVaxAttack = 50;
        maxTimerVirusShootFirstRow = 25;
        maxTimerVirusShootSecondRow = 0;
        maxTimerVirusShootThirdRow = 0;
        pointVirus = 20;
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
    }




}