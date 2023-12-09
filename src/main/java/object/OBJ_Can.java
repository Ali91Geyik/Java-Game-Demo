package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Can extends Entity {
    GamePanel gp;

    public OBJ_Can(GamePanel gp){
        super(gp);
        this.gp=gp;
        name="Can Elması";
        width=32;
        height=32;
        image= setup("/objects/elma32",width,height);
        image1=setup("/objects/elma32_yarım",width,height);
        image2=setup("/objects/elma32_bos",width,height);
        down1=setup("/objects/elma32",gp.tileSize,gp.tileSize);
        type=type_visual;



    }

}
