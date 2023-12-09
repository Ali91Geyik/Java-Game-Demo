package object.visuals;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana extends Entity {
    GamePanel gp;

    public OBJ_Mana(GamePanel gp){
        super(gp);
        this.gp=gp;
        name="Mana Bardağı";
        width=32;
        height=32;
        image= setup("/visuals/bardak32px",width,height);
        image1=setup("/visuals/bosbardak32",width,height);
        down1=setup("/visuals/bardak32px",gp.tileSize,gp.tileSize);
        type=type_visual;

    }
}
