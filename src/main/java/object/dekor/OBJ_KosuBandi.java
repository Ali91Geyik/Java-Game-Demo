package object.dekor;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_KosuBandi extends Entity {
    GamePanel gp;
    int coinCounter;
    public OBJ_KosuBandi(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="Koşu Bandı";
        description="["+name+"]"+"\n kardio OK.";
        direction="hit";
        getImage();
        collision=true;
        type = type_structure;
        coinCounter=0;
        width=64;
        height=120;
    }
    public void getImage(){
        projectileAttackHit = setup1(new BufferedImage[3],"/objects/dekor/kosubandı",48,80 );

    }
    @Override
    public void update(){
        setAction();

        collisionOn=false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this,gp.hareketli);
        gp.cChecker.checkEntity(this,gp.monster);
        contactPlayer= gp.cChecker.checkPlayer(this);

        animationSpriteChanger2();

    }
    public void draw(Graphics2D g2){
        BufferedImage image=null;
        int tempWidth=width;
        int tempHeight=height;

        int screenX= worldX-gp.player.worldX+gp.player.screenX;
        int screenY= worldY-gp.player.worldY+gp.player.screenY;

        if( worldX+gp.tileSize>gp.player.worldX-gp.player.screenX&&
                worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&
                worldY+gp.tileSize>gp.player.worldY-gp.player.screenY&&
                worldY-gp.tileSize<gp.player.worldY+gp.player.screenY){


            if (direction.equals("hit") && spriteNum >= 1 && spriteNum <= projectileAttackHit.length) {
                image = projectileAttackHit[spriteNum - 1]; // -1 because arrays are 0-indexed
            }


            g2.drawImage(image,screenX,screenY,tempWidth, tempHeight, null);
        }}
    public void animationSpriteChanger2(){
        coinCounter++;
        if (coinCounter>5){
            spriteNum = (spriteNum + 1) % projectileAttackHit.length+1;
            coinCounter = 0;}
    }
}
