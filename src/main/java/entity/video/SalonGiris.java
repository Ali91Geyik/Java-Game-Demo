package entity.video;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SalonGiris extends Entity {
    GamePanel gp;
    public SalonGiris(GamePanel gp) {
        super(gp);
        this.gp=gp;
        getImage();
        width=864;
        height=461;
    }

    public void getImage(){

        video=setup1(new BufferedImage[102],"/video/salonGiris/",720,480);

    }
    public void update(){
        setAction();
        animationSpriteChanger2();
    }
    public void draw(Graphics2D g2){
        BufferedImage image=null;




        if ( spriteNum >= 0 && spriteNum <= video.length) {
            image =video[spriteNum]; // -1 because arrays are 0-indexed
        }

        g2.drawImage(image,50,50,width, height, null);
        changeAlpha(g2,1f);

    }
    public void animationSpriteChanger2(){
        spriteCounter++;
        if (spriteCounter>4){
            spriteNum = (spriteNum + 1) % 102;
            spriteCounter = 0;}
    }
}
