package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.net.URL;

public class Player {
    
	///////////////////// ATTRIBUTES /////////////////////////

	/* position of the left up corner 
	 * that defines the position of the virus */
    private Double posX;
    private Double posY;
    
    /* hitbox of the player */
    private Rectangle hitbox;
    
	/* images of the virus */
    Image imagePlayer;
    Image imageG;
    Image imageD;
    Image imageShoot;
    ImageView i;
    
    /* jets that are displayed on the window */
    private ListJet listJet;
    /* number of jet the player has */
    private int availableJet;
    
    
    ///////////////////// CONSTRUCTOR ///////////////////////
    public Player(ImageView i){
        this.posX = i.getLayoutX();
        this.posY = i.getLayoutY();
        
        this.i = i;
        
        /* imagePlayer = imageG ? */
        this.imagePlayer = i.getImage();
        this.imageG = i.getImage();
        
        URL imageDurl = getClass().getResource("../Images/playerD.png");
        this.imageD = new Image(imageDurl.toExternalForm());
        URL imageShooturl = getClass().getResource("../Images/playerShooting.png");
        this.imageShoot = new Image(imageShooturl.toExternalForm());
        
    	hitbox = new Rectangle(posX, posY, i.getFitWidth(), i.getFitHeight());
    	hitbox.setOpacity(0);
    	
        this.listJet = new ListJet();
        this.availableJet = 10;
        
    }


    ///////////////////// METHODS ///////////////////////

    public Double getPosY() {  return posY; }

    public Double getPosX() { return posX; }
    public void setPosX(Double posX) {
        this.posX = posX;
        this.i.setLayoutX(posX);

        /* update the position of the hitbox */
        this.hitbox.setX(this.posX);
    }
    
	public Rectangle getHitbox() { return hitbox; }
	public void setHitbox(Rectangle hitbox) { this.hitbox = hitbox; }
	
    public Image getImageD() { return imageD; }
    public Image getImageG() { return imageG; }
    public Image getImageShoot() { return imageShoot; }

    public Image getImagePlayer() { return imagePlayer; }
    public void setImagePlayer(Image imagePlayer) {
        this.i.setImage(imagePlayer);
        this.imagePlayer = imagePlayer;
    }

	public ListJet getListJet() { return listJet; }
	public void setListJet(ListJet listJet) { this.listJet = listJet; }

	public int getAvailableJet() { return availableJet; }
	public void setAvailableJet(int jetOnHand) { this.availableJet = jetOnHand; }
}
