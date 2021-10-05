package Model;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import Controller.ControllerGame;
import Controller.ControllerLost;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Animation extends AnimationTimer{

	//////////////////////////// CONSTANTS /////////////////////////////
	public final static double minRange = 50.0;
	public final static double maxRange = 600.0;
	public final static double maxHeight = 540.0;
	public final static double maxWidth = 632.0;
	public final static long maxTimerSendMunition = 30;
	public final static long maxTimerAntiVaxAttack = 50;
	public final static long maxTimerVirusShoot = 25;

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

	private Level level;

	private long timerAntiVaxAttack;

	private long timerVirusShoot;
	private URL imageJetViruslv1;


	/////////////////////// CONSTRUCTOR ///////////////////////////
	public Animation(Double w, Player p, ControllerGame cg, Pane r) {
		this.level = new Level1(cg,20,numberOfAntiVax_lv1);
		virusCloud = this.level.getVirusCloud();
		widthWindow = w;
		isgoingleft = false;
		player = p;
		controllerGame = cg;
		road = r;

		lastUpdate = 0;
		imageRechargeMunition = getClass().getResource("../Images/rechargeMunition.png");
		listSendMunition = new ArrayList<ImageView>();
		timerSendMunition = maxTimerSendMunition;

		timerAntiVaxAttack = maxTimerAntiVaxAttack;

		timerVirusShoot = maxTimerVirusShoot;
		imageJetViruslv1 = getClass().getResource("../Images/jet_lv1.png");

	}

	//////////////////////////// METHODS /////////////////////////////

	@Override
	public void handle(long arg0) {
		if(arg0 - lastUpdate >= 95000000) {
			try {
				update();
			} catch (IOException e) {
				e.printStackTrace();
			}
			lastUpdate = arg0;
		}
	}

	public void update() throws IOException {
			moveJet();
			moveViruses();
			sendMunition();
			moveSendMunition();
			checkCollisionVirusJet();
			getMunition();
			antiVaxAttack();
			moveAntiVax();
			checkCollisionAntiVaxPlayer();
			virusShoot();
			moveVirusShoot();
			checkCollisionVirusShootPlayer();
			checkCollisionJetAntiVax();
			playerWinLevel();
			playerLostLevel();
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
					level.setScore(level.getScore() + level.getPoint());
					road.getChildren().remove(virusCloud.getVirus(j).getImageVirus());
					virusCloud.removeVirus(j);
				}
			}
		}
	}

	public void virusShoot(){
		if(timerVirusShoot == maxTimerVirusShoot && virusCloud.getSize() > 0) {
			timerVirusShoot = 0;
			int i = (int) (Math.random() * (virusCloud.getSize()));
			Jet j = new Jet(virusCloud.getVirus(i).getPosX() + 17,virusCloud.getVirus(i).getPosY() + 15, imageJetViruslv1);
			road.getChildren().add(j.getImageJet());
			virusCloud.getListJet().addJet(j);
		}
		else{
			timerVirusShoot++;
		}
	}

	public void moveVirusShoot(){
		for(int i = 0; i < virusCloud.getListJet().getSize(); i++){
			virusCloud.getListJet().getJet(i).setPosY(virusCloud.getListJet().getJet(i).getPosY() + speedSendMunition_lv1);
		}
	}


	public void checkCollisionVirusShootPlayer(){
		for(int i = 0; i < virusCloud.getListJet().getSize(); i++){
			if(player.i.getBoundsInParent().intersects(virusCloud.getListJet().getJet(i).getImageJet().getBoundsInParent())){
				controllerGame.removeLife();
				road.getChildren().remove(virusCloud.getListJet().getJet(i).getImageJet());
				virusCloud.getListJet().removeJet(i);
			}
		}
	}


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
		if(virusCloud.getSize() < 5 && level.getNumberOfAntiVax() > level.getListAntiVax().size()){
			if(timerAntiVaxAttack == maxTimerAntiVaxAttack) {
				timerAntiVaxAttack = 0;
				level.addAntiVax();
			}else {
				timerAntiVaxAttack++;
			}
		}
	}

	public void moveAntiVax() {
		for(int i = 0; i < level.getListAntiVax().size(); i++) {
			if(level.getListAntiVax().get(i).getPosY() > maxHeight){
				level.removeAntiVax(level.getListAntiVax().get(i));
			}
			else if(level.getListAntiVax().get(i).getPosX() < maxWidth/2){
				if(level.getListAntiVax().get(i).isInLeft()) {
					level.getListAntiVax().get(i).setPosX(level.getListAntiVax().get(i).getPosX() + speedAntiVaxAttackX);
				}
				else {
					level.getListAntiVax().get(i).setPosY(level.getListAntiVax().get(i).getPosY() + speedAntiVaxAttackY);
				}
			}
			else if (level.getListAntiVax().get(i).getPosX() < maxWidth && level.getListAntiVax().get(i).getPosX() > maxWidth/2){
				if (!level.getListAntiVax().get(i).isInLeft()) {
					level.getListAntiVax().get(i).setPosX(level.getListAntiVax().get(i).getPosX() - speedAntiVaxAttackX);
				}
				else {
					level.getListAntiVax().get(i).setPosY(level.getListAntiVax().get(i).getPosY() + speedAntiVaxAttackY);
				}
			}
		}
	}

	public void checkCollisionJetAntiVax() {
		for(int i = 0; i < level.getListAntiVax().size(); i++) {
			for(int j = 0; j < player.getListJet().getSize(); j++) {
				if (player.getListJet().getJet(j).getImageJet().getBoundsInParent()
						.intersects(level.getListAntiVax().get(i).getImage().getBoundsInParent())) {
					controllerGame.setScore(level.getListAntiVax().get(i).getPoint());
					level.setScore(level.getScore() + level.getPoint());
					road.getChildren().remove(player.getListJet().getJet(j).getImageJet());
					player.getListJet().removeJet(j);
					level.removeAntiVax(level.getListAntiVax().get(i));
					level.setNumberOfAntiVax(level.getNumberOfAntiVax()-1);
				}
			}
		}
	}

	public void checkCollisionAntiVaxPlayer(){
		for(int i = 0; i < level.getListAntiVax().size(); i++) {
			if (level.getListAntiVax().get(i).getImage().getBoundsInParent().intersects(player.i.getBoundsInParent())) {
				controllerGame.removeLife();
				level.removeAntiVax(level.getListAntiVax().get(i));
				level.setNumberOfAntiVax(level.getNumberOfAntiVax()-1);
			}
		}
	}

	public void playerWinLevel(){
		if(level.getNumberOfAntiVax() == 0 && virusCloud.getSize() == 0 && player.getPosY() < maxHeight){
			player.setPosY(player.getPosY()-20);
			player.setPosX(widthWindow/2);
			level.setWon(true);
		}
	}

	public void playerLostLevel() throws IOException {
		if(player.getLife() == 0){
			level.setLost(true);
			launchLoosePane();
		}
	}

	public void launchLoosePane() throws IOException {
		Stage primaryStage = (Stage) road.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../lost.fxml"));
		Pane myPane = loader.load();
		ControllerLost c = loader.getController();
		c.setLevel("You lost level " + level.getNbLevel());
		c.setPoint("with " + String.valueOf(level.getScore()) + " points");
		c.setStage(primaryStage);
		Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
		myScene.getRoot().requestFocus();
		primaryStage.setScene(myScene);
		primaryStage.show();
		stop();
	}
}

