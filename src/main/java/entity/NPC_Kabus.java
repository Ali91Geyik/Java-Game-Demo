package entity;

import main.GamePanel;
import object.OBJ_Bardak;
import object.OBJ_CanavarElma;
import object.projectiles.OBJ_Dumbell10kg;
import object.weapons.OBJ_10lukPlaka;
import object.weapons.OBJ_20likPlaka;
import object.weapons.OBJ_zbar10kg;
import object.weapons.OBJ_zbar20kg;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;


public class NPC_Kabus extends Entity{

    public NPC_Kabus(GamePanel gp){
        super(gp);
        direction="down";
        speed=2;
        name="Kabus Boyka";
        width=48;
        height=48;
        type=type_npc;
        solidArea.x=5;
        solidArea.y=5;
        solidArea.height=30;
        solidArea.width=30;
        collision=true;
        goalCol= new int[]{30,20};
        goalRow=new int[]{30,30};
        pathPoints=0;
        pathPointsLimit=1;
        getNPCImage();
        setDialogue();
        setItems();
    }
    public void getNPCImage(){
        up1 =setup("/npc/kabus32_up1",48,48);
        up2 =setup("/npc/kabus32_up2",48,48);
        down1 =setup("/npc/kabus32_down1",48,48);
        down2 =setup("/npc/kabus32_down2",48,48);
        left1 =setup("/npc/kabus32_left1",48,48);
        left2 =setup("/npc/kabus32_left2",48,48);
        right1 =setup("/npc/kabus32_right1",48,48);
        right2 =setup("/npc/kabus32_right2",48,48);
        avatar1= setup("/npc/kabusavatar",48,48);

    }
    public void setDialogue(){
        dialogues[0]="Kabuslar Cehennemine Sırtlanlar Dolmuş Babo";
        dialogues[1]="Kabus Kabus Kabus!!";
        dialogues[2]="Leg Press Nerde Lan babbaa";

    }
    public void update(){

        setAction();
        checkCollision();

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
    public void setAction(){
        if (onPath==true){
            /*
            goalCol[pathPoints]=(gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
            goalRow[pathPoints]=(gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;
            */

            searchPath(goalCol[pathPoints],goalRow[pathPoints]);
            if (pathPoints>pathPointsLimit){pathPoints=0;}

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
        }}}
    public void speak() {
        super.speak();
        onPath=true;
        gp.gameState= gp.tradeState;
        gp.ui.commandNum=-1;
        gp.playSE(47);
        gp.ui.npc=this;
    }
    public void setItems(){
        inventory.add(new OBJ_zbar10kg(gp));
        inventory.add(new OBJ_10lukPlaka(gp));
        inventory.add(new OBJ_20likPlaka(gp));
        inventory.add(new OBJ_zbar20kg(gp));
        inventory.add(new OBJ_CanavarElma(gp));
        inventory.add(new OBJ_Bardak(gp));
        inventory.add(new OBJ_Dumbell10kg(gp));
        inventory.add(new OBJ_Dumbell10kg(gp));

    }



}
