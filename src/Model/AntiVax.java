package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class AntiVax {

    public final static double maxRange = 600.0;
    public final static double maxHeight = 540.0;

    private URL file;
    private ImageView image;
    private int point;
    private boolean inLeft;

    public AntiVax(int p){
        file =  getClass().getResource("../Images/antivax.png");
        image = new ImageView(new Image(file.toExternalForm()));
        double randomX =  Math.random();
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

    public ImageView getImage() {
        return image;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public double getPosX() {
        return image.getLayoutX();
    }

    public double getPosY() {
        return image.getLayoutY();
    }

    public void setPosX(double x){
        image.setLayoutX(x);
    }

    public void setPosY(double y){
        image.setLayoutY(y);
    }


    public boolean isInLeft() {
        return inLeft;
    }

    public void setInLeft(boolean inLeft) {
        this.inLeft = inLeft;
    }
}
