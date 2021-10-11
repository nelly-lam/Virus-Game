package Controller;


import javafx.fxml.FXML;
import javafx.scene.image.ImageView;



public class ControllerLevel4 extends ControllerLevel{

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
    public ControllerLevel4() {
        super();
        setLevelNb(4);
    }


    ///////////////////// METHODS ///////////////////////
    /**
     * setListViruses(): add all viruses to the list of viruses displayed in the game
     */
    public void setListViruses() {
        setListVirusesFirstRow();
        setListVirusesSecondRow();
    }

    public void setListVirusesFirstRow() {
        listViruses.add(virus1);
        listViruses.add(virus2);
        listViruses.add(virus3);
        listViruses.add(virus4);
        listViruses.add(virus5);
        listViruses.add(virus6);
    }

    public void setListVirusesSecondRow() {
        listVirusesSecondRow.add(virus7);
        listVirusesSecondRow.add(virus8);
        listVirusesSecondRow.add(virus9);
        listVirusesSecondRow.add(virus10);
        listVirusesSecondRow.add(virus11);
        listVirusesSecondRow.add(virus12);
        listVirusesSecondRow.add(virus13);
        listVirusesSecondRow.add(virus14);
    }


}