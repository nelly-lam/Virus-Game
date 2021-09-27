package Model;

import java.awt.*;

import javafx.scene.image.ImageView;

public class Virus {
	
	///////////////////// ATTRIBUTES /////////////////////////
	/* position of the left up corner 
	 * that defines the position of the virus */
    private Double posX;
    private Double posY;
    
    /* Position of the hitbox of the virus */
    private Point leftup;
    private Point leftbottom;
    private Point rightup;
    private Point rightbottom;
    
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
    	
    }


    /////////////////////// METHODS ///////////////////////////
    public ImageView getImageVirus() {
        return imageVirus;
    }

	public Double getPosX() {
		return posX;
	}

	public void setPosX(Double posX) {
		this.posX = posX;
	}

	public Double getPosY() {
		return posY;
	}

	public void setPosY(Double posY) {
		this.posY = posY;
	}


	public boolean getIsAlive() {
		return isAlive;
	}


	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}


	public Point getLeftup() {
		return leftup;
	}


	public void setLeftup(Point leftup) {
		this.leftup = leftup;
	}


	public Point getLeftbottom() {
		return leftbottom;
	}


	public void setLeftbottom(Point leftbottom) {
		this.leftbottom = leftbottom;
	}


	public Point getRightup() {
		return rightup;
	}


	public void setRightup(Point rightup) {
		this.rightup = rightup;
	}


	public Point getRightbottom() {
		return rightbottom;
	}


	public void setRightbottom(Point rightbottom) {
		this.rightbottom = rightbottom;
	}
}
