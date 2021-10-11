package Controller;


import javafx.fxml.FXML;
import javafx.scene.image.ImageView;



public class ControllerLevel5 extends ControllerLevel{

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
    @FXML ImageView virus11;
    @FXML ImageView virus12;
    @FXML ImageView virus13;
    @FXML ImageView virus14;



    ///////////////////// CONSTRUCTOR ///////////////////////
    public ControllerLevel5() {
        super();
        setLevelNb(5);

        maxTimerSendMunition = 20;
        maxTimerAntiVaxAttack = 40;
        maxTimerVirusShoot = 15;
        numberOfAntiVax = 15;
    }


    ///////////////////// METHODS ///////////////////////
    /**
     * setListViruses(): add all viruses to the list of viruses displayed in the game
     */
    public void setListViruses() {
    	setListVirusesFirstRow();
    	setListVirusesSecondRow();
    	setListVirusesThirdRow();
    }
    
    public void setListVirusesFirstRow() {
        listViruses.add(virus1);
        listViruses.add(virus2);
        listViruses.add(virus3);
        listViruses.add(virus4);
    }
    
    public void setListVirusesSecondRow() {
        listVirusesSecondRow.add(virus5);
        listVirusesSecondRow.add(virus6);
        listVirusesSecondRow.add(virus7);
        listVirusesSecondRow.add(virus8);
        listVirusesSecondRow.add(virus9);
        listVirusesSecondRow.add(virus10);
        listVirusesSecondRow.add(virus11);
        listVirusesSecondRow.add(virus12);
    }
    
    public void setListVirusesThirdRow() {
        listVirusesSecondRow.add(virus13);
        listVirusesSecondRow.add(virus14);
    }


}