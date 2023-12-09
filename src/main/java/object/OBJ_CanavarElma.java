package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CanavarElma extends Entity {
    GamePanel gp;
    int recovered=0;
    int artıs=0;

    public OBJ_CanavarElma(GamePanel gp){
        super(gp);
        this.gp=gp;
        name="Canavar Elma";
        width=48;
        height=48;
        recoverValue=4;
        price=5;
        stackable=true;
        down1=setup("/objects/elma32",gp.tileSize,gp.tileSize);
        down2=setup("/objects/elma32",gp.tileSize,gp.tileSize);

        description="["+name+"]"+"\n Canavar elma can verir OK.";
        type=type_consumable;
    }
    public boolean use(Entity entity){
        gp.ui.avatar=gp.player.avatar2;
        gp.gameState=gp.dialogueState;
        recovered= gp.player.life+recoverValue;
        artıs = recoverValue-(recovered-gp.player.maxLife);
        gp.player.life+=artıs;
        gp.ui.currentDialogue="Canavar elma can verir ok.\n"+artıs+" canın arttı" ;
        gp.playSE(31);
        return true;
    }

}
