package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;

public class OBJ_Bina extends Entity {
    GamePanel gp;
    public OBJ_Bina(GamePanel gp){
        super(gp);
        name ="bina";
        height =192;
        width=192;
        solidArea.x=10;
        solidArea.y=25;
        solidArea.width=170;
        solidArea.height=140;

        down1=setup("/objects/bina1",width,height);
        down2=setup("/objects/bina1",width,height);
        collision=true;
        type=type_structure;


    }
}

