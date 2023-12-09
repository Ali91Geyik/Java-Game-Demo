package object.environment;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class OBJ_MaviOto extends Entity {
    GamePanel gp;

    int pathPointLimit;
    public OBJ_MaviOto(GamePanel gp, int[]newGoalCol, int[]newGoalRow,int newPathPointLimit ) {
        super(gp);
        this.gp=gp;
        direction = "down";
        speed = 2;
        name = "MaviOto";
        width = 96;
        height = 96;
        type = type_interactive;
        solidArea.x = 32;
        solidArea.y = 32;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.height= 47;
        solidArea.width = 47;
        collision = true;
        goalCol= newGoalCol;
        goalRow=newGoalRow;
        pathPoints=0;
        pathPointsLimit=newPathPointLimit;

        getOBJImage();

    }

    public void getOBJImage() {
        up1 = setup("/objects/environment/maviOto/maviotoup1", width, height);
        up2 = setup("/objects/environment/maviOto/maviotoup2", width, height);
        down1 = setup("/objects/environment/maviOto/maviotodown1",  width, height);
        down2 = setup("/objects/environment/maviOto/maviotodown2", width, height);
        left1 = setup("/objects/environment/maviOto/maviotosol1", width, height);
        left2 = setup("/objects/environment/maviOto/maviotosol2", width, height);
        right1 = setup("/objects/environment/maviOto/maviotosag1", width, height);
        right2 = setup("/objects/environment/maviOto/maviotosag2", width, height);
        avatar1 = setup("/objects/environment/maviOto/maviotodown1", width, height);

    }
    public void interact(){
        gp.gameState=gp.dialogueState;
        gp.ui.currentDialogue = "paran yok babo";

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

    public void setAction() {
        if (onPath==true){

            searchPath(goalCol[pathPoints],goalRow[pathPoints]);
            if (pathPoints>pathPointsLimit){pathPoints=0;}
    }
    }

}
