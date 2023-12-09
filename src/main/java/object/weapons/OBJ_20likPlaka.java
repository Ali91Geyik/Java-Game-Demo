package object.weapons;

import entity.Entity;
import main.GamePanel;

public class OBJ_20likPlaka extends Entity {
    public OBJ_20likPlaka(GamePanel gp) {
        super(gp);
        name="20 Kege Plaka";
        width=48;
        height=48;
        price=30;
        down1=setup("/silahlar/20kgplaka",width,height);
        defenceValue=2;
        description="["+name+"]"+"\nsavunma=2\nagırlıkları yerine bırak ok.";
        type= type_shield;

    }
}
