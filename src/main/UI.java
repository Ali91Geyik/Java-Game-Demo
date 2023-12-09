package main;



import entity.Entity;
import object.OBJ_Can;
import object.valuables.OBJ_Coin;
import object.visuals.OBJ_Mana;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_50B, effraBold;

    public boolean messageOn=false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter= new ArrayList<>();
    public String currentDialogue="";
    public BufferedImage avatar=null;
    public BufferedImage image, coin;
    public BufferedImage elmaFull,elmaYarim,elmaBos;
    public BufferedImage bardakFull, bardakBoş;
    public int commandNum =0;
    public int playerSlotCol =0;
    public int playerSlotRow =0;
    public int npcSlotCol=0;
    public int npcSlotRow=0;
    public int subState=0;
    int counter=0;
    public Entity npc;

    public UI(GamePanel gp){
        this.gp=gp;
        arial_40=new Font("Arial", Font.PLAIN,40);
        arial_50B = new Font("Arial", Font.BOLD, 50);
        InputStream is= getClass().getResourceAsStream("/font/Effra Medium.ttf");
        try {
            effraBold = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch (FontFormatException e){
            e.printStackTrace();
        }catch (IOException e){e.printStackTrace();}

        Entity elma = new OBJ_Can(gp);
        elmaFull=elma.image;
        elmaYarim=elma.image1;
        elmaBos=elma.image2;
        Entity bardak= new OBJ_Mana(gp);
        bardakFull=bardak.image;
        bardakBoş=bardak.image1;

        Entity coin1 = new OBJ_Coin(gp);
        coin=coin1.down1;
    }
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2){
        this.g2=g2;
        g2.setFont(effraBold);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        //TitleState
        if (gp.gameState==gp.titleState){
            drawTitleScreen();
        }

        //playState
        if(gp.gameState==gp.playState){
            drawPlayerLife();
            drawMessage();
        }
        //pauseState
        if(gp.gameState== gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();

        }
        //dialogueState
        if (gp.gameState== gp.dialogueState){
            drawDialogueScreen();
        }
        //characterState
        if (gp.gameState==gp.characterState){
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }
        //optionsState
        if (gp.gameState==gp.optionsState){
            drawOptionsScreen();
        }
        //optionsState
        if (gp.gameState==gp.gameOverState){
            drawGameOverScreen();
        }
        //CUTSCENE
        if (gp.gameState==gp.cutSceneState){
            drawCutScreenScene();
        }
        //TRADE
        if (gp.gameState==gp.tradeState){
            drawTradeState();
        }

    }
    public void drawGameOverScreen(){
        if (counter<=70){
            counter++;}
        g2.setColor(new Color(0,0,0,counter*3));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        if (counter>=50){
        gp.gameOver.update();
        gp.gameOver.draw(g2);
        int x=gp.screenWidth/2;
        int y=gp.tileSize*2;
        String text;
        text= "BEYAZA BULANDIN!";
        drawOutlinedTextWithShadow(text,x,y,effraBold,5,70,Color.YELLOW,Color.RED,7,7);


        y+=gp.tileSize*7;
        text="Çay Koy Keçeli, Yeniden Başlıyoruz";
        if (commandNum==0){
            drawOutlinedTextWithShadow(">   "+text,x,y,effraBold,2,25,Color.YELLOW,Color.RED,5,5);

        }else {
            drawOutlinedTextWithShadow(text, x, y, effraBold, 2, 25, Color.YELLOW, Color.RED, 5, 5);
        }
        y+=gp.tileSize;
        text="Tekme vede İmha";
        if (commandNum==1){
            drawOutlinedTextWithShadow(">   "+text,x,y,effraBold,2,25,Color.YELLOW,Color.RED,5,5);

        }else {
            drawOutlinedTextWithShadow(text, x, y, effraBold, 2, 25, Color.YELLOW, Color.RED, 5, 5);
        }}
    }
    public void drawOptionsScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        // subwindow
        int frameX=gp.tileSize*6;
        int frameY=gp.tileSize;
        int frameWidth=gp.tileSize*8;
        int frameHeight=gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        switch (subState){
            case 0: options_top(frameX,frameY); break;
            case 1: options_FullScreenNotification(frameX,frameY); break;
            case 2: optionsControls(frameX,frameY); break;
            case 3: optionsEndGame(frameX,frameY); break;
        }
        gp.keyH.enterPressed=false;

    }
    public void options_top(int frameX, int frameY){
        int textX=gp.screenWidth/2;
        int textY=gp.tileSize*2;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //TITLE
        String text ="AYARLAR";
        drawOutlinedTextWithShadow(text,textX,textY,effraBold,2,35,Color.YELLOW,Color.RED,5,3);

        //FULL SCREEN ON OFF
        textX=frameX+110;
        textY=gp.tileSize*4;
        if (commandNum == 0) {
            drawOutlinedTextWithShadow("> Tam Ekran",textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,5,3);
            if (gp.keyH.enterPressed==true)
            {if (gp.fullScreenOn==false){gp.fullScreenOn=true;}
            else if (gp.fullScreenOn==true){gp.fullScreenOn=false;}
            subState=1;}
        }
        else{ drawOutlinedTextWithShadow("Tam Ekran",textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,5,3);}
        //MUZİK
        textY+=gp.tileSize;
        if (commandNum == 1) {
            drawOutlinedTextWithShadow("> Müzik",textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,5,3);
        }
        else{ drawOutlinedTextWithShadow("Müzik",textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,5,3);}
        //SE
        textY+=gp.tileSize;
        if (commandNum == 2) {
            drawOutlinedTextWithShadow("> Sesler",textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,5,3);
        }
        else{ drawOutlinedTextWithShadow("Sesler",textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,5,3);}
        //CONTROL
        textY+=gp.tileSize;
        if (commandNum == 3) {
            drawOutlinedTextWithShadow("> Kontroller",textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,5,3);
            if (gp.keyH.enterPressed==true){subState=2;commandNum=0; }
        }
        else{ drawOutlinedTextWithShadow("Kontroller",textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,5,3);}
        //END GAME
        textY+=gp.tileSize;
        if (commandNum == 4) {
            drawOutlinedTextWithShadow("> Otoban",textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,5,3);
            if (gp.keyH.enterPressed==true){
             subState=3;
             commandNum=0;
            }
        }
        else{ drawOutlinedTextWithShadow("Otoban",textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,5,3);}
        //GERİ DÖN
        textY+=gp.tileSize*2;
        if (commandNum == 5) {
            drawOutlinedTextWithShadow("> Eveveve",textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,5,3);
            if (gp.keyH.enterPressed==true){
                gp.gameState= gp.playState;
                commandNum=0;
            }
        }
        else{ drawOutlinedTextWithShadow("Eveveve",textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,5,3);}

        //TAM EKRAN CHECK BOX
        textY=180;
        textX=frameX+gp.tileSize*7;

        g2.setColor(Color.RED);
        g2.drawRoundRect(textX,textY,26,26,3,3);
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.YELLOW);
        g2.drawRoundRect(textX+2,textY+2,22,22,3,3);
        g2.setColor(Color.RED);
        if (gp.fullScreenOn==true){
            g2.fillRoundRect(textX+4,textY+4,18,18,3,3);
        }

        //MUZİK BAR
        textX-=gp.tileSize*2;
        textY+=gp.tileSize;
        g2.setColor(Color.RED);
        g2.drawRoundRect(textX,textY,120,26,3,3);
        g2.setStroke(new BasicStroke(4));
        int volumeWidth = 24* gp.music.volumeScale;
        g2.setColor(Color.YELLOW);
        g2.fillRoundRect(textX,textY+2,volumeWidth,22,3,3);


        //SE BAR
        textY+=gp.tileSize;
        g2.setColor(Color.RED);
        g2.drawRoundRect(textX,textY,120,26,3,3);
        g2.setStroke(new BasicStroke(4));
        volumeWidth = 24* gp.se.volumeScale;
        g2.setColor(Color.YELLOW);
        g2.fillRoundRect(textX,textY+2,volumeWidth,22,3,3);

        gp.config.saveConfig();


    }
    public void options_FullScreenNotification(int frameX, int frameY){
    int textX=frameX+gp.tileSize*4;
    int textY=frameY+gp.tileSize*3;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    currentDialogue ="Değişiklikler\n git gel yaptıktan\n sonra işlenir OK.";
    for (String line: currentDialogue.split("\n")){
        drawOutlinedTextWithShadow(line,textX,textY,effraBold,2,35,Color.YELLOW,Color.RED,5,3);
        textY+=60;
    }
    //Geri Dön
        textY+=gp.tileSize*2;
    if (commandNum==0)
    {
        drawOutlinedTextWithShadow(">  Geri", textX,textY,effraBold,2,25,Color.YELLOW,Color.RED,4,2);
        if (gp.keyH.enterPressed==true){subState=0;}
    }



    }
    public void optionsEndGame(int frameX, int frameY) {
        int textX = gp.screenWidth / 2;
        int textY = frameY + gp.tileSize * 3;
        currentDialogue = "Tekme vede \n İmha Edileceksin \n Kabul mü?";
        for (String line : currentDialogue.split("\n")) {
            drawOutlinedTextWithShadow(line, textX, textY, effraBold, 3, 35, Color.YELLOW, Color.RED, 5, 3);
            textY += 40;
        }
        //yes
            textX -=gp.tileSize*2;
            textY+=gp.tileSize*3;
            if (commandNum==0){
            drawOutlinedTextWithShadow( "> Domal10", textX, textY, effraBold, 3, 25, Color.YELLOW, Color.RED, 5, 3);
            if (gp.keyH.enterPressed==true){
                subState=0;
                gp.gameState=gp.titleState;
            }
            }
            else {drawOutlinedTextWithShadow( "Domal10", textX, textY, effraBold, 3, 25, Color.YELLOW, Color.RED, 5, 3);}

        //No
        textY+=gp.tileSize;
            if(commandNum==1){
            drawOutlinedTextWithShadow( ">  Eveveve", textX, textY, effraBold, 3, 25, Color.YELLOW, Color.RED, 5, 3);
                if (gp.keyH.enterPressed==true){
                    subState=0;
                    commandNum=4;
                }
            }
            else {
                drawOutlinedTextWithShadow( "Eveveve", textX, textY, effraBold, 3, 25, Color.YELLOW, Color.RED, 5, 3);}



    }
    public void optionsControls(int frameX, int frameY){
        int textX=gp.screenWidth/2;
        int textY=frameY+gp.tileSize;
        //TİTLE
        String text="KONTROLLER";
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawOutlinedTextWithShadow(text, textX,textY,effraBold,3,35,Color.YELLOW,Color.RED,5,3 );

        textX-=gp.tileSize*2;
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("Hareket", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("İlmi atış", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("Saldırı/Etkileşim", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("Eşya Fırlatma", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("Karakter Ekranı", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("Ayarlar Ekranı", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("Duraklatma", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );

        textY= frameY+gp.tileSize*2;
        textX+=4*gp.tileSize;
        drawOutlinedTextWithShadow("WASD", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("F", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("ENTER", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("Q", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("C", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("ESC", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );
        textY+=gp.tileSize;
        drawOutlinedTextWithShadow("P", textX,textY,effraBold,1,20,Color.YELLOW,Color.RED,4,3 );

        textY=frameY+ gp.tileSize*9;
        textX=(gp.screenWidth/2)-(gp.tileSize*2);
        if (commandNum==0){
            drawOutlinedTextWithShadow(">  GERİ", textX,textY,effraBold,1,25,Color.YELLOW,Color.RED,4,3 );
            if (gp.keyH.enterPressed==true){
                subState=0;
                commandNum=3;
            }
        }


    }
    public void drawCharacterScreen(){
        final int frameX= gp.tileSize/2;
        final int frameY= gp.tileSize/2;
        final int frameWidth= gp.tileSize*5;
        final int frameHeigth=gp.tileSize*11;
        int textX= 105;
        int boslukY=35;
        drawSubWindow(frameX,frameY,frameWidth,frameHeigth);

        drawOutlinedText("İlm-i Ledun:", textX, frameY+boslukY, effraBold, 1,25);
        drawOutlinedText("Sabır:",textX, frameY+boslukY*2, effraBold, 1,25);
        drawOutlinedText("Balgam:",textX, frameY+boslukY*3, effraBold, 1,25);
        drawOutlinedText("Bench PR:",textX, frameY+boslukY*4, effraBold, 1,25);
        drawOutlinedText("Beceri:",textX, frameY+boslukY*5, effraBold, 1,25);
        drawOutlinedText("Kökleme:",textX, frameY+boslukY*6, effraBold, 1,25);
        drawOutlinedText("Savunma:", textX, frameY+boslukY*7, effraBold, 1,25);
        drawOutlinedText("İlim:",textX, frameY+boslukY*8, effraBold, 1,25);
        drawOutlinedText("Eksik ilim:", textX, frameY+boslukY*9, effraBold, 1,25);
        drawOutlinedText("Mangır:", textX, frameY+boslukY*10, effraBold, 1,25);
        drawOutlinedText("Silah:",textX, frameY+405, effraBold, 1,25);
        drawOutlinedText("Kalkan:", textX, frameY+460, effraBold, 1,25);

        //Values
        int valueX= textX+120;

        String value= String.valueOf(gp.player.level);
        drawOutlinedText(value, valueX, frameY+boslukY, effraBold, 1,25);
        value= String.valueOf(gp.player.life);
        drawOutlinedText(value, valueX, frameY+boslukY*2, effraBold, 1,25);
        value= String.valueOf(gp.player.mana);
        drawOutlinedText(value, valueX, frameY+boslukY*3, effraBold, 1,25);
        value= String.valueOf(gp.player.strength);
        drawOutlinedText(value, valueX, frameY+boslukY*4, effraBold, 1,25);
        value= String.valueOf(gp.player.dexterity);
        drawOutlinedText(value, valueX, frameY+boslukY*5, effraBold, 1,25);
        value= String.valueOf(gp.player.attack);
        drawOutlinedText(value, valueX, frameY+boslukY*6, effraBold, 1,25);
        value= String.valueOf(gp.player.defence);
        drawOutlinedText(value, valueX, frameY+boslukY*7, effraBold, 1,25);
        value= String.valueOf(gp.player.exp);
        drawOutlinedText(value, valueX, frameY+boslukY*8, effraBold, 1,25);
        value= String.valueOf(gp.player.nextLevelExp);
        drawOutlinedText(value, valueX, frameY+boslukY*9, effraBold, 1,25);
        value= String.valueOf(gp.player.coin);
        drawOutlinedText(value, valueX, frameY+boslukY*10, effraBold, 1,25);

        if (gp.player.currentWeapon!=null){
        g2.drawImage(gp.player.currentWeapon.down1,valueX-24,frameY+380,null );}
        if (gp.player.currentShield!=null){
        g2.drawImage(gp.player.currentShield.down1,valueX-24,frameY+440,null );}

    }
    public void drawInventory(Entity entity, boolean cursor){
        int frameX =0;
        int frameY = 0;
        int frameWidth= 0;
        int frameHeigth= 0;
        int slotCol=0;
        int slotRow=0;
        if (entity==gp.player){

            frameX =gp.tileSize*13;
            frameY = gp.tileSize;
            frameWidth= gp.tileSize*6;
            frameHeigth= gp.tileSize*5;
            slotRow=playerSlotRow;
            slotCol=playerSlotCol;
        }else {
            frameX =gp.tileSize*2;
            frameY = gp.tileSize;
            frameWidth= gp.tileSize*6;
            frameHeigth= gp.tileSize*5;
            slotRow=npcSlotRow;
            slotCol=npcSlotCol;
        }

        //FRAME

        drawSubWindow(frameX,frameY,frameWidth,frameHeigth);
        //SLOT
        final int slotXStart =frameX+20;
        final int slotYStart =frameY+20;
        int slotX= slotXStart;
        int slotY= slotYStart;
        int slotSize= gp.tileSize+3;

            //DRAW PLAYERS ITEMS
            for (int i = 0; i < entity.inventory.size(); i++) {
                //EQUIPPED ITEM CURSOR
                if (entity.inventory.get(i) == entity.currentWeapon
                        || entity.inventory.get(i) == entity.currentShield) {
                    g2.setColor(new Color(240, 190, 90));
                    g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
                }

                g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
                //DİSPLAY THE AMOUNT
                if (entity.inventory.get(i).amount>1){

                    int amountX;
                    int amountY;
                    String s=""+ entity.inventory.get(i).amount;
                    amountX=slotX+40;
                    amountY=slotY+40;
                    drawOutlinedText(s,amountX,amountY,effraBold,1,20);


                }

                slotX += slotSize;
                if (i == 4 || i == 9 || i == 14) {
                    slotX = slotXStart;
                    slotY += slotSize;
                }
            }
        //CURSOR
        if (cursor==true) {
            int cursorX = slotXStart + (slotSize * slotCol);
            int cursorY = slotYStart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeigth = gp.tileSize;

            //  DRAW CURSOR
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeigth, 10, 10);

            //DESCRIPTION FRAME
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeigth;
            int dFrameWidth = frameWidth;
            int dFrameHeigth = gp.tileSize * 3;

            //DESCRİPTİON TEXT
            int textX = dFrameX;
            int textY = dFrameY +gp.tileSize;
            int itemIndex = getItemIdx(slotCol,slotRow);
            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeigth);
                for (String line : entity.inventory.get(itemIndex).description.split("\n")) {
                    drawOutlinedText(line, textX + (frameWidth / 2), textY, effraBold, 1, 20);
                    textY += 25;
                }
            }
        }

    }
    public int getItemIdx(int slotCol, int slotRow){
        int itemIdx = slotCol +(slotRow *5);
        return itemIdx;
    }
    public void drawDialogueScreen(){

        //window
        int x= gp.tileSize*3;
        int y= gp.tileSize/2;
        int width = gp.screenWidth-(gp.tileSize*6);
        int height = gp.tileSize*4;
        drawSubWindow(x,y,width,height);
        x+=24;
        y+=24;
        drawSubWindow(x,y,64,64);
        x+=9;
        y+=7;
        g2.drawImage(avatar,x,y,null);
        x+=64;
        y+=32;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
        for (String line : currentDialogue.split("\n")){
            g2.drawString(": "+line,x,y);
            y+=40;
        }
    }
    public void drawCutScreenScene(){
        if (counter<=70){
        counter++;}
        g2.setColor(new Color(0,0,0,counter*3));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        if (counter>=50){

            if (gp.eHandler.salongiris==true){
                g2.setColor(Color.RED);
                g2.fillRoundRect(45,45,875,470,10,10);
                gp.salonGiris.update();
                gp.salonGiris.draw(g2);
                gp.currentMap=gp.eHandler.tempMap;
                gp.player.worldX=gp.tileSize*gp.eHandler.tempCol;
                gp.player.worldY=gp.tileSize*gp.eHandler.tempRow;
                gp.eHandler.previousEventX=gp.player.worldX;
                gp.eHandler.previousEventY=gp.player.worldY;

            }
            if (gp.player.lwlUp==true){
                g2.setColor(Color.RED);
                g2.fillRoundRect(45,45,875,470,10,10);
                gp.lwlUp.update();
                gp.lwlUp.draw(g2);
                int y=gp.tileSize*2;
                String lwlText="İLMİN ARTTI BABO!!\n Yeni ilim seviyesi: "+gp.player.level;
                for (String line: lwlText.split("\n")){
                    drawOutlinedTextWithShadow(line,gp.screenWidth/2,y,effraBold,5,50,Color.YELLOW,Color.RED,7,7);
                    y+=gp.tileSize*7+35;}
            }

        }
    }
    public void drawTradeState(){
    switch (subState)
    {
        case 0: tradeSelect(); break;
        case 1: tradeBuy(); break;
        case 2: tradeSell(); break;
    }
    gp.keyH.enterPressed=false;

    }
    public void tradeSelect(){
        drawDialogueScreen();
        //window
        int x=gp.tileSize*15;
        int y=gp.tileSize*4;
        int width=gp.tileSize*3;
        int height= (int)(gp.tileSize*3.5);
        drawSubWindow(x,y,width,height);

        x+=72;
        y+=gp.tileSize;
        if (commandNum==0){drawOutlinedText("> Satın al",x,y,effraBold,1,25);
            if (gp.keyH.enterPressed){subState=1;}
        }
        else{ drawOutlinedText("Satın al",x,y,effraBold,1,25);}
        y+=35;
        if (commandNum==1){drawOutlinedText("> Sat",x,y,effraBold,1,25);
        if (gp.keyH.enterPressed){subState=2;}
        }
        else{ drawOutlinedText("Sat",x,y,effraBold,1,25);}
        y+=45;
        if (commandNum==2){drawOutlinedText("> Ayrıl",x,y,effraBold,1,25);
        if (gp.keyH.enterPressed){
            gp.gameState=gp.dialogueState;
            currentDialogue="KABUUUSS!!!";
        }
        }
        else{drawOutlinedText("Ayrıl",x,y,effraBold,1,25);}
    }
    public void tradeBuy(){
    //player
        drawInventory(gp.player,false);
        //npc
        drawInventory(npc,true);

        //İpuçları
        int x=gp.tileSize*2;
        int y=gp.tileSize*9;
        int width =gp.tileSize*6;
        int height =gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        drawOutlinedText("[ESC]=Geri",x+72,y+35,effraBold,1,20);

        //player para penceresi
        x=gp.tileSize*13;
        y=gp.tileSize*9;
        width=gp.tileSize*6;
        height=gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        drawOutlinedText("Mangır Durumu: "+gp.player.coin,x+112,y+35,effraBold,1,20);

        //ürün fiyat penceresi
        int itemIndex= getItemIdx(npcSlotCol,npcSlotRow);
        if(itemIndex<npc.inventory.size()){

        x=(int)(gp.tileSize*5.5);
        y=(int)(gp.tileSize*5.5);
        width=(int)(gp.tileSize*2.5);
        height=gp.tileSize;
        drawSubWindow(x,y,width,height);
        g2.drawImage(coin, x+10,y+7,32,32,null);

        int price=npc.inventory.get(itemIndex).price;
        drawOutlinedText("= "+price,x+70,y+25,effraBold,1,20);
        }
        //Satın al
        if (gp.keyH.enterPressed==true&&itemIndex<npc.inventory.size()){
            if (npc.inventory.get(itemIndex).price>gp.player.coin){
                subState=0;
                gp.gameState= gp.dialogueState;
                currentDialogue= "Mangır lazım babo OK.";
                gp.playSE(48);
                drawDialogueScreen();
            }
            else
            if (gp.player.canObtainItem(npc.inventory.get(itemIndex))==true){
                gp.player.coin-=npc.inventory.get(itemIndex).price;
                gp.playSE(18);
            }
            else {
                subState=0;
                gp.gameState=gp.dialogueState;
                currentDialogue="Cepler dolu babo OK.";

            }

        }



    }
    public void tradeSell(){

        drawInventory(gp.player,true);
        int x;
        int y;
        int width ;
        int height ;
        int price=0;

        //İpuçları
        x=gp.tileSize*2;
        y=gp.tileSize*9;
        width =gp.tileSize*6;
        height =gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        drawOutlinedText("[ESC]=Geri",x+72,y+35,effraBold,1,20);

        //player para penceresi
        x=gp.tileSize*13;
        y=gp.tileSize*9;
        width=gp.tileSize*6;
        height=gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        drawOutlinedText("Mangır Durumu: "+gp.player.coin,x+112,y+35,effraBold,1,20);

        //ürün fiyat penceresi
        int itemIndex= getItemIdx(playerSlotCol,playerSlotRow);
        if(itemIndex<gp.player.inventory.size()){

            x=(int)(gp.tileSize*15.5);
            y=(int)(gp.tileSize*5.5);
            width=(int)(gp.tileSize*2.5);
            height=gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10,y+7,32,32,null);

            price=(int)(gp.player.inventory.get(itemIndex).price/2);
            drawOutlinedText("= "+price,x+70,y+25,effraBold,1,20);
        }
        //Sat
        if (gp.keyH.enterPressed==true&&itemIndex<gp.player.inventory.size()){
            if (gp.player.inventory.get(itemIndex)==gp.player.currentWeapon||
                    gp.player.inventory.get(itemIndex)==gp.player.currentShield){
                commandNum=0;
                subState=0;
                gp.gameState=gp.dialogueState;
                currentDialogue="Kullanımdaki malzemeyi satamazsın babo OK.";
            }else {
                if (gp.player.inventory.get(itemIndex).amount>1){
                    gp.player.inventory.get(itemIndex).amount--;

                }
                else { gp.player.inventory.remove(itemIndex);
                    }

                gp.player.coin+=price;
                gp.playSE(18);
            }

            }
        }
    public void drawPlayerLife(){
        int x=gp.tileSize/2;
        int y=gp.tileSize/2;
        int i=0;
        //TÜM CAN ÖLÇEĞİ ÇİZİMİ
        while (i<gp.player.maxLife/2){
            g2.drawImage(elmaBos,x,y,null);
            i++;
            x+=32;
        }
        x=gp.tileSize/2;
        y=gp.tileSize/2;
        i=0;
        while (i<gp.player.life){
            g2.drawImage(elmaYarim,x,y,null);
            i++;
            if (i<gp.player.life){
                g2.drawImage(elmaFull,x,y,null);
            }
            i++;
            x+=32;
        }

        //MANA BAR ÇİZİMİ
        x=(gp.tileSize/2);
        y=(gp.tileSize+10);
        i=0;
        while (i<gp.player.maxMana){
            g2.drawImage(bardakBoş,x,y,null);
            i++;
            x+=32;
        }
        x=(gp.tileSize/2);
        y=(gp.tileSize+10);
        i=0;
        while (i<gp.player.mana){
            g2.drawImage(bardakFull,x,y,null);
            i++;
            x+=32;
        }

    }
    public void drawMessage(){
        int messageX=gp.tileSize*18;
        int messageY=gp.tileSize*4;

        for (int i=0; i<message.size(); i++){
            if (message.get(i)!= null){
            drawOutlinedText(message.get(i),messageX, messageY,effraBold,1,20);
            int counter= messageCounter.get(i)+1;
            messageCounter.set(i,counter);
            messageY+=24;
            if (messageCounter.get(i)>=180){
                message.remove(i);
                messageCounter.remove(i);
            }

            }

        }

    }

    public void drawTitleScreen(){
        g2.setColor(Color.lightGray);
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/visuals/sample.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        String yenioyun, kayıtlıOyun, otoban;
        yenioyun=null;kayıtlıOyun=null; otoban= null;
        switch (commandNum){
            case 0:  yenioyun="->     YENİ OYUN   "; kayıtlıOyun ="KAYITLI OYUN"; otoban= "OTOBAN";
                break;
            case 1:  yenioyun="YENİ OYUN"; kayıtlıOyun ="->     KAYITLI OYUN   "; otoban= "OTOBAN";
                break;
            case 2:  yenioyun="YENİ OYUN"; kayıtlıOyun ="KAYITLI OYUN"; otoban= "->     OTOBAN   ";
                break;
        }

        g2.drawImage(image, 0,0, gp.screenWidth,gp.screenHeight,null);

        drawOutlinedTextWithShadow("FERİŞTAH KİNG LEGACY",gp.screenWidth/2, 400,arial_50B,3, 40,Color.YELLOW,Color.RED,7,7);
        drawOutlinedTextWithShadow(yenioyun, gp.screenWidth/2, 450,effraBold,2, 25,Color.YELLOW,Color.RED,5,5);
        drawOutlinedTextWithShadow(kayıtlıOyun, gp.screenWidth/2, 490,effraBold,2, 25,Color.YELLOW,Color.RED,5,5);
        drawOutlinedTextWithShadow(otoban, gp.screenWidth/2, 530,effraBold,2, 25,Color.YELLOW,Color.RED,5,5);
    }

    public void drawOutlinedTextWithShadow(String text, int x, int y, Font font, int outlineWidth, int fontSize, Color textColor, Color outLineColor, int shadowOffsetX, int shadowOffsetY) {
        // Set the font size
        Font largerFont = font.deriveFont((float) fontSize);

        g2.setFont(largerFont);
        FontMetrics metrics = g2.getFontMetrics(largerFont);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        // Calculate adjusted position for better alignment
        int adjustedX = x - textWidth / 2;
        int adjustedY = y + textHeight / 4;

        // Draw shadow
        Color shadowColor = new Color(0,0,0,100);
        g2.setColor(shadowColor);
        g2.drawString(text, adjustedX + shadowOffsetX, adjustedY + shadowOffsetY);

        // Draw outline
        g2.setColor(outLineColor); // Set outline color to chosen color
        for (int dx = -outlineWidth; dx <= outlineWidth; dx++) {
            for (int dy = -outlineWidth; dy <= outlineWidth; dy++) {
                if (dx != 0 || dy != 0) {
                    g2.drawString(text, adjustedX + dx, adjustedY + dy);
                }
            }
        }

        // Draw text
        g2.setColor(textColor); // Set text color
        g2.drawString(text, adjustedX, adjustedY);
    }
    public void drawOutlinedText(String text, int x, int y, Font font, int outlineWidth, int fontSize) {
        // Set the font size
        Font largerFont = font.deriveFont((float) fontSize);

        g2.setFont(largerFont);
        FontMetrics metrics = g2.getFontMetrics(largerFont);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        // Calculate adjusted position for better alignment
        int adjustedX = x - textWidth / 2;
        int adjustedY = y + textHeight / 4;

        g2.setColor(Color.RED); // Set outline color to red
        for (int dx = -outlineWidth; dx <= outlineWidth; dx++) {
            for (int dy = -outlineWidth; dy <= outlineWidth; dy++) {
                if (dx != 0 || dy != 0) {
                    g2.drawString(text, adjustedX + dx, adjustedY + dy);
                }
            }
        }

        g2.setColor(Color.YELLOW); // Set text color to yellow
        g2.drawString(text, adjustedX, adjustedY);
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c =new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c= new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);


    }
    public void drawPauseScreen(){
        String text= "Bekle Babo";
        drawOutlinedText(text, gp.screenWidth/2, gp.screenHeight/2,arial_50B,5,50 );

    }
    public int getXforCenteredText(String text){
        int x;
        int textlength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
                x=gp.screenWidth/2-textlength/2;
        return x;
    }
}
