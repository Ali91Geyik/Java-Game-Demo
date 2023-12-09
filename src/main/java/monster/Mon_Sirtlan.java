package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Bardak;
import object.OBJ_CanavarElma;
import object.projectiles.OBJ_Ban;
import object.valuables.OBJ_Coin;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Mon_Sirtlan  extends Entity {
    public Mon_Sirtlan(GamePanel gp) {
        super(gp);
        name="GFS Sirtlan";
        defaultSpeed=2;
        speed=defaultSpeed;
        maxLife=30;
        attack=1;
        defence=0;
        exp=10;
        life=maxLife;
        type=type_monster;
        solidArea.x=16;
        solidArea.y=3;
        solidArea.width=50;
        solidArea.height=40;
        width=64;
        height=48;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        collision=true;
        projectile= new OBJ_Ban(gp);
        particle=setup("/monster/blood", 16,16);
        particlesize=32;


        getImage();

    }
    public void update(){
        int xDistance =Math.abs(worldX-gp.player.worldX);
        int yDistance =Math.abs(worldY-gp.player.worldY);
        int tileDistance=(xDistance+yDistance)/gp.tileSize;

        if (knockBack==true){
            checkCollision();
            if (collisionOn==true){
                switch (knockBackDirection){
                    case "up":
                        worldX+=speed;
                        if (tileDistance>1){
                        knockBackDirection="right";}

                        break;
                    case "down":
                        worldX-=speed;
                        if (tileDistance>1){
                        knockBackDirection="left";}

                        break;
                    case "left":
                        worldY -= speed;
                        if (tileDistance>1){
                        knockBackDirection="up";}

                        break;
                    case "right":
                        worldY += speed;
                        if (tileDistance>1){
                        knockBackDirection="down";}

                        break;}

            } else if (collisionOn==false) {
                switch (knockBackDirection){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        yd=-4;
                        worldY +=yd;
                        worldX -= speed;
                        break;
                    case "right":
                        yd=-4;
                        worldY +=yd;
                        worldX += speed;
                        break;
                }
            }
            knockBackCounter++;
            if (knockBackCounter==15){
                speed-=5;

            }if (knockBackCounter==25){
                speed-=4;
            }
            if (knockBackCounter>10){
                yd++;
            }
            if (knockBackCounter==40){
                knockBackCounter=0;
                knockBack=false;
                speed=defaultSpeed;
                yd=-2;
            }
        }
        else {


        setAction();

        collisionOn=false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this,gp.monster);
        contactPlayer= gp.cChecker.checkPlayer(this);
        if (this.type==type_monster&& contactPlayer==true){
            damagePlayer(attack);
        }


        //Collisioncheck
        if (collisionOn==false){
            switch (direction){
                case "up":
                    worldY-=speed;
                    break;
                case "down":
                    worldY+=speed;
                    break;
                case "left":
                    worldX-=speed;
                    break;
                case "right":
                    worldX+=speed;
                    break;
            }

        }

        animationSpriteChanger2();
        if (invincible==true){
            invincibleCounter++;
            if (invincibleCounter>=40){
                invincible=false;
                invincibleCounter=0;
            }
        }
        if (shotAvailableCounter<60){
            shotAvailableCounter++;
        }


        if (onPath==false&&tileDistance<5){
            int i= new Random().nextInt(100)+1;
            if (i>50){onPath=true;}
        }
        if (onPath==true&&tileDistance>15){
            onPath=false;
            speed=2;
        }

    }}
    public void getImage(){
        projectileAttackleft= setup1(new BufferedImage[14],"/monster/sirtlan/sirlanleft",64,48);
        projectileAttackRight= setup1(new BufferedImage[14],"/monster/sirtlan/sirlanright",64,48);


        dead= setup("/monster/sirtlan/sirlandead",80,48);

    }
    public void draw(Graphics2D g2){
        BufferedImage image=null;


        int screenX= worldX-gp.player.worldX+gp.player.screenX;
        int screenY= worldY-gp.player.worldY+gp.player.screenY;

        if( worldX+gp.tileSize>gp.player.worldX-gp.player.screenX&&
                worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&
                worldY+gp.tileSize>gp.player.worldY-gp.player.screenY&&
                worldY-gp.tileSize<gp.player.worldY+gp.player.screenY){

            switch (direction){
                case "up":
                    if ( spriteNum >= 0 && spriteNum <= projectileAttackleft.length) {
                        image = projectileAttackleft[spriteNum]; // -1 because arrays are 0-indexed
                    }

                    break;
                case "down":
                    if ( spriteNum >= 0 && spriteNum <= projectileAttackRight.length) {
                        image = projectileAttackRight[spriteNum]; // -1 because arrays are 0-indexed
                    }

                    break;
                case "left":
                    if ( spriteNum >= 0 && spriteNum <= projectileAttackleft.length) {
                        image = projectileAttackleft[spriteNum]; // -1 because arrays are 0-indexed
                    }
                    break;
                case "right":
                    if ( spriteNum >= 0 && spriteNum <= projectileAttackRight.length) {
                        image = projectileAttackRight[spriteNum]; // -1 because arrays are 0-indexed
                    }
                    break;
            }
            if (dying==true){
                image=dead;
            }
            //Monster Hp Bar
            if (type==2 && hpBarOn==true) {
                double oneScale=(double)life/maxLife;
                double hpBarValue= gp.tileSize*oneScale;

                g2.setColor(new Color(255, 255, 0));
                g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
                g2.setColor(new Color(0, 0, 0));
                g2.fillRect(screenX, screenY-15, gp.tileSize , 10);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY-15, (int)hpBarValue , 10);
                hpBarCounter++;
                if (hpBarCounter>600){
                    hpBarOn=false;
                    hpBarCounter=0;
                }
            }

            if (invincible==true){
                hpBarOn=true;
                hpBarCounter=0;
                changeAlpha(g2,0.4f);
            }
            if (dying==true){

                dyingAnimation(g2);
            }
/*
            counter++;
            if (counter%2==0){
                screenX-=4;
                screenY-=4;            //kükreme için yedek mekanik
            }else {screenX+=4;
                screenY+=4;}   */

            g2.drawImage(image,screenX,screenY,width, height, null);
            changeAlpha(g2,1f);

        }}
    public void setAction(){

        if (onPath==true){
            speed=3;
            int goalCol=(gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
            int goalRow=(gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol,goalRow);

        }
        else {
            actionLockCounter++;
            if (actionLockCounter==120){
                Random random = new Random();
                int i = random.nextInt(100)+1;
                if(i<=25){direction="up";}
                if (i>25&&i<=50){direction="down";}
                if (i>50&&i<=75){direction="left";}
                if(i>75&&i<=100){direction="right";}
                actionLockCounter=0;
            }}
    }
    public void damageReaction(){
        onPath=true;
    }
    public void checkDrop(){
        int i = new Random().nextInt(100)+1;

        if (i<50&&i>=25){dropItem(new OBJ_Bardak(gp), gp.obj);}
        if (i>=50&&i<75){dropItem(new OBJ_Coin(gp),gp.hareketli);}
        if (i>=75&&i<=100){dropItem(new OBJ_CanavarElma(gp),gp.obj);}

    }


    public Color getParticleColor(){
        Color color= new Color(65,50,30);
        return color;
    }
    public int getParticleSize(){
        int size =10;
        return size;
    }
    public int getParticleSpeed(){
        int speed=2;
        return speed;
    }
    public int getParticleMaxlife(){
        int maxLife=20;
        return maxLife;
    }
    public void animationSpriteChanger2(){
        spriteCounter++;
        if (spriteCounter>2){
            spriteNum = (spriteNum + 1) % 14;
            spriteCounter = 0;}
    }

}

