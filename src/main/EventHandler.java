package main;


import entity.Entity;

import java.security.PublicKey;

public class EventHandler {
    public boolean salongiris=false;
    public boolean map0giris= false;
    GamePanel gp;
    EventRect eventRect[][][];
    public int previousEventX, previousEventY;
    public boolean canTouchEvent =true;
    public int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp){
        this.gp=gp;
        eventRect= new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map=0;
        int col=0;
        int row=0;
        while (map<gp.maxMap&& col< gp.maxWorldCol&& row< gp.maxWorldRow) {

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col++;
            if (col== gp.maxWorldCol){
                col=0;
                row++;
                if (row==gp.maxWorldRow){
                    row=0;
                    map++;
                }
            }
        }
    }
    public void checkEvent(){
        int xDistance= Math.abs(gp.player.worldX-previousEventX);
        int yDistance= Math.abs(gp.player.worldY-previousEventY);
        int distance = Math.max(xDistance,yDistance);
        if (distance>gp.tileSize){ canTouchEvent=true; }

        if (canTouchEvent==true) {
            if (hit(0,10, 10, "down") == true) {
                damagePit( gp.dialogueState);
            }
            else if (hit(0,5, 5, "down") == true) {
                teleport(gp.dialogueState);
            }
            else if (hit(0,19, 16, "up") == true) {
                ledun(gp.dialogueState);

            }
            else if (hit(0,25,16,"any")==true){
                mapTeleport(1,19,20);
            }
        }
    }
    public boolean hit(int map , int col, int row, String reqDirection){
        boolean hit=false;

        if (map==gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;


                }

            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

        }
        return hit;
    }
    public void damagePit(int gameState){
        gp.gameState=gameState;
        gp.ui.avatar=gp.player.avatar1;
        gp.ui.currentDialogue="Tuzağa düştük zafer";
        gp.player.life-=1;
        canTouchEvent=false;

    }
    public void ledun (int gamestate){
        if (gp.keyH.enterPressed==true){
            gp.gameState=gamestate;
            gp.playSE(45);
            gp.player.attackCancelled=true;
            gp.ui.avatar=gp.player.avatar2;
            gp.ui.currentDialogue="ilimlendik babba";
            gp.player.life=gp.player.maxLife;
            gp.player.mana=gp.player.maxMana;
        }

    }
    public void teleport(int gamestate){
    gp.gameState=gamestate;
        gp.aSetter.setMonster();
        gp.playSE(16);
        gp.player.attackCancelled=true;
        gp.ui.avatar=gp.player.avatar2;
    gp.ui.currentDialogue="OK ZIRINK";
    gp.player.worldY=gp.tileSize*11;
    gp.player.worldX=gp.tileSize*1;
    }
    public void mapTeleport(int map, int col, int row){
        if (gp.keyH.enterPressed==true){
            gp.gameState= gp.cutSceneState;
            if (map==1){
                salongiris=true;
                gp.playSE(46);
            }
            tempMap=map;
            tempCol=col;
            tempRow=row;

        canTouchEvent=false;}
    }
    public void speak(Entity entity){
        if (gp.keyH.enterPressed==true){
            gp.gameState=gp.dialogueState;
            gp.player.attackCancelled=true;
            entity.speak();

        }

    }
}
