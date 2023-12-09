package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;

public class OBJ_Bardak extends Entity {
    GamePanel gp;
    int recovered=0;
    int artıs=0;
    public OBJ_Bardak(GamePanel gp){
        super(gp);
        this.gp=gp;
        name ="bardak";
        solidArea.x=0;
        solidArea.y=0;
        solidArea.width=32;
        solidArea.height=32;
        height =48;
        width=48;
        price=5;
        stackable=true;
        down1=setup("/objects/bardak32px", width,height);
        type=type_consumable;
        description="["+name+"]"+"\n Canavar bardak balgam verir OK.";
    }
    public boolean use(Entity entity){
        gp.ui.avatar=gp.player.avatar2;
        gp.gameState=gp.dialogueState;
        recovered= gp.player.mana+recoverValue;
        artıs = recoverValue-(recovered-gp.player.maxMana);
        gp.player.mana+=artıs;
        gp.ui.currentDialogue="Canavar çay balgam verir ok.\n"+artıs+" balgam arttı" ;
        gp.playSE(31);
        return true;
    }

}
