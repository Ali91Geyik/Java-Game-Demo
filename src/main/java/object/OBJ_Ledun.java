package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;

public class OBJ_Ledun extends Entity {
    GamePanel gp;
    public OBJ_Ledun(GamePanel gp){
        super(gp);
        name ="ledun";
        solidArea.x=0;
        solidArea.y=0;
        solidArea.width=144;
        solidArea.height=48;
        height =96;
        width=144;
        collision=true;

        down1=setup("/objects/ledun48x",width,height);
        type=type_structure;


    }}
