package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Entity {

    public GamePanel gp;
    public BufferedImage image, image1, image2, particle;
    public BufferedImage up1,up2,up3,up4,up5,up6,up7,up8,down1,down2,down3,down4,down5,down6,down7,down8,left1,left2,
            left3,left4,left5,right1,right2,right3,right4,right5,avatar1, avatar2;
    public BufferedImage
            attackup1,attackup2,attackup3,attackup4,attackup5,attackup6,
            attackdown1,attackdown2,attackdown3,attackdown4, attackdown5,attackdown6,
            attackleft1,attackleft2,attackleft3,attackleft4,attackleft5,attackleft6,
            attackright1,attackright2,attackright3,attackright4,attackright5,attackright6;
    public BufferedImage[] meleeAttackdown= new BufferedImage[50];
    public BufferedImage[] meleeAttackup= new BufferedImage[50];
    public BufferedImage[] meleeAttackleft= new BufferedImage[50];
    public BufferedImage[] meleeAttackright= new BufferedImage[50];
    public BufferedImage[] projectileAttackRight= new BufferedImage[50];
    public BufferedImage[] projectileAttackUp= new BufferedImage[50];
    public BufferedImage[] projectileAttackDown= new BufferedImage[50];
    public BufferedImage[] projectileAttackleft= new BufferedImage[50];
    public BufferedImage[] projectileAttackHit= new BufferedImage[50];
    public BufferedImage[] picByHealth= new BufferedImage[50];
    public BufferedImage[] video= new BufferedImage[500];
    public BufferedImage dead;

    public Rectangle solidArea= new Rectangle(0,0,48,48);
    public Rectangle attackArea= new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision=false;
    public boolean hit=false;
    //FOR METHODS
    public int pathPoints;
    public int pathPointsLimit;
    public int goalCol[];
    public int goalRow[];
    String dialogues[]= new String[20];
    public int getLeftX(){
        return worldX+solidArea.x;
    }
    public int getRightX(){
        return worldX+solidArea.x+solidArea.width;
    }
    public int getTopY(){
        return worldY+solidArea.y;
    }
    public int getBottomY(){
        return  worldY+solidArea.y+solidArea.height;
    }
    public int getCol(){
        return (worldX+solidArea.x)/gp.tileSize;
    }
    public int getRow(){
        return (worldY+solidArea.y)/gp.tileSize;
    }


    //STATE
    public int worldX, worldY;
    public String direction="down";
    public int spriteNum=1;
    public int xd,yd;
    public int spriteNum2=1;
    public int spriteNum3=1;
    public boolean invincible = false;
    public boolean collisionOn=false;
    public boolean attacking =false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn =false;
    public boolean contactPlayer=false;
    public boolean destructable= false;
    public boolean onPath=false;
    public boolean knockBack=false;
    public boolean stackable=false;
    public String knockBackDirection="";

    //COUNTER
    public int spriteCounter=0;
    public int spriteCounter2=0;
    public int spriteCounter3=0;
    public int spriteCounter4=0;
    public int actionLockCounter =0;
    public int invincibleCounter=0;
    public int dialogueIndex = 0;
    public int dyingCounter = 0;
    public int hpBarCounter=0;
    public int shotAvailableCounter=0;
    public int projectileAvailableCounter=0;
    public int knockBackCounter=0;
    public int changeableSoundEffects[];


    //CHARACTER ATTRIBUTES
    public int height;
    public int width;
    public int defaultSpeed;
    public String name;
    public int speed;
    public int ammo;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defence;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int amount=1;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;
    public Projectile projectile2;
    public Entity attacker;

    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize=20;
    public int price;
    public int recoverValue;
    public int attackValue;
    public int defenceValue;
    public String description="";
    public int type;
    public int particlesize;
    public int useCost;
    public final int type_player=0;
    public final int type_npc=1;
    public final int type_monster=2;
    public final int type_melee=3;
    public final int type_shield=4;
    public final int type_consumable=5;
    public final int type_structure= 6;
    public final int type_projectile=7;
    public final int type_visual=8;
    public final int type_PickUpOnly=9;
    public final int type_breakable=10;
    public final int type_interactive=11;





    public Entity (GamePanel gp){

        this.gp=gp;
    }
    public void interact(){}
    public boolean use(Entity entity){
        return false;
    }
    public void setAction(){}
    public void damageReaction(){}
    public void checkDrop(){}
    public void dropItem(Entity droppedItem, Entity[][] target){
        for (int i=0; i< target[1].length;i++){
            if (target[gp.currentMap][i]==null){
                target[gp.currentMap][i]=droppedItem;
                target[gp.currentMap][i].worldX=worldX;
                target[gp.currentMap][i].worldY=worldY;
                break;
            }

        }
    }
    public Color getParticleColor(){
        Color color= null;
        return color;
    }
    public int getParticleSize(){
        int size =0;
        return size;
    }
    public int getParticleSpeed(){
        int speed=0;
        return speed;
    }
    public int getParticleMaxlife(){
        int maxLife=0;
        return maxLife;
    }
    public void generateParticle(Entity generator){
        Color color= generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife =generator.getParticleMaxlife();
        Particle p1 = new Particle(gp,generator,color,size,speed,maxLife,-2,-3);
        Particle p2= new Particle(gp,generator,color,size,speed,maxLife,2,-3);
        Particle p3= new Particle(gp,generator,color,size,speed,maxLife,-2,0);
        Particle p4= new Particle(gp,generator,color,size,speed,maxLife,2,0);
        Particle p5= new Particle(gp,generator,color,size,speed,maxLife,0,-3);
        Particle p6= new Particle(gp,generator,color,size,speed,maxLife,-2,0);
        Particle p7= new Particle(gp,generator,color,size,speed,maxLife,0,0);
        Particle p8= new Particle(gp,generator,color,size,speed,maxLife,2,0);

        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
        gp.particleList.add(p5);
        gp.particleList.add(p6);
        gp.particleList.add(p7);
        gp.particleList.add(p8);

    }
    public void speak(){

        if (dialogues[dialogueIndex]==null){
            dialogueIndex=0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        gp.ui.avatar = avatar1;
        dialogueIndex++;

        switch (gp.player.direction){
            case "up":
                direction="down";
                break;
            case "down":
                direction="up";
                break;
            case "left":
                direction="right";
                break;
            case "right":
                direction="left";
                break;

        }
    }
    public void checkCollision(){


        collisionOn=false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this,gp.monster);
        contactPlayer= gp.cChecker.checkPlayer(this);
        if (this.type==type_monster&& contactPlayer==true){
            damagePlayer(attack);
        }

    }
    public void update(){

            setAction();
            checkCollision();

            //Collisioncheck
            if (collisionOn == false) {
                switch (knockBackDirection) {
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
            }}
    public void damagePlayer(int attack){

        if (gp.player.invincible==false&&gp.player.attacking==false){
            changeableSoundEffects = new int[]{11, 12, 13};
            gp.playSE(soundEffectChanger(3, changeableSoundEffects));
            int damage=attack-gp.player.defence;
            if (damage<=0){damage=1;}
            gp.player.life-=damage;
            gp.player.invincible=true;}

    }
    public void setKnockBack(Entity target, Entity attacker){
        this.attacker=attacker;
        target.knockBackDirection=attacker.direction;
        target.speed+=10;
        target.knockBack=true;

    }
    public int soundEffectChanger(int range, int[]seInput){
        int seoutput=11;
        Random random = new Random();
        int idx = random.nextInt(range);
        switch (idx){
            case 0: seoutput=seInput[0];break;
            case 1: seoutput=seInput[1];break;
            case 2: seoutput=seInput[2];break;
            case 3: seoutput=seInput[3];break;
            case 4: seoutput=seInput[4];break;
            case 5: seoutput=seInput[5];break;
            case 6: seoutput=seInput[6];break;
            case 7: seoutput=seInput[7];break;
            case 8: seoutput=seInput[8];break;
            case 9: seoutput=seInput[9];break;
            case 10: seoutput=seInput[10];break;
            case 11: seoutput=seInput[11];break;
        }
        return seoutput;
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

                    break;
                case "down":
                    if (spriteNum==1){image=down1;}
                    if (spriteNum==2){image=down2;}

                    break;
                case "left":
                    if (spriteNum==1){image=left1;}
                    if (spriteNum==2){image=left2;}
                    break;
                case "right":
                    if (spriteNum==1){image=right1;}
                    if (spriteNum==2){image=right2;}
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
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        int i=10;

        if (dyingCounter<=i){changeAlpha(g2,0f);}
        if (dyingCounter>i&&dyingCounter<=i*2){changeAlpha(g2,1f);}
        if (dyingCounter>i*2&&dyingCounter<=i*3){changeAlpha(g2,0f);}
        if (dyingCounter>i*3&&dyingCounter<=i*4){changeAlpha(g2,1f);}
        if (dyingCounter>i*4&&dyingCounter<=i*5){changeAlpha(g2,0f);}
        if (dyingCounter>i*5&&dyingCounter<=i*6){changeAlpha(g2,1f);}
        if (dyingCounter>i*6&&dyingCounter<=i*7){changeAlpha(g2,0f);}
        if (dyingCounter>i*7&&dyingCounter<=i*8){changeAlpha(g2,1f);}
        if (dyingCounter>i*8){
            alive=false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
    }

    public BufferedImage setup(String imagePath,int width,int height){
        UtilityTool uTool= new UtilityTool();
        BufferedImage image= null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
            image= uTool.scaleImage(image, width, height);

        }catch (IOException e){
            e.printStackTrace();
        }
        return  image;
    }
    public BufferedImage[] setup1(BufferedImage[] images,String imagePath,int width,int height){
        UtilityTool uTool= new UtilityTool();
        BufferedImage[] image= new BufferedImage[images.length];
        for (int i=0; i<images.length; i++){
            try {
                image[i] = ImageIO.read(getClass().getResourceAsStream(imagePath+(i+1)+".png"));
                image[i]= uTool.scaleImage(image[i], width, height);

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return  image;
    }

    public void animationSpriteChanger(){}

    public Image animationImageSetter(Image[] images){
        int imageIdx = spriteNum3 - 1;
        if (imageIdx >= 0 && imageIdx < images.length) {
            return images[imageIdx];
        } else {
            return null; // Return a default image or handle this case as needed
        }
    }
    public void searchPath(int goalCol, int goalRow){

        int startCol =(worldX+solidArea.x)/gp.tileSize;
        int startRow =(worldY+solidArea.y)/gp.tileSize;
        gp.pFinder.setNodes(startCol,startRow,goalCol,goalRow,this);

        if (gp.pFinder.search()==true){
            //Next worldx & next worldY
            int nextX= gp.pFinder.pathList.get(0).col*gp.tileSize;
            int nextY= gp.pFinder.pathList.get(0).row*gp.tileSize;
            //ENTİTY'S SOLİD AREA POSİTİON
            int enLeftX= worldX+solidArea.x;
            int enrightX= worldX+solidArea.x+solidArea.width;
            int enTopY=worldY+solidArea.y;
            int enBottomY = worldY+solidArea.y+solidArea.height;

            if (enTopY>nextY && enLeftX >=nextX && enrightX<nextX+gp.tileSize ){
                direction="up";
            }
            else if (enTopY<nextY && enLeftX >=nextX && enrightX<nextX+gp.tileSize ){
                direction="down";
            } else if (enTopY>= nextY && enBottomY <nextY +gp.tileSize) {
                //left or right
                if (enLeftX>nextX){direction="left"; }
                if (enLeftX<nextX){direction="right";}
            } else if (enTopY>nextY && enLeftX>nextX) {
                //up or left
                direction="up";
                checkCollision();
                if (collisionOn==true){direction="left";}
            } else if (enTopY>nextY && enLeftX<nextX) {
                //up or right
                direction="up";
                checkCollision();
                if (collisionOn==true){direction="right";}
            } else if (enTopY < nextY && enLeftX>nextX) {
                //down or left
                direction="down";
                checkCollision();
                if (collisionOn==true){direction="left";}
            }
            else if (enTopY < nextY && enLeftX<nextX) {
                //down or right
                direction="down";
                checkCollision();
                if (collisionOn==true){direction="right";}
            }
            //hedefe ulaşınca aramayı durdur/yeni hedef belirle/ döngü kur vsvs
            int nextCol= gp.pFinder.pathList.get(0).col;
            int nextRow= gp.pFinder.pathList.get(0).row;
            if(nextCol == goalCol && nextRow==goalRow){
                pathPoints++;
            }
        }
    }
    public int getDetected(Entity user,Entity target[][],String targetName){
        int index=999;

        //cevre obje kontrolü
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction){
            case "up": nextWorldY = user.getTopY()-1; break;
            case "down": nextWorldY= user.getBottomY()+1; break;
            case "left": nextWorldX= user.getLeftX()-1; break;
            case "right": nextWorldX = user.getRightX()+1;break;
        }
        int col= nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for (int i =0; i<target[1].length; i++){
            if (target[gp.currentMap][i]!= null){
                if(target[gp.currentMap][i].getCol()==col&& target[gp.currentMap][i].getRow()==row&&
                        target[gp.currentMap][i].name.equals(targetName)){
                    index =i;
                    break;
                }
            }
        }
        return index;
    }

}
