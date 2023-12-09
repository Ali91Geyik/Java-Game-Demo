package object.weapons;

import entity.Entity;
import main.GamePanel;

public class OBJ_zbar20kg extends Entity {
    public OBJ_zbar20kg(GamePanel gp) {
        super(gp);
        name="20 Kege Zbar";
        width=48;
        height=48;
        price=45;
        down1=setup("/silahlar/zbar20kg",48,48);
        attackValue=4;
        description="["+name+"]"+"\nkökleme=4\ndik yapılmış ok.";
        type=type_melee;


    }
}
