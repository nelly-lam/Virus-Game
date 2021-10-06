package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class AntiVax {

	///////////////////// CONSTANTS /////////////////////////
    public final static double maxRange = 600.0;
    public final static double maxHeight = 540.0;

	///////////////////// ATTRIBUTES /////////////////////////
    private URL file;
    private ImageView image;
    private int point;
    private boolean inLeft;

	///////////////////// CONSTRUTOR /////////////////////////
    public AntiVax(int p){
        file =  getClass().getResource("../Images/antivax.png");
        image = new ImageView(new Image(file.toExternalForm()));
        double randomX =  Math.random();
        
        //place antiVax either at left or right
        if(randomX < 0.5){
            image.setLayoutX(0);
            inLeft = true;
        }
        else{
            image.setLayoutX(maxRange);
            inLeft = false;
        }
        image.setLayoutY(maxHeight/4);
        point = p;


    }

	///////////////////// METHODS /////////////////////////
    public ImageView getImage() { return image; }

    public int getPoint() { return point; }

    public double getPosX() { return image.getLayoutX(); }
    public void setPosX(double x){ image.setLayoutX(x); }

    public double getPosY() { return image.getLayoutY(); }
    public void setPosY(double y){ image.setLayoutY(y); }


    public boolean isInLeft() { return inLeft; }

}
