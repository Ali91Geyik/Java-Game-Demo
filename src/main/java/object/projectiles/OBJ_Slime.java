package object.projectiles;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Slime extends Projectile {
    GamePanel gp;
    int spriteCounter;
    public OBJ_Slime(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="Tükürük";
        speed=4+dexterity;
        maxLife=80;
        life=maxLife;
        attack=2+dexterity;
        useCost=1;
        alive=false;
        getImage();
        spriteCounter=0;
        type = type_projectile;
        width=48;
        height=48;
    }
    public void getImage(){
        projectileAttackDown= setup1(new BufferedImage[4],"/projectiles/slime_down",48,48);
        projectileAttackUp= setup1(new BufferedImage[4],"/projectiles/slime_up",48,48 );
        projectileAttackRight = setup1(new BufferedImage[4], "/projectiles/slime_right",48,48);
        projectileAttackleft = setup1(new BufferedImage[4], "/projectiles/slime_left",48,48);
        projectileAttackHit = setup1(new BufferedImage[4],"/projectiles/slime_hit",96,96 );

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
                            image = projectileAttackUp[0];
                        }
                        if (spriteNum3 == 2) {
                            image = projectileAttackUp[1];
                        }
                        if (spriteNum3 == 3) {
                            image = projectileAttackUp[2];
                        }
                        if (spriteNum3 == 4) {
                            image = projectileAttackUp[3];
                        }
                        break;
                    case "down":
                        if (spriteNum3 == 1) {
                            image = projectileAttackDown[0];
                        }
                        if (spriteNum3 == 2) {
                            image = projectileAttackDown[1];
                        }
                        if (spriteNum3 == 3) {
                            image = projectileAttackDown[2];
                        }
                        if (spriteNum3 == 4) {
                            image = projectileAttackDown[3];
                        }
                        break;
                    case "left":
                        if (spriteNum3 == 1) {
                            image = projectileAttackleft[0];
                        }
                        if (spriteNum3 == 2) {
                            image = projectileAttackleft[1];
                        }
                        if (spriteNum3 == 3) {
                            image = projectileAttackleft[2];
                        }
                        if (spriteNum3 == 4) {
                            image = projectileAttackleft[3];
                        }
                        break;
                    case "right":
                        if (spriteNum3 == 1) {
                            image = projectileAttackRight[0];
                        }
                        if (spriteNum3 == 2) {
                            image = projectileAttackRight[1];
                        }
                        if (spriteNum3 == 3) {
                            image = projectileAttackRight[2];
                        }
                        if (spriteNum3 == 4) {
                            image = projectileAttackRight[3];
                        }
                        break;
                    case "hit":
                        tempWidth=128;
                        tempHeight=128;
                        screenX-=48;
                        screenY-=48;
                        if (spriteNum3 == 1) {
                            image = projectileAttackHit[0];
                        }
                        if (spriteNum3 == 2) {
                            image = projectileAttackHit[1];
                        }
                        if (spriteNum3 == 3) {
                            image = projectileAttackHit[2];
                        }
                        if (spriteNum3 == 4) {
                            image = projectileAttackHit[3];
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
                    spriteNum3 = 3;
                    spriteCounter3 = 0;
                    break;
                case 3:
                    spriteNum3 = 4;
                    spriteCounter3 = 0;
                    break;
                case 4:
                    spriteNum3 = 1;
                    spriteCounter3 = 0;
                    break;
            }
        }}
    public boolean haveResourceMethod(Entity user){
        boolean haveResource=false;
        if (user.mana>= useCost){
            haveResource=true;
        }

        return haveResource;
    }
    public void subtractResource(Entity user){
        user.mana-=useCost;
    }

    }


