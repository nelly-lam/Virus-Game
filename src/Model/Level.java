package Model;

import Controller.ControllerLevel;
import Controller.ControllerLevel1;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Level {

    ///////////////////// ATTRIBUTES /////////////////////////
    private int numberOfAntiVax;
    private ArrayList<AntiVax> listAntiVax;
    private int pointOfAntiVax;

    private boolean won;
    private boolean lost;

    private VirusCloud virusCloud;
    private VirusCloud virusCloudSecondRow;
    private VirusCloud virusCloudThirdRow;

    private int numberOfLevel;

    private ControllerLevel controllerLevel;


    ///////////////////// CONSTRUCTOR /////////////////////////
    public Level(ControllerLevel c, int point, int numberOfAntiVax){
        this.numberOfAntiVax = numberOfAntiVax;
        this.listAntiVax = new ArrayList<AntiVax>();
        this.pointOfAntiVax = point;

        this.won = false;
        this.lost = false;

        this.virusCloud = new VirusCloud();
        this.virusCloudSecondRow = new VirusCloud();
        this.virusCloudThirdRow = new VirusCloud();

        this.controllerLevel = c;
        this.numberOfLevel = c.getLevelNb();
        
        for(ImageView img : this.controllerLevel.getListViruses()) {
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
    public ControllerLevel getControllerGame() { return controllerLevel; }

    public int getPoint() { return pointOfAntiVax; }

    public VirusCloud getVirusCloud() { return virusCloud; }
    public void setVirusCloud(VirusCloud virusCloud) { this.virusCloud = virusCloud; }
    
	public VirusCloud getVirusCloudSecondRow() { return virusCloudSecondRow; }
	public void setVirusCloudSecondRow(VirusCloud v) { this.virusCloudSecondRow = v; }

	public VirusCloud getVirusCloudThirdRow() { return virusCloudThirdRow; }
	public void setVirusCloudThirdRow(VirusCloud v) { this.virusCloudThirdRow = v; }

    public int getNumberOfAntiVax() { return numberOfAntiVax; }
    public void setNumberOfAntiVax(int numberOfAntiVax) { this.numberOfAntiVax = numberOfAntiVax; }

    public boolean isLost() { return lost; }
    public void setLost(boolean lost) { this.lost = lost; }

    public boolean getWon(){ return this.won; }
    public void setWon(boolean won) { this.won = won; }

    public ArrayList<AntiVax> getListAntiVax() { return listAntiVax; }
    public void setListAntiVax(ArrayList<AntiVax> listAntiVax) { this.listAntiVax = listAntiVax; }

    public void addVirusToVirusCloud(Virus virus){ virusCloud.addVirus(virus); }
    public void addVirusToVirusCloudSecondRow(Virus virus){ virusCloudSecondRow.addVirus(virus); }
    public void addVirusToVirusCloudThirdRow(Virus virus){ virusCloudThirdRow.addVirus(virus); }


    public AntiVax createAntiVax(){ return new AntiVax(pointOfAntiVax); }

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