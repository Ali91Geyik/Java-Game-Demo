package monster;

import entity.Entity;
import main.GamePanel;
import main.Sound;
import object.OBJ_Bardak;
import object.OBJ_CanavarElma;
import object.projectiles.OBJ_Ban;
import object.valuables.OBJ_Coin;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MON_Dislike extends Entity {
    GamePanel gp;
    public MON_Dislike(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="dislike";
        xd=0;
        yd=0;
        defaultSpeed=1;
        speed=defaultSpeed;
        maxLife=5;
        attack=1;
        defence=0;
        exp=5;
        life=maxLife;
        type=type_monster;
        solidArea.x=3;
        solidArea.y=3;
        solidArea.width=40;
        solidArea.height=40;
        width=48;
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
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;
        if (knockBack==true){
            checkCollision();
            if (collisionOn==true){
                switch (knockBackDirection){
                    case "up":
                        worldX+=speed;
                        knockBackDirection="right";
                        System.out.println(knockBackDirection);
                        break;
                    case "down":
                        worldX-=speed;
                        knockBackDirection="left";
                        System.out.println(knockBackDirection);
                        break;
                    case "left":
                        worldY -= speed;
                        knockBackDirection="up";
                        System.out.println(knockBackDirection);
                        break;
                    case "right":
                        worldY += speed;
                        knockBackDirection="down";
                        System.out.println(knockBackDirection);
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

            collisionOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this, false);
            gp.cChecker.checkEntity(this, gp.npc);
            gp.cChecker.checkEntity(this, gp.monster);
            contactPlayer = gp.cChecker.checkPlayer(this);
            if (this.type == type_monster && contactPlayer == true) {
                damagePlayer(attack);
            }

            //Collisioncheck
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
                spriteCounter++;
                if (spriteCounter > 12) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 3;
                    } else if (spriteNum == 3) {
                        spriteNum = 4;
                    } else if (spriteNum == 4) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
            if (invincible == true) {
                invincibleCounter++;
                if (invincibleCounter >= 40) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
            if (shotAvailableCounter < 60) {
                shotAvailableCounter++;
            }

            if (onPath == false && tileDistance < 5) {
                int i = new Random().nextInt(100) + 1;
                if (i > 50) {
                    onPath = true;
                }
            }
            if (onPath == true && tileDistance > 15) {
                onPath = false;
            }
        }
    }
    public void getImage(){
        up1=setup("/monster/dislikeLeft1",48,48);
        up2=setup("/monster/dislikeLeft2",48,48);
        up3=setup("/monster/dislikeLeft3",48,48);
        up4=setup("/monster/dislikeLeft4",48,48);

        down1=setup("/monster/dislikeRight1",48,48);
        down2=setup("/monster/dislikeRight2",48,48);
        down3=setup("/monster/dislikeRight3",48,48);
        down4=setup("/monster/dislikeRight4",48,48);

        left1=setup("/monster/dislikeLeft1",48,48);
        left2=setup("/monster/dislikeLeft2",48,48);
        left3=setup("/monster/dislikeLeft3",48,48);
        left4=setup("/monster/dislikeLeft4",48,48);

        right1=setup("/monster/dislikeRight1",48,48);
        right2=setup("/monster/dislikeRight2",48,48);
        right3=setup("/monster/dislikeRight3",48,48);
        right4=setup("/monster/dislikeRight4",48,48);


        dead= setup("/monster/dislike_dead",48,48);

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
                    if (spriteNum==1){image=up1;}
                    if (spriteNum==2){image=up2;}
                    if (spriteNum==3){image=up3;}
                    if (spriteNum==4){image=up4;}

                    break;
                case "down":
                    if (spriteNum==1){image=down1;}
                    if (spriteNum==2){image=down2;}
                    if (spriteNum==3){image=down3;}
                    if (spriteNum==4){image=down4;}

                    break;
                case "left":
                    if (spriteNum==1){image=left1;}
                    if (spriteNum==2){image=left2;}
                    if (spriteNum==3){image=left3;}
                    if (spriteNum==4){image=left4;}
                    break;
                case "right":
                    if (spriteNum==1){image=right1;}
                    if (spriteNum==2){image=right2;}
                    if (spriteNum==3){image=right3;}
                    if (spriteNum==4){image=right4;}
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

        int goalCol=(gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
        int goalRow=(gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;

        searchPath(goalCol,goalRow);
            int i= new Random().nextInt(100)+1;
            if (i>99 && projectile.alive==false){
                projectile.set(worldX,worldY,direction,true,this);
                gp.projectileList.add(projectile);
            }

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

}
