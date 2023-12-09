package main;

import entity.Entity;

public class Video extends Entity {
    GamePanel gp;
    public Video(GamePanel gp) {
        super(gp);
        this.gp=gp;

        getImage();
    }
    public void getImage(){}

}
