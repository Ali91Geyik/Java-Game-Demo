package object.weapons;

import entity.Entity;
import main.GamePanel;

public class OBJ_zbar10kg extends Entity {
    GamePanel gp;
    public OBJ_zbar10kg(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="10 Kege Zbar";
        width=48;
        height=48;
        price=15;
        down1=setup("/silahlar/zbar10kg",48,48);
        attackValue=2;
        attackArea.width=64;
        attackArea.height=64;
        description="["+name+"]"+"\nkökleme=2\ndik yapılmış ok.";
        type= type_melee;


    }
}
