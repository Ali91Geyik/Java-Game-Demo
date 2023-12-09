package object.dekor;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Bitki extends Entity {
    GamePanel gp;
    int coinCounter;
    public OBJ_Bitki(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="orta Ayna";
        description="["+name+"]"+"\n kardio OK.";
        direction="hit";
        collision=true;
        type = type_structure;
        coinCounter=0;
        width=48;
        height=64;
        getImage();
    }
    public void getImage(){
        down1 = setup("/objects/dekor/bitki",width,height);

    }
    public void draw(Graphics2D g2){
        BufferedImage image;
        int tempWidth=width;
        int tempHeight=height;

        int screenX= worldX-gp.player.worldX+gp.player.screenX;
        int screenY= worldY-gp.player.worldY+gp.player.screenY;

        if( worldX+gp.tileSize>gp.player.worldX-gp.player.screenX&&
                worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&
                worldY+gp.tileSize>gp.player.worldY-gp.player.screenY&&
                worldY-gp.tileSize<gp.player.worldY+gp.player.screenY){
            image=down1;

            g2.drawImage(image,screenX,screenY,tempWidth, tempHeight, null);
        }}
}

