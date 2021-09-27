package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;

public class Player {
    Image imagePlayer;
    Image imageG;
    Image imageD;
    Image imageShoot;
    ImageView i;
    
    Double posX;
    Double posY;
    
    private Rectangle hitbox;
    
    private ListJet listJet;
    
    /* number of jet the player has */
    private int availableJet;
    
    ArrayList<ImageView> shoots = new ArrayList<>();


    public Player(ImageView i){
        this.i = i;
        this.imagePlayer = i.getImage();

        this.imageG = i.getImage();
        
        this.posX = i.getLayoutX();
        this.posY = i.getLayoutY();
        
    	hitbox = new Rectangle(posX, posY, i.getFitWidth(), i.getFitHeight());
    	hitbox.setOpacity(0);
        
        this.listJet = new ListJet();
        this.availableJet = 10;
        
        URL imageDurl = getClass().getResource("../Images/playerD.png");
        this.imageD = new Image(imageDurl.toExternalForm());
        
        URL imageShooturl = getClass().getResource("../Images/playerShooting.png");
        this.imageShoot = new Image(imageShooturl.toExternalForm());
    }


    public void addShoot(ImageView i){
        this.shoots.add(i);
    }



    public Double getPosY() {
        return posY;
    }

    public Double getPosX() {
        return posX;
    }

    public Image getImagePlayer() {
        return imagePlayer;
    }

    public Image getImageD() {
        return imageD;
    }

    public Image getImageG() {
        return imageG;
    }

    public Image getImageShoot() {
        return imageShoot;
    }

    public void setImagePlayer(Image imagePlayer) {
        this.i.setImage(imagePlayer);
        this.imagePlayer = imagePlayer;
    }

    public void setPosX(Double posX) {
        this.i.setLayoutX(posX);
        this.posX = posX;
    }


	public int getAvailableJet() {
		return availableJet;
	}


	public void setAvailableJet(int jetOnHand) {
		this.availableJet = jetOnHand;
	}


	public ListJet getListJet() {
		return listJet;
	}


	public void setListJet(ListJet listJet) {
		this.listJet = listJet;
	}


	public Rectangle getHitbox() {
		return hitbox;
	}


	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
}
