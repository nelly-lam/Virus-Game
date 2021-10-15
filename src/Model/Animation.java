package Model;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import Controller.ControllerLevel;
import Controller.ControllerEnd;
import Controller.ControllerWinLevel;
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

	public final static int speedSendMunition = 25;
	public final static int speedSendExtraLife = 25;

	public final static int speedAntiVaxAttackX = 10;
	public final static int speedAntiVaxAttackY = 10;

	public final static long maxTimerSendExtraLife = 50;


	//////////////////////////// ATTRIBUTES /////////////////////////////
	private Double widthWindow;
	private Double heightWindow;
	private Pane road;
	private ControllerLevel controllerLevel;
	private Level level;

	private Player player;
	private Score score;
	
	private VirusCloud virusCloudFirstRow;
	private VirusCloud virusCloudSecondRow;
	private VirusCloud virusCloudThirdRow;
	private VirusCloud totalVirus;
	private int numberTotalVirus;
	
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
	
	private long timerVirusShootFirstRow;
	private long timerVirusShootSecondRow;
    private long timerVirusShootThirdRow;
	
	private URL imageJetVirusLvl;



	/////////////////////// CONSTRUCTOR ///////////////////////////
	public Animation(Double w, Double h, ControllerLevel cg, Pane r, String nameFileJet, Score s) {
		widthWindow = w;
		heightWindow = h;
		road = r;
		controllerLevel = cg;
		this.level = new Level((ControllerLevel) cg, 20, cg.getNumberOfAntiVax());

		player = cg.getPlayer();
		score = s;
		
		virusCloudFirstRow = this.level.getVirusCloud();
		virusCloudSecondRow = this.level.getVirusCloudSecondRow();
		virusCloudThirdRow = this.level.getVirusCloudThirdRow();
		
		totalVirus = new VirusCloud();
		totalVirus.addVirusCloud(virusCloudFirstRow);
		if(level.getNumberOfLevel() == 3 || level.getNumberOfLevel() == 4 || level.getNumberOfLevel() == 5) {
			totalVirus.addVirusCloud(virusCloudSecondRow);
		}
		if(level.getNumberOfLevel() == 5) {
			totalVirus.addVirusCloud(virusCloudThirdRow);
		}
		numberTotalVirus = totalVirus.getSize();
		
		isVirusGoingLeftFirstRow = false;
		isVirusGoingLeftSecondRow = false;
		isVirusGoingLeftThirdRow = false;

		lastUpdate = 0;

		imageRechargeMunition = getClass().getResource("../Images/rechargeMunition.png");
		listSendMunition = new ArrayList<ImageView>();

		imageExtraLife = getClass().getResource("../Images/newlife.png");
		listSendExtraLife = new ArrayList<ImageView>();
		timerSendExtraLife = maxTimerSendExtraLife;
		
		//Setting difficulties for each level
		timerSendMunition = controllerLevel.getMaxTimerSendMunition();
		timerAntiVaxAttack = controllerLevel.getMaxTimerAntiVaxAttack();
		timerVirusShootFirstRow = controllerLevel.getMaxTimerVirusShootFirstRow();
        timerVirusShootSecondRow = controllerLevel.getMaxTimerVirusShootSecondRow();
        timerVirusShootThirdRow = controllerLevel.getMaxTimerVirusShootThirdRow();
		
		imageJetVirusLvl = getClass().getResource(nameFileJet);

	}

	//////////////////////////// METHODS /////////////////////////////
	public Score getScore() { return score; }
	public void setScore(Score score) { this.score = score; }

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
		virusShootFirstRow();

		if(this.level.getNumberOfLevel() == 1 || this.level.getNumberOfLevel() == 2) {
			moveVirusesLevel1and2();
		}
		if(this.level.getNumberOfLevel() == 3 || this.level.getNumberOfLevel() == 4) {
			moveVirusesLevel3and4();
			virusShootSecondRow();
		}
		if(this.level.getNumberOfLevel() == 5) {
			moveVirusesLevel5();
			virusShootSecondRow();
			virusShootThirdRow();
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
		if(virusCloudFirstRow.getSize() > 0) {
			moveVirusesOneRow(this.virusCloudFirstRow, 1);
		}
	}
	
	/**
	 * moveVirusesLevel3and4(): move viruses from level 3 to 4
	 */
	public void moveVirusesLevel3and4() {
		if(virusCloudFirstRow.getSize() > 0) {
			moveVirusesOneRow(this.virusCloudFirstRow, 1);
		}
		if(virusCloudSecondRow.getSize() > 0) {
			moveVirusesOneRow(this.virusCloudSecondRow, 2);
		}
	}
	
	/**
	 * moveVirusesLevel5(): move viruses from level 5
	 */
	public void moveVirusesLevel5() {
		if(virusCloudFirstRow.getSize() > 0) {
			moveVirusesOneRow(this.virusCloudFirstRow, 1);
		}
		if(virusCloudSecondRow.getSize() > 0) {
			moveVirusesOneRow(this.virusCloudSecondRow, 2);
		}
		if(virusCloudThirdRow.getSize() > 0) {
			moveVirusesOneRow(this.virusCloudThirdRow, 3);
		}
	}
	
	/**
	 * moveViruses(): moves a VirusCloud from left to right and vice versa
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
						goLeftViruses(list);
					}else {
						isVirusGoingLeftFirstRow = false;
						goRightViruses(list);
					}
				}else{
					if(lastVirus.getPosX()+lastVirus.getImageVirus().getFitWidth() > widthWindow) {
						isVirusGoingLeftFirstRow = true;
						goLeftViruses(list);
					}else {
						goRightViruses(list);
					}
				}
			}else if(numberRow == 2) { // if the list is located on the second row
				if(isVirusGoingLeftSecondRow) {
					if(firstVirus.getPosX() > 0) {
						goLeftViruses(list);
					}else {
						isVirusGoingLeftSecondRow = false;
						goRightViruses(list);
					}
				}else{
					if(lastVirus.getPosX()+lastVirus.getImageVirus().getFitWidth() > widthWindow ) {
						isVirusGoingLeftSecondRow = true;
						goLeftViruses(list);
					}else {
						goRightViruses(list);
					}
				}
			}else if(numberRow == 3) { // if the list is located on the third row
				if(isVirusGoingLeftThirdRow) {
					if(firstVirus.getPosX() > 0) {
						goLeftViruses(list);
					}else {
						isVirusGoingLeftThirdRow = false;
						goRightViruses(list);
					}
				}else{
					if(lastVirus.getPosX()+lastVirus.getImageVirus().getFitWidth() > widthWindow ) {
						isVirusGoingLeftThirdRow = true;
						goLeftViruses(list);
					}else {
						goRightViruses(list);
					}
				}
			}
		}
	}
	
	/**
	 * goLeft(): move viruses to the left
	 */
	public void goLeftViruses(VirusCloud list) {
		Virus virus;
		for(int i = 0; i < list.getSize(); i++) {
			virus = list.getVirus(i);
			virus.setPosX(virus.getPosX()-10);
		}
	}
	
	/**
	 * goRight(): move viruses to the right
	 */
	public void goRightViruses(VirusCloud list) {
		Virus virus;
		for(int i = 0; i < list.getSize(); i++) {
			virus = list.getVirus(i);
			virus.setPosX(virus.getPosX()+10);
		}
	}


	/**
	 * checkCollisionVirusJet(): check if a player's jet touches a virus,
	 * 							if yes, remove this virus from the game
	 */
	public void checkCollisionVirusJet() {
		for(int i = 0; i < player.getListJet().getSize(); i++) {
			for(int j = 0; j < virusCloudThirdRow.getSize(); j++) { //check collision in third row
				if(player.getListJet().getJet(i).getImageJet().getBoundsInParent()
						.intersects(virusCloudThirdRow.getVirus(j).getImageVirus().getBoundsInParent())) {

					controllerLevel.setScoreText(virusCloudThirdRow.getVirus(j).getPointVirus());
					score.setCurrentScore(score.getCurrentScore() + level.getPoint());

					road.getChildren().remove(virusCloudThirdRow.getVirus(j).getImageVirus());
					virusCloudThirdRow.removeVirus(j);
					totalVirus.remove();
					
					//remove the jet if this jet already touches a virus from the third row
					road.getChildren().remove(player.getListJet().getJet(i).getImageJet());
					player.getListJet().removeJet(i);
					return;
				}
			}
			for(int k = 0; k < virusCloudSecondRow.getSize(); k++) { //check collision in second row
				if(player.getListJet().getJet(i).getImageJet().getBoundsInParent()
						.intersects(virusCloudSecondRow.getVirus(k).getImageVirus().getBoundsInParent())) {

					controllerLevel.setScoreText(virusCloudSecondRow.getVirus(k).getPointVirus());
					score.setCurrentScore(score.getCurrentScore() + level.getPoint());

					road.getChildren().remove(virusCloudSecondRow.getVirus(k).getImageVirus());
					virusCloudSecondRow.removeVirus(k);
					totalVirus.remove();

					//remove the jet if this jet already touches a virus from the second row
					road.getChildren().remove(player.getListJet().getJet(i).getImageJet());
					player.getListJet().removeJet(i);
					return;
				}
			}
			for(int l = 0; l < virusCloudFirstRow.getSize(); l++) { //check collision in first row
				if(player.getListJet().getJet(i).getImageJet().getBoundsInParent()
						.intersects(virusCloudFirstRow.getVirus(l).getImageVirus().getBoundsInParent())) {
					
					controllerLevel.setScoreText(virusCloudFirstRow.getVirus(l).getPointVirus());
					score.setCurrentScore(score.getCurrentScore() + level.getPoint());

					road.getChildren().remove(virusCloudFirstRow.getVirus(l).getImageVirus());
					virusCloudFirstRow.removeVirus(l);
					totalVirus.remove();

					return;
				}
			}
		}
	}


	/**
	 * virusShoot(): shoot a virus' jet from the first row
	 * 				if this is time and if there is viruses in the cloud
	 */
	public void virusShootFirstRow(){
		if(timerVirusShootFirstRow == controllerLevel.getMaxTimerVirusShootFirstRow() && virusCloudFirstRow.getSize() > 0) {
			timerVirusShootFirstRow = 0;
			int i = (int) (Math.random() * (virusCloudFirstRow.getSize()));
			Jet j = new Jet(virusCloudFirstRow.getVirus(i).getPosX() + 17,
					virusCloudFirstRow.getVirus(i).getPosY() + 15, imageJetVirusLvl);
			road.getChildren().add(j.getImageJet());
			virusCloudFirstRow.getListJet().addJet(j);
		}
		else{
			timerVirusShootFirstRow++;
		}
		moveVirusShoot(virusCloudFirstRow);
	}
	
	/**
	 * virusShootSecondRow(): shoot a virus' jet from the second row 
	 * 							if this is time and if there is viruses in the cloud
	 */
	public void virusShootSecondRow() {
		if(timerVirusShootSecondRow == controllerLevel.getMaxTimerVirusShootFirstRow() && virusCloudSecondRow.getSize() > 0) {
			timerVirusShootSecondRow = 0;
			int i = (int) (Math.random() * (virusCloudSecondRow.getSize()));
			Jet j = new Jet(virusCloudSecondRow.getVirus(i).getPosX() + 17,
					virusCloudSecondRow.getVirus(i).getPosY() + 15, imageJetVirusLvl);
			road.getChildren().add(j.getImageJet());
			virusCloudSecondRow.getListJet().addJet(j);
		}
		else{
			timerVirusShootSecondRow++;
		}
		moveVirusShoot(virusCloudSecondRow);
	}
	
	/**
	 * virusShootThirdRow(): shoot a virus' jet from the third row 
	 * 							if this is time and if there is viruses in the cloud
	 */
	public void virusShootThirdRow() {
		if(timerVirusShootThirdRow == controllerLevel.getMaxTimerVirusShootFirstRow()+10 && virusCloudThirdRow.getSize() > 0) {
			timerVirusShootThirdRow = 0;
			int i = (int) (Math.random() * (virusCloudThirdRow.getSize()));
			Jet j = new Jet(virusCloudThirdRow.getVirus(i).getPosX() + 17,
					virusCloudThirdRow.getVirus(i).getPosY() + 15, imageJetVirusLvl);
			road.getChildren().add(j.getImageJet());
			virusCloudThirdRow.getListJet().addJet(j);
		}
		else{
			timerVirusShootThirdRow++;
		}
		moveVirusShoot(virusCloudThirdRow);
	}

	/**
	 * moveVirusShoot(): move jets of a VirusCloud down
	 * @param list, list of VirusCloud whose viruses will move
	 */
	public void moveVirusShoot(VirusCloud list){
		for(int i = 0; i < list.getListJet().getSize(); i++){
			list.getListJet().getJet(i)
					.setPosY(list.getListJet().getJet(i).getPosY() + speedSendMunition);
		}
	}
	

	/**
	 * checkCollisionVirusShootPlayer(): check if a virus' jet touch the player,
	 * 									if yes, remove a life from the player remaining lives
	 */
	public void checkCollisionVirusShootPlayer(){
		for(int i = 0; i < virusCloudFirstRow.getListJet().getSize(); i++){ //check collision from first row
			if(player.imageView.getBoundsInParent()
					.intersects(virusCloudFirstRow.getListJet().getJet(i).getImageJet().getBoundsInParent())){

				controllerLevel.removeLife();
				road.getChildren().remove(virusCloudFirstRow.getListJet().getJet(i).getImageJet());
				virusCloudFirstRow.getListJet().removeJet(i);
			}
		}
		for(int j = 0; j < virusCloudSecondRow.getListJet().getSize(); j++){ //check collision from second row
			if(player.imageView.getBoundsInParent()
					.intersects(virusCloudSecondRow.getListJet().getJet(j).getImageJet().getBoundsInParent())){

				controllerLevel.removeLife();
				road.getChildren().remove(virusCloudSecondRow.getListJet().getJet(j).getImageJet());
				virusCloudSecondRow.getListJet().removeJet(j);
			}
		}
		for(int k = 0; k < virusCloudThirdRow.getListJet().getSize(); k++){ //check collision from third row
			if(player.imageView.getBoundsInParent()
					.intersects(virusCloudThirdRow.getListJet().getJet(k).getImageJet().getBoundsInParent())){

				controllerLevel.removeLife();
				road.getChildren().remove(virusCloudThirdRow.getListJet().getJet(k).getImageJet());
				virusCloudThirdRow.getListJet().removeJet(k);
			}
		}
	}

	/**
	 * sendExtraLife(): send an extra life, depending on the number of viruses left + number of remaining life
	 */
	public void sendExtraLife() {
		if(totalVirus.getSize() <= numberTotalVirus/2 && player.getLife() == 1) {
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
		if(virusCloudFirstRow.getSize() < 5 && level.getNumberOfAntiVax() > level.getListAntiVax().size()){
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
			if(level.getListAntiVax().get(i).getPosX() < widthWindow/2){
				if(level.getListAntiVax().get(i).isInLeft()) {
					level.getListAntiVax().get(i).setPosX(level.getListAntiVax().get(i).getPosX() + speedAntiVaxAttackX);
				}
				else {
					level.getListAntiVax().get(i).setPosY(level.getListAntiVax().get(i).getPosY() + speedAntiVaxAttackY);
				}
			}
			else if (level.getListAntiVax().get(i).getPosX() < widthWindow 
						&& level.getListAntiVax().get(i).getPosX() > widthWindow/2){
				if (!level.getListAntiVax().get(i).isInLeft()) {
					level.getListAntiVax().get(i).setPosX(level.getListAntiVax().get(i).getPosX() - speedAntiVaxAttackX);
				}
				else {
					level.getListAntiVax().get(i).setPosY(level.getListAntiVax().get(i).getPosY() + speedAntiVaxAttackY);
				}
			}
			//remove antiVax that are not displayed anymore
			if(level.getListAntiVax().get(i).getPosY() > heightWindow){
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

					controllerLevel.setScoreText(level.getListAntiVax().get(i).getPoint());
					score.setCurrentScore(score.getCurrentScore() + level.getPoint());

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
		if(level.getNumberOfAntiVax() == 0 && virusCloudFirstRow.getSize() == 0){
			if(level.getNumberOfLevel() == 5) {
				launchWinGamePane();
			}else {
				//TODO: necessary???
				level.setWon(true);
				launchWinLevelPane();
			}
		}
	}

	/**
	 * playerLostLevel(): launch the next pane if the player lost the level he is in
	 * 						[lose if lost all his remaining lives]
	 * @throws IOException
	 */
	public void playerLostLevel() throws IOException {
		if(player.getLife() == 0){
			//TODO: necessary???
			level.setLost(true);
			launchLoseGamePane();
		}
	}


	/**
	 * launchWinLevelPane(): launch the won pane
	 * @throws IOException
	 */
	public void launchWinLevelPane() throws IOException {
		Stage primaryStage = (Stage) road.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/winLevel.fxml"));
		Pane myPane = loader.load();
		ControllerWinLevel controllerWin = loader.getController();

		controllerWin.setScoreNumber(Integer.toString(score.getCurrentScore()));
		controllerWin.setLevelNumber(Integer.toString(level.getNumberOfLevel()));
		controllerWin.setNextLevelNumber(this.level.getNumberOfLevel()+1);
		controllerWin.setScore(score);
		controllerWin.setStage(primaryStage);

		Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
		myScene.getRoot().requestFocus();
		primaryStage.setScene(myScene);
		primaryStage.show();

		stop();
	}
	
	/**
	 * launchWinGamePane(): launch the congrats pane
	 * @throws IOException
	 */
	public void launchWinGamePane() throws IOException {
		Stage primaryStage = (Stage) road.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/winGame.fxml"));
		Pane myPane = loader.load();
		ControllerEnd controllerEnd = loader.getController();

		controllerEnd.setScoreNumber(Integer.toString(score.getCurrentScore()));
		controllerEnd.setLevelNumber(Integer.toString(level.getNumberOfLevel()));
		controllerEnd.setScore(score);
		controllerEnd.setStage(primaryStage);

		Scene myScene = new Scene(myPane, myPane.getPrefWidth(),myPane.getPrefHeight());
		myScene.getRoot().requestFocus();
		primaryStage.setScene(myScene);
		primaryStage.show();

		stop();
	}

	/**
	 * launchLoseGamePane(): launch the lose pane
	 * @throws IOException
	 */
	public void launchLoseGamePane() throws IOException {
		Stage primaryStage = (Stage) road.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/loseGame.fxml"));
		Pane myPane = loader.load();
		ControllerEnd controllerEnd = loader.getController();

		controllerEnd.setLevelNumber(Integer.toString(level.getNumberOfLevel()));
		controllerEnd.setScoreNumber(Integer.toString(score.getCurrentScore()));
		controllerEnd.setScore(score);
		controllerEnd.setStage(primaryStage);

		Scene myScene = new Scene(myPane, myPane.getPrefWidth(), myPane.getPrefHeight());
		myScene.getRoot().requestFocus();
		primaryStage.setScene(myScene);
		primaryStage.show();

		stop();
	}





}