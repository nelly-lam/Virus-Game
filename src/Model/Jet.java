package Model;

import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Jet {
	
	///////////////////// ATTRIBUTES /////////////////////////

	/* position of the left up corner 
	 * that defines the position of the virus */
	private Double posX;
	private Double posY;
	
    /* image of the jet */
	private ImageView imageJet;
	
	
	
    ///////////////////// CONSTRUCTOR ///////////////////////
	public Jet(Double x, Double y, URL imageJeturl) {
		posX = x;
		posY = y;

        imageJet = new ImageView(new Image(imageJeturl.toExternalForm()));
        imageJet.setLayoutX(x);
        imageJet.setLayoutY(y);

	}

	
    ///////////////////// METHODS ///////////////////////
	public Double getPosY() { return posY; }
	public void setPosY(Double posY) {
		this.posY = posY;
        this.imageJet.setLayoutY(this.posY);
	}
	
	public ImageView getImageJet() { return imageJet; }
	public void setImageJet(ImageView imageJet) { this.imageJet = imageJet; }



}
