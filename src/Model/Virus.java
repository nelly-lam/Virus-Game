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
    	
    	/*
    	hitbox = new Rectangle(posX, posY, imageVirus.getFitWidth(), imageVirus.getFitHeight());
    	//hitbox.setOpacity(0);
    	hitbox.setFill(Color.PEACHPUFF);
    	*/
    }


    /////////////////////// METHODS ///////////////////////////

	public Double getPosX() { return posX; }
	public void setPosX(Double posX) {
		this.posX = posX;
		imageVirus.setLayoutX(posX);
		
        /* update the position of the hitbox */
        //this.hitbox.setX(this.posX);
	}

	public Double getPosY() { return posY; }
	public void setPosY(Double posY) {
		this.posY = posY;
		imageVirus.setLayoutY(posY);
		
        /* update the position of the hitbox */
        //this.hitbox.setY(this.posY);
	}

	public Rectangle getHitbox() { return hitbox; }
	public void setHitbox(Rectangle hitboxPlayer) { this.hitbox = hitboxPlayer; }
	
    public ImageView getImageVirus() { return imageVirus; }
    
	public boolean getIsAlive() { return isAlive; }
	public void setIsAlive(boolean isAlive) { this.isAlive = isAlive; }

}
