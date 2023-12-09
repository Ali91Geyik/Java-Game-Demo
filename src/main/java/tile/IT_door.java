package tile;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class IT_door extends Entity {
    GamePanel gp;
    int coinCounter;
    public IT_door(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="kapi";
        description="["+name+"]"+"\n kır geç OK";
        direction="down";
        getImage();
        life=20;
        collision=true;
        type = type_breakable;
        coinCounter=0;
        width=96;
        height=96;
        particlesize=24;
        solidArea.y=8;
        solidArea.height=88;
        solidArea.width=96;
        destructable=true;
        invincible=false;
        particle= setup("/interactive_objects/particle_door", particlesize,particlesize);
    }
    public void getImage(){
        picByHealth = setup1(new BufferedImage[3],"/interactive_objects/tahtakapi",96,96 );

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
        if (invincible==true){animationSpriteChanger();}

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

            if (direction.equals("down")){
                if (life>=15){image=picByHealth[0];}
                else if (life>=7){image=picByHealth[1];}
                else if (life>=0){image=picByHealth[2];}

            }

            else
            if (direction.equals("hit")) {
                if (life>=15){
                image = picByHealth[0];
                switch (spriteNum){
                    case 1: screenY+=2; screenX+=2; break;
                    case 2: screenY-=2; screenX+=2; break;
                    case 3: screenY+=2; screenX-=2; break;
                    case 4: screenY-=2; screenX-=2; break;
                }
                }
                else if (life>=7){
                    image = picByHealth[1];
                    switch (spriteNum){
                        case 1: screenY+=2; screenX+=2; break;
                        case 2: screenY-=2; screenX+=2; break;
                        case 3: screenY+=2; screenX-=2; break;
                        case 4: screenY-=2; screenX-=2; break;
                    }
                }
                else if (life>=0){
                    image = picByHealth[2];
                    switch (spriteNum){
                        case 1: screenY+=2; screenX+=2; break;
                        case 2: screenY-=2; screenX+=2; break;
                        case 3: screenY+=2; screenX-=2; break;
                        case 4: screenY-=2; screenX-=2; break;
                    }
                }

            }


            g2.drawImage(image,screenX,screenY,tempWidth, tempHeight, null);
        }}
    public void animationSpriteChanger2(){
        coinCounter++;
        if (coinCounter>1){
            spriteNum = (spriteNum + 1) % 5;
            coinCounter = 0;}
    }
    public void animationSpriteChanger(){
        spriteCounter++; // Increment the spriteCounter
        direction="hit";
        if (spriteCounter > 20) {
            spriteCounter = 0; // Reset spriteCounter
            direction = "down";
            invincible = false;
        }
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





