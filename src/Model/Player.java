package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Player {
    Image imagePlayer;
    Image imageG;
    Image imageD;
    Image imageShoot;
    ImageView i;
    Double posX;
    Double posY;
    ArrayList<ImageView> shoots = new ArrayList<>();


    public Player(ImageView i){
        this.i = i;
        this.imagePlayer = i.getImage();
        this.posX = i.getLayoutX();
        this.imageG = i.getImage();
        this.posY = i.getLayoutY();
        this.imageD = new Image("file:/C:/Users/minic/OneDrive/Documents/M1/Advanced%20Programming%20of%20Interactive%20Systems/Projet/Virus-Game/out/production/Virus-Game/Images/playerD.png");
        this.imageShoot = new Image("file:/C:/Users/minic/OneDrive/Documents/M1/Advanced%20Programming%20of%20Interactive%20Systems/Projet/Virus-Game/out/production/Virus-Game/Images/playerShooting.png");
    }


    public void addShoot(ImageView i){
        this.shoots.add(i);
    }



    public Double getPosY() {
        return posY;
    }

    public Double getPosX() {
        return posX;
    }

    public Image getImagePlayer() {
        return imagePlayer;
    }

    public Image getImageD() {
        return imageD;
    }

    public Image getImageG() {
        return imageG;
    }

    public Image getImageShoot() {
        return imageShoot;
    }

    public void setImagePlayer(Image imagePlayer) {
        this.i.setImage(imagePlayer);
        this.imagePlayer = imagePlayer;
    }

    public void setPosX(Double posX) {
        this.i.setLayoutX(posX);
        this.posX = posX;
    }
}
