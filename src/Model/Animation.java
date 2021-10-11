package Model;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import Controller.ControllerLevel;
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
	public final static int speedSendExtraLife = 25;

	public final static int speedAntiVaxAttackX = 10;
	public final static int speedAntiVaxAttackY = 10;

	public final static long maxTimerSendExtraLife = 40;


	//////////////////////////// ATTRIBUTES /////////////////////////////
	private Double widthWindow;
	private Double heightWindow;
	private Pane road;
	private ControllerLevel controllerLevel;
	private Level level;

	private Player player;
	private VirusCloud virusCloud;
	private VirusCloud virusCloudSecondRow;
	private VirusCloud virusCloudThirdRow;

	
	private boolean isVirusGoingLeftFirstRow;
	private boolean isVirusGoingLeftSecondRow;
	private boolean isVirusGoingLeftThirdRow;


	//after how much time the animation need to update the game play
	private long lastUpdate;

	private URL imageRechargeMunition;
	private ArrayList<ImageView> listSendMunition;

	private URL imageExtraLife;
	private ArrayList<ImageView> listSendExtraLife;
	private long timerSendExtraLife;

	private long timerSendMunition;
	private long timerAntiVaxAttack;
	private long timerVirusShoot;
	private int numberOfAntiVax;
	
	private URL imageJetViruslvl;


	/////////////////////// CONSTRUCTOR ///////////////////////////
	public Animation(Double w, Double h, ControllerLevel cg, Pane r, String nameFileJet) {
		widthWindow = w;
		heightWindow = h;
		road = r;
		controllerLevel = cg;
		this.level = new Level((ControllerLevel) cg, 20, cg.getNumberOfAntiVax());

		player = cg.getPlayer();
		virusCloud = this.level.getVirusCloud();
		virusCloudSecondRow = this.level.getVirusCloudSecondRow();
		virusCloudThirdRow = this.level.getVirusCloudThirdRow();
		
		isVirusGoingLeftFirstRow = false;
		isVirusGoingLeftSecondRow = false;
		isVirusGoingLeftThirdRow = false;

		lastUpdate = 0;

		imageRechargeMunition = getClass().getResource("../Images/rechargeMunition.png");
		listSendMunition = new ArrayList<ImageView>();

		imageExtraLife = getClass().getResource("../Images/newlife.png");
		listSendExtraLife = new ArrayList<ImageView>();
		timerSendExtraLife = maxTimerSendExtraLife;
		
		//Setting of each levels
		timerSendMunition = controllerLevel.getMaxTimerSendMunition();
		timerAntiVaxAttack = controllerLevel.getMaxTimerAntiVaxAttack();
		timerVirusShoot = controllerLevel.getMaxTimerVirusShoot() - 5;
		numberOfAntiVax = controllerLevel.getNumberOfAntiVax();
		
		imageJetViruslvl = getClass().getResource(nameFileJet);

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
		virusShoot();

		if(this.level.getNumberOfLevel() == 1 || this.level.getNumberOfLevel() == 2) {
			moveVirusesLevel1and2();
		}
		if(this.level.getNumberOfLevel() == 3 || this.level.getNumberOfLevel() == 4) {
			moveVirusesLevel3and4();
			//virusShootSecondRow();
		}
		if(this.level.getNumberOfLevel() == 5) {
			moveVirusesLevel5();
			//virusShootSecondRow();
			//virusShootThirdRow();
		}
		sendMunition();
		addMunition();
		sendExtraLife();
		addExtraLife();
		antiVaxAttack();

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
	 * moveVirusesLevel1and2(): move viruses from level 1 to 2
	 */
	public void moveVirusesLevel1and2() {
		if(virusCloud.getSize() > 0) {
			moveVirusesOneRow(this.virusCloud, 1);
		}
	}
	
	/**
	 * moveVirusesLevel3and4(): move viruses from level 3 to 4
	 */
	public void moveVirusesLevel3and4() {
		if(virusCloud.getSize() > 0) {
			moveVirusesOneRow(this.virusCloud, 1);
		}
		if(virusCloudSecondRow.getSize() > 0) {
			moveVirusesOneRow(this.virusCloudSecondRow, 2);
		}
	}
	
	/**
	 * moveVirusesLevel5(): move viruses from level 5
	 */
	public void moveVirusesLevel5() {
		if(virusCloud.getSize() > 0) {
			moveVirusesOneRow(this.virusCloud, 1);
		}
		if(virusCloudSecondRow.getSize() > 0) {
			moveVirusesOneRow(this.virusCloudSecondRow, 2);
		}
		if(virusCloudThirdRow.getSize() > 0) {
			moveVirusesOneRow(this.virusCloudThirdRow, 3);
		}
	}
	
	/**
	 * moveViruses(): moves viruses from left to right and vice versa
	 * @param list a VirusCloud
	 * @param numberRow int, the number of the row where is located the VirusCloud
	 */
	public void moveVirusesOneRow(VirusCloud list, int numberRow) {
		if(list.getSize() > 0) {
			Virus firstVirus = list.getVirus(0);
			Virus lastVirus = list.getVirus(list.getSize()-1);
			
			if(numberRow == 1) { // if the list is located on the first row
				if(isVirusGoingLeftFirstRow) {
					if(firstVirus.getPosX() > 0) {
						goLeft(list);
					}else {
						isVirusGoingLeftFirstRow = false;
						goRight(list);
					}
				}else{
					if(lastVirus.getPosX()+lastVirus.getImageVirus().getFitWidth() > widthWindow ) {
						isVirusGoingLeftFirstRow = true;
						goLeft(list);
					}else {
						goRight(list);
					}
				}
			}else if(numberRow == 2) { // if the list is located on the second row
				if(isVirusGoingLeftSecondRow) {
					if(firstVirus.getPosX() > 0) {
						goLeft(list);
					}else {
						isVirusGoingLeftSecondRow = false;
						goRight(list);
					}
				}else{
					if(lastVirus.getPosX()+lastVirus.getImageVirus().getFitWidth() > widthWindow ) {
						isVirusGoingLeftSecondRow = true;
						goLeft(list);
					}else {
						goRight(list);
					}
				}
			}else if(numberRow == 3) { // if the list is located on the third row
				if(isVirusGoingLeftThirdRow) {
					if(firstVirus.getPosX() > 0) {
						goLeft(list);
					}else {
						isVirusGoingLeftThirdRow = false;
						goRight(list);
					}
				}else{
					if(lastVirus.getPosX()+lastVirus.getImageVirus().getFitWidth() > widthWindow ) {
						isVirusGoingLeftThirdRow = true;
						goLeft(list);
					}else {
						goRight(list);
					}
				}
			}
		}
	}
	
	/**
	 * goLeft(): move viruses to the left
	 */
	public void goLeft(VirusCloud list) {
		Virus virus;
		for(int i = 0; i < list.getSize(); i++) {
			virus = list.getVirus(i);
			virus.setPosX(virus.getPosX()-10);
		}
	}
	
	/**
	 * goRight(): move viruses to the right
	 */
	public void goRight(VirusCloud list) {
		Virus virus;
		for(int i = 0; i < list.getSize(); i++) {
			virus = list.getVirus(i);
			virus.setPosX(virus.getPosX()+10);
		}
	}

	/**
	 * moveViruses(): moves viruses from left to right and vice versa
	 */
	/*
	public void moveViruses() {
		if(virusCloud.getSize() > 0) {
			Virus firstVirus = virusCloud.getVirus(0);
			Virus lastVirus = virusCloud.getVirus(virusCloud.getSize()-1);

			if(isVirusGoingLeftFirstRow) {
				if(firstVirus.getPosX() > 0) {
					goLeftVirus();
				}else {
					isVirusGoingLeftFirstRow = false;
					goRightVirus();
				}
			}else{
				if(lastVirus.getPosX()+lastVirus.getImageVirus().getFitWidth() > widthWindow ) {
					isVirusGoingLeftFirstRow = true;
					goLeftVirus();
				}else {
					goRightVirus();
				}
			}
		}
	}*/
	/**
	 * goLeft(): move viruses to the left
	 */
	/*
	public void goLeftVirus() {
		Virus virus;
		for(int i = 0; i < virusCloud.getSize(); i++) {
			virus = virusCloud.getVirus(i);
			virus.setPosX(virus.getPosX()-10);
		}
	}*/

	/**
	 * goRight(): move viruses to the right
	 */
	/*
	public void goRightVirus() {
		Virus virus;
		for(int i = 0; i < virusCloud.getSize(); i++) {
			virus = virusCloud.getVirus(i);
			virus.setPosX(virus.getPosX()+10);
		}
	}*/

	/**
	 * checkCollisionVirusJet(): check if a player's jet touches a virus,
	 * 							if yes, remove this virus from the game
	 */
	public void checkCollisionVirusJet() {
		for(int i = 0; i < player.getListJet().getSize(); i++) {
			for(int j = 0; j < virusCloudThirdRow.getSize(); j++) { //check collision in third row
				if(player.getListJet().getJet(i).getImageJet().getBoundsInParent()
						.intersects(virusCloudThirdRow.getVirus(j).getImageVirus().getBoundsInParent())) {

					controllerLevel.setScore(virusCloudThirdRow.getVirus(j).getPointVirus());
					player.setScore(player.getScore() + level.getPoint());

					road.getChildren().remove(virusCloudThirdRow.getVirus(j).getImageVirus());
					virusCloudThirdRow.removeVirus(j);
					
					//remove the jet if this jet already touches a virus from the third row
					road.getChildren().remove(player.getListJet().getJet(i).getImageJet());
					player.getListJet().removeJet(i);
					return;
				}
			}
			for(int k = 0; k < virusCloudSecondRow.getSize(); k++) { //check collision in second row
				if(player.getListJet().getJet(i).getImageJet().getBoundsInParent()
						.intersects(virusCloudSecondRow.getVirus(k).getImageVirus().getBoundsInParent())) {

					controllerLevel.setScore(virusCloudSecondRow.getVirus(k).getPointVirus());
					player.setScore(player.getScore() + level.getPoint());

					road.getChildren().remove(virusCloudSecondRow.getVirus(k).getImageVirus());
					virusCloudSecondRow.removeVirus(k);
					
					//remove the jet if this jet already touches a virus from the second row
					road.getChildren().remove(player.getListJet().getJet(i).getImageJet());
					player.getListJet().removeJet(i);
					return;
				}
			}
			for(int l = 0; l < virusCloud.getSize(); l++) { //check collision in first row
				if(player.getListJet().getJet(i).getImageJet().getBoundsInParent()
						.intersects(virusCloud.getVirus(l).getImageVirus().getBoundsInParent())) {

					controllerLevel.setScore(virusCloud.getVirus(l).getPointVirus());
					player.setScore(player.getScore() + level.getPoint());

					road.getChildren().remove(virusCloud.getVirus(l).getImageVirus());
					virusCloud.removeVirus(l);
					return;
				}
			}
		}
	}


	/**
	 * virusShoot(): shoot a virus' jet if this is time and if there is viruses in the cloud
	 */
	public void virusShoot(){
		if(timerVirusShoot == controllerLevel.getMaxTimerVirusShoot() && virusCloud.getSize() > 0) {
			timerVirusShoot = 0;
			int i = (int) (Math.random() * (virusCloud.getSize()));
			Jet j = new Jet(virusCloud.getVirus(i).getPosX() + 17,
					virusCloud.getVirus(i).getPosY() + 15, imageJetViruslvl);
			road.getChildren().add(j.getImageJet());
			virusCloud.getListJet().addJet(j);
		}
		else{
			timerVirusShoot++;
		}
		moveVirusShoot();
	}
	
	public void virusShootSecondRow() {
		if(timerVirusShoot == controllerLevel.getMaxTimerVirusShoot()+10 && virusCloudSecondRow.getSize() > 0) {
			timerVirusShoot = 0;
			int i = (int) (Math.random() * (virusCloudSecondRow.getSize()));
			Jet j = new Jet(virusCloudSecondRow.getVirus(i).getPosX() + 17,
					virusCloudSecondRow.getVirus(i).getPosY() + 15, imageJetViruslvl);
			road.getChildren().add(j.getImageJet());
			virusCloudSecondRow.getListJet().addJet(j);
		}
		else{
			timerVirusShoot++;
		}
		moveVirusShootSecondRow();
	}
	
	public void virusShootThirdRow() {
		if(timerVirusShoot == controllerLevel.getMaxTimerVirusShoot()+20 && virusCloudThirdRow.getSize() > 0) {
			timerVirusShoot = 0;
			int i = (int) (Math.random() * (virusCloudThirdRow.getSize()));
			Jet j = new Jet(virusCloudThirdRow.getVirus(i).getPosX() + 17,
					virusCloudThirdRow.getVirus(i).getPosY() + 15, imageJetViruslvl);
			road.getChildren().add(j.getImageJet());
			virusCloudThirdRow.getListJet().addJet(j);
		}
		else{
			timerVirusShoot++;
		}
		moveVirusShootThirdRow();
	}

	/**
	 * moveVirusShoot(): move viruses' jets down
	 */
	public void moveVirusShoot(){
		for(int i = 0; i < virusCloud.getListJet().getSize(); i++){
			virusCloud.getListJet().getJet(i)
					.setPosY(virusCloud.getListJet().getJet(i).getPosY() + speedSendMunition);
		}
	}
	
	/**
	 * moveVirusShootSecondRow(): move second rowcviruses' jets down
	 */
	public void moveVirusShootSecondRow(){
		for(int i = 0; i < virusCloudSecondRow.getListJet().getSize(); i++){
			virusCloudSecondRow.getListJet().getJet(i)
					.setPosY(virusCloudSecondRow.getListJet().getJet(i).getPosY() + speedSendMunition);
		}
	}
	
	/**
	 * moveVirusShootThirdRow(): move third row viruses' jets down
	 */
	public void moveVirusShootThirdRow(){
		for(int i = 0; i < virusCloudThirdRow.getListJet().getSize(); i++){
			virusCloudThirdRow.getListJet().getJet(i)
					.setPosY(virusCloudThirdRow.getListJet().getJet(i).getPosY() + speedSendMunition);
		}
	}

	/**
	 * checkCollisionVirusShootPlayer(): check if a virus' jet touch the player,
	 * 									if yes, remove a life from the player remaining lives
	 */
	public void checkCollisionVirusShootPlayer(){
		for(int i = 0; i < virusCloud.getListJet().getSize(); i++){
			if(player.imageView.getBoundsInParent()
					.intersects(virusCloud.getListJet().getJet(i).getImageJet().getBoundsInParent())){

				controllerLevel.removeLife();
				road.getChildren().remove(virusCloud.getListJet().getJet(i).getImageJet());
				virusCloud.getListJet().removeJet(i);
			}
		}/*
		for(int i = 0; i < virusCloudSecondRow.getListJet().getSize(); i++){
			if(player.imageView.getBoundsInParent()
					.intersects(virusCloudSecondRow.getListJet().getJet(i).getImageJet().getBoundsInParent())){

				controllerLevel.removeLife();
				road.getChildren().remove(virusCloudSecondRow.getListJet().getJet(i).getImageJet());
				virusCloudSecondRow.getListJet().removeJet(i);
			}
		}
		for(int i = 0; i < virusCloudThirdRow.getListJet().getSize(); i++){
			if(player.imageView.getBoundsInParent()
					.intersects(virusCloudThirdRow.getListJet().getJet(i).getImageJet().getBoundsInParent())){

				controllerLevel.removeLife();
				road.getChildren().remove(virusCloudThirdRow.getListJet().getJet(i).getImageJet());
				virusCloudThirdRow.getListJet().removeJet(i);
			}
		}*/
	}

	/**
	 * sendExtraLife(): send an extra life, depending on the number of viruses left + number of remaining life
	 */
	public void sendExtraLife() {
		if(virusCloud.getSize() <= 3 && player.getLife() == 1) {
			if(timerSendExtraLife == maxTimerSendExtraLife) {
				timerSendExtraLife = 0;
				ImageView newExtraLife = new ImageView(new Image(imageExtraLife.toExternalForm()));
				double randomX = new Random().nextDouble() * (maxRange - minRange) + minRange;
				newExtraLife.setLayoutX(randomX);
				newExtraLife.setLayoutY(0);

				this.listSendExtraLife.add(newExtraLife);
				road.getChildren().add(newExtraLife);
			}else {
				timerSendExtraLife++;
			}
		}
		moveSendExtraLife();
	}

	/**
	 * moveSendExtaLife(): move all extra lives that are already sent
	 * 						and remove extra lives that weren't catch by the player
	 */
	public void moveSendExtraLife() {
		if(this.listSendExtraLife.size() != 0) {
			ImageView extraLifeToDelete = new ImageView();
			for(int i = 0; i < this.listSendExtraLife.size(); i++) {
				extraLifeToDelete = this.listSendExtraLife.get(i);
				extraLifeToDelete.setLayoutY(extraLifeToDelete.getLayoutY() + speedSendExtraLife);
				if(extraLifeToDelete.getLayoutY() > heightWindow) {
					this.listSendExtraLife.remove(extraLifeToDelete);
				}
			}
		}
	}


	/**
	 * addExtraLife(): add an extra life to the player if he touches it
	 */
	public void addExtraLife(){
		for(int i = 0; i < this.listSendExtraLife.size(); i++) {
			if (player.getImageViewPlayer().getBoundsInParent()
					.intersects(this.listSendExtraLife.get(i).getBoundsInParent())) {

				controllerLevel.addExtraLifeToList();
				road.getChildren().remove(this.listSendExtraLife.get(i));
				this.listSendExtraLife.remove(this.listSendExtraLife.get(i));
			}
		}
	}


	/**
	 * sendMunition(): send a munition to the player if this is time
	 */
	public void sendMunition() {
		if(player.getAvailableJet() < 9) {
			if(timerSendMunition == controllerLevel.getMaxTimerSendMunition()) {
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
				controllerLevel.addMunitionToList();

			}
		}
	}


	/**
	 * antiVaxAttack(): add an antiVax to the game when this is time
	 */
	public void antiVaxAttack(){
		if(virusCloud.getSize() < 5 && level.getNumberOfAntiVax() > level.getListAntiVax().size()){
			if(timerAntiVaxAttack == controllerLevel.getMaxTimerAntiVaxAttack()) {
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

					controllerLevel.setScore(level.getListAntiVax().get(i).getPoint());
					player.setScore(player.getScore() + level.getPoint());

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
					.intersects(player.imageView.getBoundsInParent())) {

				controllerLevel.removeLife();
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/win.fxml"));
		Pane myPane = loader.load();
		ControllerWin controllerWin = loader.getController();

		controllerWin.setCurrentLevelText(" " + level.getNumberOfLevel());
		controllerWin.setScoreText(" " + player.getScore() + " points");
		controllerWin.setNextLevelText(Integer.toString(level.getNumberOfLevel()+1));
		controllerWin.setNextLevel(this.level.getNumberOfLevel()+1);
		controllerWin.setPlayer(player);
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/lost.fxml"));
		Pane myPane = loader.load();
		ControllerLost controllerLost = loader.getController();

		controllerLost.setLevel(" " + level.getNumberOfLevel());
		controllerLost.setScore(" " + player.getScore() + " points");
		controllerLost.setStage(primaryStage);

		Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
		myScene.getRoot().requestFocus();
		primaryStage.setScene(myScene);
		primaryStage.show();

		stop();
	}



}