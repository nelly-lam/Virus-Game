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


    /* point of the virus */
    private int pointVirus;
    
    
    ///////////////////// CONSTRUCTOR ///////////////////////
    public Virus(ImageView img, int point){
    	posX = img.getLayoutX();
    	posY = img.getLayoutY();
    	this.pointVirus = point;
    	imageVirus = img;

    }


    /////////////////////// METHODS ///////////////////////////
	public Double getPosX() { return posX; }

	public void setPosX(Double posX) {
		this.posX = posX;
		imageVirus.setLayoutX(posX);

	}

	public Double getPosY() { return posY; }


	public int getPointVirus() { return pointVirus; }

	public ImageView getImageVirus() { return imageVirus; }

}
