package Model;

import Controller.ControllerLevel;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Level {

    ///////////////////// ATTRIBUTES /////////////////////////
    private ArrayList<AntiVax> listAntiVax;
    private int pointOfEnemies;

    private VirusCloud virusCloudFirstRow;
    private VirusCloud virusCloudSecondRow;
    private VirusCloud virusCloudThirdRow;

    private int numberOfLevel;

    private ControllerLevel controllerLevel;


    ///////////////////// CONSTRUCTOR /////////////////////////
    public Level(ControllerLevel c, int point){
        this.listAntiVax = new ArrayList<AntiVax>();
        this.pointOfEnemies = point;

        this.virusCloudFirstRow = new VirusCloud();
        this.virusCloudSecondRow = new VirusCloud();
        this.virusCloudThirdRow = new VirusCloud();

        this.controllerLevel = c;
        this.numberOfLevel = c.getLevelNb();
        
        for(ImageView img : this.controllerLevel.getListVirusesFirstRow()) {
            addVirusToVirusCloud(new Virus(img, point));
        }
        if(this.controllerLevel.getLevelNb() == 3 || this.controllerLevel.getLevelNb() == 4 
        		|| this.controllerLevel.getLevelNb() == 5) {
            for(ImageView img : this.controllerLevel.getListVirusesSecondRow()) {
                addVirusToVirusCloudSecondRow(new Virus(img, point));
            }
        }
        if(this.controllerLevel.getLevelNb() == 5) {
            for(ImageView img : this.controllerLevel.getListVirusesThirdRow()) {
                addVirusToVirusCloudThirdRow(new Virus(img, point));
            }
        }
    }

    ///////////////////// METHODS /////////////////////////

    public int getPointOfEnemies() { return pointOfEnemies; }

    public VirusCloud getVirusCloud() { return virusCloudFirstRow; }
    
	public VirusCloud getVirusCloudSecondRow() { return virusCloudSecondRow; }

	public VirusCloud getVirusCloudThirdRow() { return virusCloudThirdRow; }

    public ArrayList<AntiVax> getListAntiVax() { return listAntiVax; }


    public void addVirusToVirusCloud(Virus virus){ virusCloudFirstRow.addVirus(virus); }
    public void addVirusToVirusCloudSecondRow(Virus virus){ virusCloudSecondRow.addVirus(virus); }
    public void addVirusToVirusCloudThirdRow(Virus virus){ virusCloudThirdRow.addVirus(virus); }

    public AntiVax createAntiVax(){ return new AntiVax(pointOfEnemies); }

    public void addAntiVax(){
        AntiVax a = createAntiVax();
        listAntiVax.add(a);
        controllerLevel.getRoad().getChildren().add(a.getImage());

    }
    public void removeAntiVax(AntiVax a){
        controllerLevel.getRoad().getChildren().remove(a.getImage());
        this.listAntiVax.remove(a);
    }

    public int getNumberOfLevel() { return numberOfLevel; }



}