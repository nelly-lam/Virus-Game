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
