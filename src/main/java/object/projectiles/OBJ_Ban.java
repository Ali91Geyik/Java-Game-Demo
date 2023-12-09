package object.projectiles;

import entity.Projectile;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Ban extends Projectile {

    GamePanel gp;
    int spriteCounter;
    public OBJ_Ban(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="Ban";
        speed=4;
        maxLife=80;
        life=maxLife;
        attack=2;
        useCost=1;
        alive=false;
        spriteCounter=0;
        type = type_projectile;
        width=48;
        height=48;
        getImage();
    }
    public void getImage(){
        up1= setup("/projectiles/ban/ban",48,48);
        up2= setup("/projectiles/ban/ban",48,48);
        down1= setup("/projectiles/ban/ban",48,48);
        down2= setup("/projectiles/ban/ban",48,48);
        left1= setup("/projectiles/ban/ban",48,48);
        left2= setup("/projectiles/ban/ban",48,48);
        right1= setup("/projectiles/ban/ban",48,48);
        right2= setup("/projectiles/ban/ban",48,48);

    }
    public void update(){
        if (user==gp.player){
            int monsterIdx=gp.cChecker.checkEntity(this,gp.monster);
            if (monsterIdx!=999){
                gp.player.damageMonster(monsterIdx,this, attack);
                gp.playSE(28);
                direction="hit";
                if (spriteNum3==4){
                    alive=false;}
            }
        }
        if (user!=gp.player){
            boolean contactPlayer =gp.cChecker.checkPlayer(this);
            if (gp.player.invincible==false&& contactPlayer==true){
                damagePlayer(attack);

                alive=false;}
            }



        switch (direction){
            case "up": worldY-=speed; break;
            case "down": worldY+=speed; break;
            case "right": worldX+=speed;break;
            case "left":worldX-=speed; break;
        }
        spriteCounter3++;
        life--;
        if (life<=0){
            alive=false;}
        animationSpriteChanger();

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

            switch (direction) {
                case "up":
                    if (spriteNum3 == 1) {
                        image = up1;
                    }
                    if (spriteNum3 == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum3 == 1) {
                        image = down1;
                    }
                    if (spriteNum3 == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum3 == 1) {
                        image = left1;
                    }
                    if (spriteNum3 == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum3 == 1) {
                        image = right1;
                    }
                    if (spriteNum3 == 2) {
                        image = right2;
                    }
                    break;
            }
            g2.drawImage(image,screenX,screenY,tempWidth, tempHeight, null);
        }}

    public void animationSpriteChanger(){
        if (spriteCounter3 > 3) {
            switch (spriteNum3) {
                case 1:
                    spriteNum3 = 2;
                    spriteCounter3 = 0;
                    break;
                case 2:
                    spriteNum3 = 1;
                    spriteCounter3 = 0;
                    break;
            }
        }
    }





}


