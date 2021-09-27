package Model;

import java.util.TimerTask;

/**
 * action à effectuer par le timer, ici on veut decrementer la position en y de chaque shoot du player
 */
public class TimerTaskGame extends TimerTask{
	
	/////////////////////// ATTRIBUTES /////////////////////////
	private Player player;
	private VirusCloud virusCloud;
	private Double widthWindow;
	private boolean isgoingleft;
	
	
	/////////////////////// CONSTRUCTOR ///////////////////////////
	public TimerTaskGame(VirusCloud vc, Double w, Player p) {
		virusCloud = vc;
		widthWindow = w;
		isgoingleft = false;
		player = p;
	}
	
	
	/**
	 * run() : 
	 */
	public void run() {
		moveViruses();
		moveJet();
	}
	
	
	/**
	 * moveJet(): moves jets that are launch by the player
	 */
	public void moveJet() {
		for(int i = 0; i < player.getListJet().getSize(); i++) {
			player.getListJet().getJet(i).setPosY(player.getListJet().getJet(i).getPosY()-45);
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
			if(lastVirus.getPosX()+lastVirus.getImageVirus().prefWidth(0) > widthWindow ) {
				isgoingleft = true;
				goLeft();
			}else {
				goRight();
			}
		}
	}
	
	/**
	 * goLeft(): moves viruses to the left
	 */
	public void goLeft() {
		Virus virus;
		for(int i = 0; i < virusCloud.getSize(); i++) {
			virus = virusCloud.getVirus(i);
			virus.setPosX(virus.getPosX()-10);
		}
	}
	
	/**
	 * goRight(): moves viruses to the right
	 */
	public void goRight() {
		Virus virus;
		for(int i = 0; i < virusCloud.getSize(); i++) {
			virus = virusCloud.getVirus(i);
			virus.setPosX(virus.getPosX()+10);
		}
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}

		
	
}
