package object.weapons;

import entity.Entity;
import main.GamePanel;

public class OBJ_10lukPlaka extends Entity {
    public OBJ_10lukPlaka(GamePanel gp) {
        super(gp);
        name="10 Kege Plaka";
        width=48;
        height=48;
        price=10;
        down1=setup("/silahlar/10kgplaka",width,height);
        defenceValue=1;
        description="["+name+"]"+"\nsavunma=1\nagırlıkları yerine bırak ok.";
        type= type_shield;

    }
}
