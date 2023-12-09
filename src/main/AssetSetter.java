package main;

import entity.NPC_Kabus;
import monster.MON_Dislike;
import monster.Mon_Sirtlan;
import object.*;
import object.dekor.*;
import object.environment.OBJ_MaviOto;
import object.projectiles.OBJ_Dumbell10kg;
import object.valuables.OBJ_Coin;
import object.weapons.OBJ_10lukPlaka;
import object.weapons.OBJ_20likPlaka;
import object.weapons.OBJ_zbar10kg;
import object.weapons.OBJ_zbar20kg;
import tile.IT_door;

public class AssetSetter {
GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){
        int mapNum=0;
        int i=0;
        gp.obj[mapNum][i] = new OBJ_Ledun(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*18;
        gp.obj[mapNum][i].worldY=gp.tileSize*15;
        i++;
        gp.obj[mapNum][i] = new OBJ_Dumbell10kg(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*35;
        gp.obj[mapNum][i].worldY=gp.tileSize*29;
        i++;

    }
    public void setHareketli(){
        int mapNum=0;
        int i =0;
        gp.hareketli[mapNum][i]= new IT_door(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*25;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*19;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_Bina(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*24;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*13;
        i++;

        mapNum=1;
        i=0;
        gp.hareketli[mapNum][i]= new OBJ_Ayna1(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*19;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*14;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_Ayna1(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*19;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*15;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_Ayna1(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*25;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*14;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_Ayna1(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*25;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*15;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_Ayna1(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*30;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*14;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_Ayna1(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*30;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*15;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_KosuBandi(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*18;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*15;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_KosuBandi(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*20;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*15;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_BenchPress(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*22;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*15;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_ChestPress(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*24;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*15;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_DumbellRack(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*26;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*16;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_Kardiyo(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*29;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*15;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_Kardiyo2(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*31;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*15;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_PullDown(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*33;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*15;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_Mat1(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*20;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*18;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_Mat2(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*30;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*18;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_MaviOto(gp, new int[]{30,20}, new int[]{29,29},1);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*25;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*25;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_CanavarElma(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*24;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*24;
        i++;
        gp.hareketli[mapNum][i]= new OBJ_CanavarElma(gp);
        gp.hareketli[mapNum][i].worldX=gp.tileSize*23;
        gp.hareketli[mapNum][i].worldY=gp.tileSize*24;
        i++;





    }
    public void setNPC(){
        int mapNum=1;
        int i=0;
        gp.npc[mapNum][i]= new NPC_Kabus(gp);
        gp.npc[mapNum][i].worldX=gp.tileSize*19;
        gp.npc[mapNum][i].worldY=gp.tileSize*19;
        i++;
    }
    public void setMonster(){
        int mapNum=0;
        int i =0;
        gp.monster[mapNum][i] = new MON_Dislike(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*35;
        gp.monster[mapNum][i].worldY = gp.tileSize*28;
        i++;
        gp.monster[mapNum][i] = new MON_Dislike(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*35;
        gp.monster[mapNum][i].worldY = gp.tileSize*29;
        i++;
        gp.monster[mapNum][i] = new MON_Dislike(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*25;
        gp.monster[mapNum][i].worldY = gp.tileSize*28;
        i++;
        gp.monster[mapNum][i] = new MON_Dislike(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*25;
        gp.monster[mapNum][i].worldY = gp.tileSize*29;
        i++;
        gp.monster[mapNum][i] = new MON_Dislike(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*20;
        gp.monster[mapNum][i].worldY = gp.tileSize*27;
        i++;
        gp.monster[mapNum][i] = new MON_Dislike(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*20;
        gp.monster[mapNum][i].worldY = gp.tileSize*28;
        i++;


        mapNum=1;
        i =0;
        i++;
        /*
        gp.monster[mapNum][i] = new Mon_Sirtlan(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*32;
        gp.monster[mapNum][i].worldY = gp.tileSize*32;
        i++;
        gp.monster[mapNum][i] = new Mon_Sirtlan(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*31;
        gp.monster[mapNum][i].worldY = gp.tileSize*31;
        i++;
        gp.monster[mapNum][i] = new Mon_Sirtlan(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*30;
        gp.monster[mapNum][i].worldY = gp.tileSize*30;
        i++;
        gp.monster[mapNum][i] = new Mon_Sirtlan(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*32;
        gp.monster[mapNum][i].worldY = gp.tileSize*31;
        */
    }




}
