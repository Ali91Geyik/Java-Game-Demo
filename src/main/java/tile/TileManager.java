package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath=true;
    int counter=0;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map01.txt",0);
        loadMap("/maps/map02.txt",1);

    }
    public void getTileImage(){

            setup(0,"0grass",false);
            setup(1,"1stone",true);
            setup(2,"2block",false);
            setup(3,"3agac",true);
            setup(4,"4yol",false);
            setup(5,"5yol",false);
            setup(6,"6yol",false);
            setup(7,"7yol",false);
            setup(8,"8yol",false);
            setup(9,"9yol",false);
            setup(10,"10yol",false);
            setup(11,"11wall",true);
        setup(12,"12roof",true);
        setup(13,"13roof",true);
        setup(14,"14roof",true);
        setup(15,"15roof",true);
        setup(16,"16roof",true);
        setup(17,"17roof",true);
        setup(18,"18duvar",true);
        setup(19,"19duvar",true);
        setup(20,"20duvar",true);
        setup(21,"21duvar",true);
        setup(22,"22zemin",false);
        setup(23,"23zemin",false);
        setup(24,"24zemin",false);
        setup(25,"25zemin",false);
        setup(26,"26zemin",false);
        setup(27,"27zemin",false);
        setup(28,"28zemin",false);
        setup(29,"29zemin",true);
        setup(30,"30zemin",true);
        setup(31,"31zemin",true);
        setup(32,"32zemin",true);
        setup(33,"33zemin",true);
        setup(34,"34zemin",false);
        setup(35,"35zemin",false);
        setup(36,"36zemin",false);










    }
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index]= new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
            tile[index].image= uTool.scaleImage(tile[index].image, gp.tileSize,gp.tileSize);
            tile[index].collision=collision;



        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void loadMap(String filePath, int map){
        try {
            InputStream is= getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while (col< gp.maxWorldCol && row <gp.maxWorldRow){
                String line= br.readLine();
                while (col< gp.maxWorldCol){
                    String numbers[]= line.split("\t");
                    int num= Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e){
        e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int worldCol=0;
        int worldRow=0;

        while (worldCol< gp.maxWorldCol&& worldRow< gp.maxWorldRow){

            int tileNum= mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX= worldCol*gp.tileSize;
            int worldY= worldRow*gp.tileSize;
            int screenX= worldX-gp.player.worldX+gp.player.screenX;
            int screenY= worldY-gp.player.worldY+gp.player.screenY;



            if( worldX+gp.tileSize>gp.player.worldX-gp.player.screenX&&
                worldX-gp.tileSize<gp.player.worldX+gp.player.screenX&&
                worldY+gp.tileSize>gp.player.worldY-gp.player.screenY&&
                worldY-gp.tileSize<gp.player.worldY+gp.player.screenY){

                /*
                counter++;
                if (counter%2==0){
                    screenX+=4;                   kükreme efekti için yedek mekanik
                    screenY+=4;
                }else {screenX-=4;
                    screenY-=4;}  */

                g2.drawImage(tile[tileNum].image,screenX,screenY,null);
            }


            worldCol++;

            if(worldCol==gp.maxWorldCol){
                worldCol=0;

                worldRow++;

            }
        }
        if (drawPath==true){
            g2.setColor(new Color(255,0,0,70));
            for (int i=0; i<gp.pFinder.pathList.size();i++){
                int worldX= gp.pFinder.pathList.get(i).col*gp.tileSize;
                int worldY= gp.pFinder.pathList.get(i).row*gp.tileSize;
                int screenX= worldX-gp.player.worldX+gp.player.screenX;
                int screenY= worldY-gp.player.worldY+gp.player.screenY;

                g2.fillRect(screenX,screenY,gp.tileSize,gp.tileSize);

            }
        }
    }
}
