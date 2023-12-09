package entity;

import main.GamePanel;
import object.projectiles.OBJ_Slime;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity{
    public Entity user;
    GamePanel gp;

    public Projectile(GamePanel gp) {
        super(gp);
        this.gp=gp;
    }
    public void set(int worldX, int worldY, String direction, boolean alive,Entity user){
    this.worldX=worldX;
    this.worldY=worldY;
    this.direction=direction;
    this.alive= alive;
    this.user=user;
    this.life=this.maxLife;
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
                direction="hit";
                if (spriteNum3==4){
                    alive=false;}
            }

        }
        switch (direction){
            case "up": worldY-=speed; break;
            case "down": worldY+=speed; break;
            case "right": worldX+=speed;break;
            case "left":worldX-=speed; break;

        }
    }
    public boolean haveResourceMethod(Entity user){
        boolean haveResource=false;
        return haveResource;
    }
    public void subtractResource(Entity user){
    }
    public void animationSpriteChanger2(){}
}
