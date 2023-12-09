package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_CarKey extends Entity {
    GamePanel gp;
    public OBJ_CarKey(GamePanel gp) {
        super(gp);
        this.gp=gp;

        type=type_consumable;
        stackable=true;
        name= "Araba Anahtarı";
        down1= setup("/objects/arabaenektar", gp.tileSize,gp.tileSize);
        description= "["+name+"]"+ "Araba çalıştırır";
        price=100;
    }
    public boolean use(Entity entity){
        gp.gameState=gp.dialogueState;

        int objIndex = getDetected(entity,gp.hareketli,"MaviOto");
        if (objIndex!=999){
            System.out.println(gp.hareketli[gp.currentMap][objIndex].name);
            if (gp.hareketli[gp.currentMap][objIndex].onPath==false){
            gp.ui.currentDialogue="Araba Çalıştı";
            gp.hareketli[gp.currentMap][objIndex].onPath=true;
            return true;
            }
            else {
                gp.ui.currentDialogue="Araba Durdu";
                gp.hareketli[gp.currentMap][objIndex].onPath=false;
                return true;
            }
        }
        else {
            gp.ui.currentDialogue="araba mı var yarram!";
            return false;
        }
    }
}
