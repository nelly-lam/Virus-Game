package Model;

import java.util.TimerTask;

import Controller.ControllerGame;

public class TimerTaskGame extends TimerTask{
	
	/////////////////////// ATTRIBUTES /////////////////////////
	private Player player;
	private VirusCloud virusCloud;
	private Double widthWindow;
	private boolean isgoingleft;
	private ControllerGame controllerGame;
	
	/////////////////////// CONSTRUCTOR ///////////////////////////
	public TimerTaskGame(VirusCloud vc, Double w, Player p, ControllerGame cg) {
		virusCloud = vc;
		widthWindow = w;
		isgoingleft = false;
		player = p;
		setControllerGame(cg);
	}
	
    ///////////////////// METHODS ///////////////////////

	/**
	 * run(): 
	 */
	public void run() {
		moveViruses();
		moveJet();
		checkCollisionVirusJet();
	}
	
	
	/**
	 * moveJet(): moves jets that are launched by the player
	 */
	public void moveJet() {
		for(int i = 0; i < player.getListJet().getSize(); i++) {
			player.getListJet().getJet(i).setPosY(player.getListJet().getJet(i).getPosY()-45);
			
			/* remove if jet crosses the window */
			if(player.getListJet().getJet(i).getPosY() < -45) {
				player.getListJet().removeJet(i);
			}
		}
	}

	/**
	 * moveViruses(): moves viruses from left to right and vice versa
	 */
	public void moveViruses() {
		Virus firstVirus = virusCloud.getVirus(0);
		Virus lastVirus = virusCloud.getVirus(virusCloud.getSize()-1);

		if(isgoingleft) {
			if(firstVirus.getPosX() > 0) {
				goLeft();
			}else {
				isgoingleft = false;
				goRight();
			}
		}else{
			if(lastVirus.getPosX()+lastVirus.getImageVirus().getFitWidth() > widthWindow ) {
				isgoingleft = true;
				goLeft();
			}else {
				goRight();
			}
		}
	}
	
	/**
	 * goLeft(): move viruses to the left
	 */
	public void goLeft() {
		Virus virus;
		for(int i = 0; i < virusCloud.getSize(); i++) {
			virus = virusCloud.getVirus(i);
			virus.setPosX(virus.getPosX()-10);
		}
	}
	
	/**
	 * goRight(): move viruses to the right
	 */
	public void goRight() {
		Virus virus;
		for(int i = 0; i < virusCloud.getSize(); i++) {
			virus = virusCloud.getVirus(i);
			virus.setPosX(virus.getPosX()+10);
		}
	}

	/**
	 * checkCollisionVirusJet(): check if a jet touches a virus,
	 * if true then erase this virus
	 * if false then nothing
	 */
	public void checkCollisionVirusJet() {
		for(int i = 0; i < player.getListJet().getSize(); i++) {
			for(int j = 0; j < virusCloud.getSize(); j++) {
				if(player.getListJet().getJet(i).getImageJet().getBoundsInParent()
						.intersects(virusCloud.getVirus(j).getImageVirus().getBoundsInParent())) {
					//controllerGame.removeVirus(virusCloud.getVirus(j), j);
					controllerGame.setScore(virusCloud.getVirus(j));

				}
			}
		}
	}

	/*
	public void checkCollisionVirusPlayer(){
		for(int i = 0; i < virusCloud.getListJet().getSize(); i++){
			if(player.getImage().getBoundsInParent().intersects(virusCloud.getListJet().getImage().getBoundsInParent())){
				if(player.getLife == 0){
					System.out.println("game lost);
				else(
					controllerGame.removeLife();

	}
*/
	public ControllerGame getControllerGame() {
		return controllerGame;
	}

	public void setControllerGame(ControllerGame controllerGame) {
		this.controllerGame = controllerGame;
	}
	

		
	
}
