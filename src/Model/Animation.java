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
	public final static double maxHeight = 540.0;
	public final static double maxWidth = 632.0;
	public final static long maxTimerSendMunition = 30;
	public final static long maxTimerAntiVaxAttack = 50;


	public final static int speedSendMunition_lv1 = 25;
	public final static int speedAntiVaxAttackX = 10;
	public final static int speedAntiVaxAttackY = 10;

	public final static int numberOfAntiVax_lv1 = 6;
	
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


	private ArrayList<AntiVax> listAntiVax;
	private long timerAntiVaxAttack;
	private int numberOfAntiVax;


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

		listAntiVax = new ArrayList<AntiVax>();
		timerAntiVaxAttack = maxTimerAntiVaxAttack;
		numberOfAntiVax = numberOfAntiVax_lv1;
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
		getMunition();
		antiVaxAttack();
		moveAntiVax();
		checkCollisionAntiVaxPlayer();
		//playerWinLevel1();
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
					
					controllerGame.setScore(virusCloud.getVirus(j).getPoint());
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
		if(player.getAvailableJet() < 9) {
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
		for (ImageView imageView : this.listSendMunition) {
			imageView.setLayoutY(imageView.getLayoutY() + speedSendMunition_lv1);
		}
	}

	public void getMunition(){
		for(int i = 0; i < this.listSendMunition.size(); i++) {
			if (player.getImageViewPlayer().getBoundsInParent().intersects(this.listSendMunition.get(i).getBoundsInParent())) {
				road.getChildren().remove(this.listSendMunition.get(i));
				this.listSendMunition.remove(this.listSendMunition.get(i));
				controllerGame.addMunitionToList();

			}
		}
	}


	public void antiVaxAttack(){
		if(virusCloud.getSize() < 5 && numberOfAntiVax > listAntiVax.size()){
			if(timerAntiVaxAttack == maxTimerAntiVaxAttack) {
				timerAntiVaxAttack = 0;
				AntiVax antiVax = new AntiVax(20);
				double randomX =  Math.random();
				if(randomX < 0.5){
					antiVax.setPosX(0);
				}
				else{
					antiVax.setPosX(maxRange);
					antiVax.setInLeft(false);
				}
				antiVax.setPosY(maxHeight/4);
				this.listAntiVax.add(antiVax);
				road.getChildren().add(antiVax.getImage());
			}else {
			timerAntiVaxAttack++;
			}
		}
	}

	public void moveAntiVax() {
		for(int i = 0; i < this.listAntiVax.size(); i++) {
			if(listAntiVax.get(i).getPosY() > maxHeight){
				road.getChildren().remove(listAntiVax.get(i).getImage());
				this.listAntiVax.remove(listAntiVax.get(i));
			}
			else if(listAntiVax.get(i).isInLeft()) {
				listAntiVax.get(i).setPosY(listAntiVax.get(i).getPosY() + speedAntiVaxAttackY);
				listAntiVax.get(i).setPosX(listAntiVax.get(i).getPosX() + speedAntiVaxAttackX);

			}
			else if (!listAntiVax.get(i).isInLeft()){
				listAntiVax.get(i).setPosY(listAntiVax.get(i).getPosY() + speedAntiVaxAttackY);
				listAntiVax.get(i).setPosX(listAntiVax.get(i).getPosX() - speedAntiVaxAttackX);
			}
		}
	}

	public void checkCollisionAntiVaxPlayer() {
		for(int i = 0; i < this.listAntiVax.size(); i++) {
			for(int j = 0; j < player.getListJet().getSize(); j++) {
				if (player.getListJet().getJet(i).getImageJet().getBoundsInParent()
						.intersects(this.listAntiVax.get(i).getImage().getBoundsInParent())) {
					controllerGame.setScore(this.listAntiVax.get(i).getPoint());
					road.getChildren().remove(this.listAntiVax.get(i).getImage());
					this.listAntiVax.remove(this.listAntiVax.get(i));
					numberOfAntiVax -= 1;
					System.out.println(numberOfAntiVax);
				}
			}
			if (this.listAntiVax.get(i).getImage().getBoundsInParent().intersects(player.i.getBoundsInParent())) {
				controllerGame.removeLife();
				road.getChildren().remove(this.listAntiVax.get(i).getImage());
				this.listAntiVax.remove(this.listAntiVax.get(i));
				numberOfAntiVax -= 1;
			}

		}
	}

	public void playerWinLevel1(){
		if(numberOfAntiVax == 0 && virusCloud.getSize() == 0 && player.getPosY() < maxHeight){
			player.setPosY(player.getPosY()+20);
		}
	}
}

