package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;

public class OBJ_Kapi extends Entity {
    GamePanel gp;
    public OBJ_Kapi(GamePanel gp){
        super(gp);
        name ="kapı";
        height =128;
        width=128;
        solidArea.x=0;
        solidArea.y=0;
        solidArea.width=128;
        solidArea.height=128;

        down1= setup("/objects/kapi64x",width,height);
        collision=true;
        type=type_structure;

    }
}
