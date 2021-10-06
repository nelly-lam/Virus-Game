package Model;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import Controller.ControllerLevel1;
import Controller.ControllerLost;
import Controller.ControllerWin;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Animation extends AnimationTimer{

	//////////////////////////// CONSTANTS /////////////////////////////
	public final static double minRange = 50.0;
	public final static double maxRange = 600.0;
	public final static double maxHeight = 540.0;
	public final static double maxWidth = 632.0;

	public final static int speedSendMunition = 25;

	public final static int speedAntiVaxAttackX = 10;
	public final static int speedAntiVaxAttackY = 10;
	
	public final static long maxTimerSendMunition_lv1 = 30;
	public final static long maxTimerSendMunition_lv2 = 30;
	public final static long maxTimerSendMunition_lv3 = 25;
	public final static long maxTimerSendMunition_lv4 = 25;
	public final static long maxTimerSendMunition_lv5 = 20;

	public final static long maxTimerAntiVaxAttack_lv1 = 50;
	public final static long maxTimerAntiVaxAttack_lv2 = 50;
	public final static long maxTimerAntiVaxAttack_lv3 = 40;
	public final static long maxTimerAntiVaxAttack_lv4 = 40;
	public final static long maxTimerAntiVaxAttack_lv5 = 40;

	public final static long maxTimerVirusShoot_lv1 = 25;
	public final static long maxTimerVirusShoot_lv2 = 20;
	public final static long maxTimerVirusShoot_lv3 = 20;
	public final static long maxTimerVirusShoot_lv4 = 20;
	public final static long maxTimerVirusShoot_lv5 = 15;

	public final static int numberOfAntiVax_lv1 = 3;
	public final static int numberOfAntiVax_lv2 = 5;
	public final static int numberOfAntiVax_lv3 = 5;
	public final static int numberOfAntiVax_lv4 = 7;
	public final static int numberOfAntiVax_lv5 = 10;
	

	//////////////////////////// ATTRIBUTES /////////////////////////////
	private Player player;
	private VirusCloud virusCloud;
	private Double widthWindow;
	private boolean isvirusgoingleft;
	private ControllerLevel1 controllerGame;
	private Pane road;

	//after how much time the animation need to update the game play
	private long lastUpdate;

	private URL imageRechargeMunition;
	private ArrayList<ImageView> listSendMunition;
	private long timerSendMunition;

	private Level level;

	private long timerAntiVaxAttack;

	private long timerVirusShoot;
	private URL imageJetViruslv1;


	/////////////////////// CONSTRUCTOR ///////////////////////////
	public Animation(Double w, Player p, ControllerLevel1 cg, Pane r) {
		this.level = new Level1(cg,20,numberOfAntiVax_lv1);
		virusCloud = this.level.getVirusCloud();
		widthWindow = w;
		isvirusgoingleft = false;
		player = p;
		controllerGame = cg;
		road = r;

		lastUpdate = 0;

		imageRechargeMunition = getClass().getResource("../Images/rechargeMunition.png");
		listSendMunition = new ArrayList<ImageView>();
		timerSendMunition = maxTimerSendMunition_lv1;

		timerAntiVaxAttack = maxTimerAntiVaxAttack_lv1;

		timerVirusShoot = maxTimerVirusShoot_lv1 - 5 ;
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
			addMunition();
			antiVaxAttack();
			virusShoot();
			
			checkCollisionVirusJet();
			checkCollisionAntiVaxPlayer();
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

			//remove if jet crosses the window
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

			if(isvirusgoingleft) {
				if(firstVirus.getPosX() > 0) {
					goLeft();
				}else {
					isvirusgoingleft = false;
					goRight();
				}
			}else{
				if(lastVirus.getPosX()+lastVirus.getImageVirus().getFitWidth() > widthWindow ) {
					isvirusgoingleft = true;
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
	 * 							if yes, remove this virus from the game
	 */
	public void checkCollisionVirusJet() {
		for(int i = 0; i < player.getListJet().getSize(); i++) {
			for(int j = 0; j < virusCloud.getSize(); j++) {
				if(player.getListJet().getJet(i).getImageJet().getBoundsInParent()
						.intersects(virusCloud.getVirus(j).getImageVirus().getBoundsInParent())) {
					
					controllerGame.setScore(virusCloud.getVirus(j).getPointVirus());
					level.setTotalScore(level.getTotalScore() + level.getPoint());
					
					road.getChildren().remove(virusCloud.getVirus(j).getImageVirus());
					virusCloud.removeVirus(j);
				}
			}
		}
	}

	/**
	 * virusShoot(): shoot a virus' jet if this is time and if there is viruses in the cloud
	 */
	public void virusShoot(){
		if(timerVirusShoot == maxTimerVirusShoot_lv1 && virusCloud.getSize() > 0) {
			timerVirusShoot = 0;
			int i = (int) (Math.random() * (virusCloud.getSize()));
			Jet j = new Jet(virusCloud.getVirus(i).getPosX() + 17,virusCloud.getVirus(i).getPosY() + 15, imageJetViruslv1);
			road.getChildren().add(j.getImageJet());
			virusCloud.getListJet().addJet(j);
		}
		else{
			timerVirusShoot++;
		}
		moveVirusShoot();
	}

	/**
	 * moveVirusShoot(): move viruses' jets down
	 */
	public void moveVirusShoot(){
		for(int i = 0; i < virusCloud.getListJet().getSize(); i++){
			virusCloud.getListJet().getJet(i).setPosY(virusCloud.getListJet().getJet(i).getPosY() + speedSendMunition);
		}
	}
	
	/**
	 * checkCollisionVirusShootPlayer(): check if a virus' jet touch the player,
	 * 									if yes, remove a life from the player remaining lives
	 */
	public void checkCollisionVirusShootPlayer(){
		for(int i = 0; i < virusCloud.getListJet().getSize(); i++){
			if(player.i.getBoundsInParent()
					.intersects(virusCloud.getListJet().getJet(i).getImageJet().getBoundsInParent())){
				
				controllerGame.removeLife();
				road.getChildren().remove(virusCloud.getListJet().getJet(i).getImageJet());
				virusCloud.getListJet().removeJet(i);
			}
		}
	}

	/**
	 * sendMunition(): send a munition to the player if this is time
	 */
	public void sendMunition() {
		if(player.getAvailableJet() < 9) {
			if(timerSendMunition == maxTimerSendMunition_lv1) {
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
		moveSendMunition();
	}


	/**
	 * moveSendMunition(): move all munitions that are already sent
	 */
	public void moveSendMunition() {
		for (ImageView imageView : this.listSendMunition) {
			imageView.setLayoutY(imageView.getLayoutY() + speedSendMunition);
		}
	}

	/**
	 * getMunition(): add a sent munition if the player touch it
	 */
	public void addMunition(){
		for(int i = 0; i < this.listSendMunition.size(); i++) {
			if (player.getImageViewPlayer().getBoundsInParent()
					.intersects(this.listSendMunition.get(i).getBoundsInParent())) {
				
				road.getChildren().remove(this.listSendMunition.get(i));
				this.listSendMunition.remove(this.listSendMunition.get(i));
				controllerGame.addMunitionToList();

			}
		}
	}

	/**
	 * antiVaxAttack(): add an antiVax to the game when this is time
	 */
	public void antiVaxAttack(){
		if(virusCloud.getSize() < 5 && level.getNumberOfAntiVax() > level.getListAntiVax().size()){
			if(timerAntiVaxAttack == maxTimerAntiVaxAttack_lv1) {
				timerAntiVaxAttack = 0;
				level.addAntiVax();
			}else {
				timerAntiVaxAttack++;
			}
		}
		moveAntiVax();
	}

	/**
	 * moveAntiVax(): move all displayed antiVax
	 */
	public void moveAntiVax() {
		for(int i = 0; i < level.getListAntiVax().size(); i++) {
			if(level.getListAntiVax().get(i).getPosX() < maxWidth/2){
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
			
			//remove antiVax that are not displayed anymore
			if(level.getListAntiVax().get(i).getPosY() > maxHeight){
				level.removeAntiVax(level.getListAntiVax().get(i));
			}
		}
	}

	/**
	 * checkCollisionJetAntiVax(): check if all player's jets touch an antiVax,
	 * 								if yes, remove the touched antiVax from the game
	 */
	public void checkCollisionJetAntiVax(){
		for(int i = 0; i < level.getListAntiVax().size(); i++) {
			for(int j = 0; j < player.getListJet().getSize(); j++) {
				if (player.getListJet().getJet(j).getImageJet().getBoundsInParent()
						.intersects(level.getListAntiVax().get(i).getImage().getBoundsInParent())) {
					
					controllerGame.setScore(level.getListAntiVax().get(i).getPoint());
					//TODO: remove level.score???????
					level.setTotalScore(level.getTotalScore() + level.getPoint());
					
					//remove the jet if this jet already touches an antiVax
					road.getChildren().remove(player.getListJet().getJet(j).getImageJet());
					player.getListJet().removeJet(j);
					
					level.removeAntiVax(level.getListAntiVax().get(i));
					level.setNumberOfAntiVax(level.getNumberOfAntiVax()-1);
				}
			}
		}
	}

	/**
	 * checkCollisionAntiVaxPlayer(): check if an antiVax touches the player,
	 * 									if yes, remove a life from the player remaining lives
	 */
	public void checkCollisionAntiVaxPlayer(){
		for(int i = 0; i < level.getListAntiVax().size(); i++) {
			if (level.getListAntiVax().get(i).getImage().getBoundsInParent()
					.intersects(player.i.getBoundsInParent())) {
				
				controllerGame.removeLife();
				level.removeAntiVax(level.getListAntiVax().get(i));
				
				level.setNumberOfAntiVax(level.getNumberOfAntiVax()-1);
			}
		}
	}

	/**
	 * playerWinLevel(): launch the next pane if the player win the level he is in
	 * 					[win if beat all viruses and antiVax]
	 * @throws IOException
	 */
	public void playerWinLevel() throws IOException {
		if(level.getNumberOfAntiVax() == 0 && virusCloud.getSize() == 0){
			level.setWon(true);
			launchWonPane();
		}
	}

	/**
	 * playerLostLevel(): launch the next pane if the player lost the level he is in
	 * 						[lose if lost all his remaining lives]
	 * @throws IOException
	 */
	public void playerLostLevel() throws IOException {
		if(player.getLife() == 0){
			level.setLost(true);
			launchLoosePane();
		}
	}


    public void launchWonPane() throws IOException {
        Stage primaryStage = (Stage) road.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../win.fxml"));
        Pane myPane = loader.load();
        ControllerWin controllerWin = loader.getController();
        
        controllerWin.setLevel(" " +level.getNumberOfLevel());
        controllerWin.setScore(" " + level.getTotalScore() + " points");
        controllerWin.setNextLevel(Integer.toString(level.getNumberOfLevel()+1));
        controllerWin.setStage(primaryStage);
        
        Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
        myScene.getRoot().requestFocus();
        primaryStage.setScene(myScene);
        primaryStage.show();
        
        stop();
    }
    
    /**
     * launchLoosePane(): launch the lose pane
     * @throws IOException
     */
    public void launchLoosePane() throws IOException {
        Stage primaryStage = (Stage) road.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../lost.fxml"));
        Pane myPane = loader.load();
        ControllerLost controllerLost = loader.getController();
        
        controllerLost.setLevel(" " + level.getNumberOfLevel());
        controllerLost.setScore(" " + level.getTotalScore() + " points");
        controllerLost.setStage(primaryStage);
        
        Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
        myScene.getRoot().requestFocus();
        primaryStage.setScene(myScene);
        primaryStage.show();
        
        stop();
    } 
}

