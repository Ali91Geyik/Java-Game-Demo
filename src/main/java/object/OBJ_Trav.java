package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Trav extends Entity {
    GamePanel gp;
    public OBJ_Trav(GamePanel gp){
        super(gp);
        name = "trav";
        height =48;
        width=48;
        solidArea.x=0;
        solidArea.y=0;
        solidArea.width=48;
        solidArea.height=48;
        collision=true;

        down1= setup("/objects/trav32px",width,height);

    }
}
