package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Particle extends Entity {
    Entity generator;
    Color color;
    int size;
    int xd;
    int yd;


    public Particle(GamePanel gp , Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(gp);


        this.generator=generator;
        this.color=color;
        this.xd=xd;
        this.yd=yd;
        this.size=size;
        this.speed=speed;
        this.maxLife=maxLife;

        life=maxLife;
        worldY=generator.worldY+(generator.height/2)-(size/2);
        worldX=generator.worldX+(generator.width/2)-(size/2);

    }
    public void update(){

        life--;
        if (life<=maxLife/3){
            yd++;
        }
        worldX+=xd*speed;
        worldY+=yd*speed;
        if (life<=0){
            alive=false;
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image=null;

        int screenX= worldX-gp.player.worldX+gp.player.screenX;
        int screenY= worldY-gp.player.worldY+gp.player.screenY;
        image= generator.particle;

        g2.setColor(color);
        g2.drawImage(image,screenX,screenY,generator.particlesize,generator.particlesize,null);
        //g2.fillRoundRect(screenX,screenY,size,size,3,3);

    }



}
