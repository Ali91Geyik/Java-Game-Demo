package object.projectiles;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Dumbell10kg extends Projectile {
    GamePanel gp;
    int spriteCounter;
    public OBJ_Dumbell10kg(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="Dumbell 10kg";
        description="["+name+"]"+"\n 10 kege dumbell yerine koy OK.";
        speed=4+dexterity;
        maxLife=80;
        life=maxLife;
        attack=3+dexterity;
        useCost=1;
        price=10;
        ammo=10;
        alive=false;
        getImage();
        spriteCounter=0;
        type = type_projectile;
        width=48;
        height=48;
        down1=setup("/projectiles/dumbell10kg/dumbell10kg3",gp.tileSize,gp.tileSize);
    }
    public void getImage(){
        projectileAttackDown= setup1(new BufferedImage[4],"/projectiles/dumbell10kg/dumbell10kg",48,48);
        projectileAttackHit = setup1(new BufferedImage[6],"/projectiles/dumbell10kg/dumbell10kghit",96,96 );

    }
    public void update(){
        if (user==gp.player){
            int monsterIdx=gp.cChecker.checkEntity(this,gp.monster);
            if (monsterIdx!=999){
                gp.player.damageMonster(monsterIdx,this, attack);
                gp.playSE(30);
                direction="hit";
                if (spriteNum3==6){
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
        spriteCounter4++;
        life--;
        if (life<=0){
            alive=false;}
        animationSpriteChanger();
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

            switch (direction) {
                case "up":
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
                case "right":
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
                case "hit":
                    tempWidth=128;
                    tempHeight=128;
                    screenX-=48;
                    screenY-=48;
                    if (spriteNum == 1) {
                        image = projectileAttackHit[0];
                    }
                    if (spriteNum == 2) {
                        image = projectileAttackHit[1];
                    }
                    if (spriteNum == 3) {
                        image = projectileAttackHit[2];
                    }
                    if (spriteNum == 4) {
                        image = projectileAttackHit[3];
                    }
                    if (spriteNum == 5) {
                        image = projectileAttackHit[4];
                    }
                    if (spriteNum == 6) {
                        image = projectileAttackHit[5];
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
    public void animationSpriteChanger2(){
        if (spriteCounter4 > 3) {
            switch (spriteNum) {
                case 1:
                    spriteNum = 2;
                    spriteCounter4 = 0;
                    break;
                case 2:
                    spriteNum = 3;
                    spriteCounter4 = 0;
                    break;
                case 3:
                    spriteNum = 4;
                    spriteCounter4 = 0;
                    break;
                case 4:
                    spriteNum = 5;
                    spriteCounter4 = 0;
                    break;
                case 5:
                    spriteNum = 6;
                    spriteCounter4 = 0;
                    break;
                case 6:
                    spriteNum = 1;
                    spriteCounter4 = 0;
                    break;

            }
        }}
    public boolean haveResourceMethod(Entity user){
        boolean haveResource=false;
        if (user.ammo>= useCost){
            haveResource=true;
        }

        return haveResource;
    }
    public void subtractResource(Entity user){
        user.ammo-=useCost;
    }
    public boolean use(Entity entity){
        gp.ui.avatar=gp.player.avatar2;
        gp.gameState=gp.dialogueState;

        gp.player.ammo+=ammo;
        gp.ui.currentDialogue="Cephane geldi babo.\n"+ammo+" dumbell eklendi" ;
        gp.playSE(5);
        return true;

    }

}

