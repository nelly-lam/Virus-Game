package Model;

import Controller.ControllerGame;

import java.util.ArrayList;

public class Level {
    private int numberOfAntiVax;
    private boolean won;
    private boolean lost;
    private int score;
    private  VirusCloud virusCloud;
    private ControllerGame controllerGame;
    private int point;
    private ArrayList<AntiVax> listAntiVax;
    private int nbLevel;


    public Level(ControllerGame c, int point, int numberOfAntiVax){
        this.won = false;
        this.lost = false;
        this.score = 0;
        this.virusCloud = new VirusCloud();
        this.controllerGame = c;
        this.listAntiVax = new ArrayList<AntiVax>();
        this.point = point;
        this.numberOfAntiVax = numberOfAntiVax;
        this.nbLevel = 1;


    }

    public ControllerGame getControllerGame() {
        return controllerGame;
    }

    public int getPoint() {
        return point;
    }

    public VirusCloud getVirusCloud() {
        return virusCloud;
    }

    public void setVirusCloud(VirusCloud virusCloud) {
        this.virusCloud = virusCloud;
    }

    public int getNumberOfAntiVax() {
        return numberOfAntiVax;
    }

    public void setNumberOfAntiVax(int numberOfAntiVax) {
        this.numberOfAntiVax = numberOfAntiVax;
    }


    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public boolean getWon(){
        return this.won;
    }

    public ArrayList<AntiVax> getListAntiVax() {
        return listAntiVax;
    }

    public void setListAntiVax(ArrayList<AntiVax> listAntiVax) {
        this.listAntiVax = listAntiVax;
    }

    public void addVirusToVirusCloud(Virus virus){
        virusCloud.addVirus(virus);
    }

    public AntiVax createAnAntiVax(){
        return new AntiVax(point);
    }

    public void addAntiVax(){
        AntiVax a = createAnAntiVax();
        listAntiVax.add(a);
        controllerGame.getRoad().getChildren().add(a.getImage());

    }
    public void removeAntiVax(AntiVax a){
        controllerGame.getRoad().getChildren().remove(a.getImage());
        this.listAntiVax.remove(a);
    }

    public int getNbLevel() {
        return nbLevel;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
