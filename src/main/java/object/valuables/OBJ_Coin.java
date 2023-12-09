package object.valuables;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Coin extends Entity {
    GamePanel gp;
    int coinCounter;
    public OBJ_Coin(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="Coin";
        description="["+name+"]"+"\n 5 mangır eder OK.";
        direction="hit";
        getImage();
        amount=5;
        collision=true;
        type = type_PickUpOnly;
        coinCounter=0;
        width=48;
        height=48;
        down1=setup("/objects/valuables/coin1",gp.tileSize,gp.tileSize);
    }
    public void getImage(){
        projectileAttackHit = setup1(new BufferedImage[8],"/objects/valuables/coin",48,48 );

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
    public boolean use(Entity entity){
        gp.player.coin+=amount;
        gp.ui.addMessage("Mangır+" +amount);
        gp.playSE(18);
        return true;
    }}
