package Model;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Virus {
	
	///////////////////// ATTRIBUTES /////////////////////////
	/* position of the left up corner 
	 * that defines the position of the virus */
    private Double posX;
    private Double posY;
    
    /* hitbox of the virus */
    private Rectangle hitbox;
    
    /* image of the virus */
    private ImageView imageVirus;
    
    /* indicates if the virus is alive or not
     * (if it is shown or not) */
    private boolean isAlive;

    
    
    ///////////////////// CONSTRUCTOR ///////////////////////
    public Virus(ImageView img){
    	posX = img.getLayoutX();
    	posY = img.getLayoutY();
    	
    	imageVirus = img;
    	
    	hitbox = new Rectangle(posX, posY, posX+imageVirus.prefWidth(0), posY+imageVirus.prefHeight(0));
    	hitbox.setOpacity(1);
    	
    }


    /////////////////////// METHODS ///////////////////////////
    public ImageView getImageVirus() {
        return imageVirus;
    }

	public Double getPosX() {
		return posX;
	}

	public void setPosX(Double posX) {
		imageVirus.setLayoutX(posX);
		this.posX = posX;
	}

	public Double getPosY() {
		return posY;
	}

	public void setPosY(Double posY) {
		imageVirus.setLayoutY(posY);
		this.posY = posY;
	}


	public boolean getIsAlive() {
		return isAlive;
	}


	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}


	public Rectangle getHitbox() {
		return hitbox;
	}


	public void setHitbox(Rectangle hitboxPlayer) {
		this.hitbox = hitboxPlayer;
	}
}
