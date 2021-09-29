package Model;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import Controller.ControllerGame;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Animation extends AnimationTimer{
	
	//////////////////////////// CONSTANTS /////////////////////////////
	public final static double minRange = 50.0;
	public final static double maxRange = 600.0;
	public final static long maxTimerSendMunition = 50;

	public final static int speedSendMunition_lv1 = 20;

	
	//////////////////////////// ATTRIBUTES /////////////////////////////
	private Player player;
	private VirusCloud virusCloud;
	private Double widthWindow;
	private boolean isgoingleft;
	private ControllerGame controllerGame;
	private Pane road;
	
	private long lastUpdate;
	
	private URL imageRechargeMunition;
	private ArrayList<ImageView> listSendMunition;
	private long timerSendMunition;


	/////////////////////// CONSTRUCTOR ///////////////////////////
	public Animation(VirusCloud vc, Double w, Player p, ControllerGame cg, Pane r) {
		virusCloud = vc;
		widthWindow = w;
		isgoingleft = false;
		player = p;
		controllerGame = cg;
		road = r;
		
		lastUpdate = 0;
		imageRechargeMunition = getClass().getResource("../Images/rechargeMunition.png");
		listSendMunition = new ArrayList<ImageView>();
		timerSendMunition = maxTimerSendMunition;
	}
	
	//////////////////////////// METHODS /////////////////////////////

	@Override
	public void handle(long arg0) {
		if(arg0 - lastUpdate >= 95000000) {
			update();
			lastUpdate = arg0;
		}
	}
	
	public void update() {
		moveJet();
		moveViruses();
		sendMunition();
		moveSendMunition();
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
		if(virusCloud.getSize() > 0) {
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
					
					controllerGame.setScore(virusCloud.getVirus(j));
					road.getChildren().remove(virusCloud.getVirus(j).getImageVirus());
					virusCloud.removeVirus(j);

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

	} */
	
	
	public void sendMunition() {
		if(player.getAvailableJet() < 4) {
			if(timerSendMunition == maxTimerSendMunition) {
				timerSendMunition = 0;
				
				ImageView newMunition = new ImageView(new Image(imageRechargeMunition.toExternalForm()));

				double randomX = new Random().nextDouble() * (maxRange - minRange) + minRange;
				newMunition.setLayoutX(randomX);
				newMunition.setLayoutY(0);
				this.listSendMunition.add(newMunition);
				
				road.getChildren().add(newMunition);
			}else {
				timerSendMunition++;
			}
		}
	}
	
	
	public void moveSendMunition() {
		for(int i = 0; i < this.listSendMunition.size(); i++) {
			this.listSendMunition.get(i).setLayoutY(this.listSendMunition.get(i).getLayoutY()+speedSendMunition_lv1);
		}
	}
	

}

