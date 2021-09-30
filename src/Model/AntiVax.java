package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class AntiVax {

    private URL file;
    private ImageView image;
    private int point;
    private double posX;
    private double posY;
    private boolean inLeft;

    public AntiVax(int p){
        file =  getClass().getResource("../Images/antivax.png");
        image = new ImageView(new Image(file.toExternalForm()));

        point = p;
        posX = 0;
        posY = 0;
        inLeft = true;

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
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosX(double x){
        posX = x;
        image.setLayoutX(x);
    }

    public void setPosY(double y){
        posY = y;
        image.setLayoutY(y);
    }


    public boolean isInLeft() {
        return inLeft;
    }

    public void setInLeft(boolean inLeft) {
        this.inLeft = inLeft;
    }
}
