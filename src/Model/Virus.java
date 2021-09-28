package Model;

import javafx.scene.image.ImageView;


public class Virus {
	
	///////////////////// ATTRIBUTES /////////////////////////
	
	/* position of the left up corner 
	 * that defines the position of the virus */
    private Double posX;
    private Double posY;

    
    /* image of the virus */
    private ImageView imageVirus;
    
    /* indicates if the virus is alive or not
     * (if it is shown or not) */
    private boolean isAlive;

    private int point;
    
    ///////////////////// CONSTRUCTOR ///////////////////////
    public Virus(ImageView img, int point){
    	posX = img.getLayoutX();
    	posY = img.getLayoutY();
    	this.point = point;
    	imageVirus = img;

    }


    /////////////////////// METHODS ///////////////////////////

	public Double getPosX() { return posX; }

	public void setPosX(Double posX) {
		this.posX = posX;
		imageVirus.setLayoutX(posX);

	}

	public Double getPosY() { return posY; }
	public void setPosY(Double posY) {
		this.posY = posY;
		imageVirus.setLayoutY(posY);
	}

	public int getPoint() {
		return point;
	}

	public ImageView getImageVirus() { return imageVirus; }
    
	public boolean getIsAlive() { return isAlive; }
	public void setIsAlive(boolean isAlive) { this.isAlive = isAlive; }

}
