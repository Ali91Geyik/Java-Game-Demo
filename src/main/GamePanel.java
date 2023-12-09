package main;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import entity.Projectile;
import entity.video.GameOver;
import entity.video.LwlUp;
import entity.video.SalonGiris;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class GamePanel extends JPanel implements Runnable , MouseMotionListener {
    //VİDEOS
    SalonGiris salonGiris= new SalonGiris(this);
    GameOver gameOver= new GameOver(this);
    LwlUp lwlUp= new LwlUp(this);
    public PathFinder pFinder = new PathFinder(this);

    //SCREEN SETTİNG
    public final int originalTileSize = 32; //32x32 px tile
    public final double scale = 1.5;
    public final int tileSize = (int)(scale*originalTileSize) ;
    public final int maxScreenCol =20;
    public final int maxScreenRow=12;
    public final int screenWidth= tileSize*maxScreenCol;
    public final int screenHeight = tileSize*maxScreenRow;
    public int mouseX=0;
    public int mouseY=0;
    //WORLD SETTİNGS
    public final int maxMap=10;
    public int currentMap=0;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FOR FULLSCREEN
    int screenwidth2= screenWidth;
    int screenHeight2= screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn=true;
    int FPS=60;


    //GAMESTATE
    public int gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;
    public final int characterState=4;
    public final int optionsState=5;
    public final int gameOverState=6;
    public final int cutSceneState=7;
    public final int tradeState=8;

    private boolean isMusicStopped=false;



    //SYSTEM
    public UI ui = new UI(this);
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public MouseHandler mouseHandler= new MouseHandler(this);
    Sound se = new Sound();
    Sound music = new Sound();
    Sound pauseMenuMusic = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);

    Thread gameThread;// oyunda paralel zaman ayarı buradan olacak
    public MusicThread musicThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH, mouseHandler);
    public Entity obj[][]= new Entity[maxMap][100];
    public Entity npc[][]= new Entity[maxMap][100];
    public Entity monster[][]= new Entity[maxMap][100];
    public Entity hareketli[][]= new Entity[maxMap][100];

    public Entity entity= new Entity(this);
    public ArrayList<Entity> entityList= new ArrayList<>();
    public ArrayList<Projectile> projectileList= new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();


    public GamePanel (){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        this.addMouseMotionListener(this);

    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setHareketli();

        player.setDefaultPositions();//BUNU BURDAN KALDIR
        gameState=titleState;
        tempScreen= new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();
        if (fullScreenOn==true){
        setFullScreen();}
        playMusic(9);
    }
    public void retry() {
    player.setDefaultPositions();
    player.restoreLifeMana();
    aSetter.setNPC();
    aSetter.setMonster();
    aSetter.setHareketli();
    playMusic(0);
    }
    public void restart(){
        player.setDefaultPositions();
        player.restoreLifeMana();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setHareketli();
    }

    public void setFullScreen(){
        //cihaz ayarlarını edinme kısmı
        GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);
        screenwidth2=Main.window.getWidth();
        screenHeight2=Main.window.getHeight();

    }
    public void startGameThread(){
        gameThread= new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawTimeInterval = 1000000000/FPS;
        double delta =0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;

    while (gameThread!=null){                   //Bu döngü içerisinde draw ve update seri biçimde gerçekleşecek
        currentTime= System.nanoTime();

        delta+= (currentTime-lastTime)/drawTimeInterval;
        timer+= currentTime-lastTime;
        lastTime=currentTime;

        if (delta>=1){
            update();                                       //UpDate
            drawToTempScreen();    //buffered ımage çizimi
            drawToScreen();        //screen image cizimi
            delta--;
            drawCount++;
        }
        if(timer>=1000000000){
            System.out.println("FPS: "+drawCount);
            drawCount=0;
            timer=0;
        }
    }
    }
    public void update(){               //Update işlemini yapacak
    if (gameState==playState){
        player.update();
        for (int i=0; i<npc[1].length; i++){
            if(npc[currentMap][i]!=null){
                npc[currentMap][i].update();
            }
        }
        for (int i =0; i<monster[1].length; i++){
            if (monster[currentMap][i] !=null){
                if (monster[currentMap][i].alive==true && monster[currentMap][i].dying==false){
                monster[currentMap][i].update();}
            if (monster[currentMap][i].alive==false){
                monster[currentMap][i].checkDrop();
                monster[currentMap][i] = null;
            }}
        }
        for (int i =0; i<hareketli[1].length; i++){
            if (hareketli[currentMap][i]!=null){
                    hareketli[currentMap][i].update();}}

        for (int i =0; i< projectileList.size(); i++){
            if (projectileList.get(i) !=null){
                if (projectileList.get(i).alive==true){
                    projectileList.get(i).update();}
                if (projectileList.get(i).alive==false){
                    projectileList.remove(i);
                }}
        }
        for (int i =0; i< particleList.size(); i++){
            if (particleList.get(i) !=null){
                if (particleList.get(i).alive==true){
                    particleList.get(i).update();}
                if (particleList.get(i).alive==false){
                    particleList.remove(i);
                }}
        }

        if (isMusicStopped==true){
        music.resume();
        isMusicStopped=false;
        }                    }
    if (gameState==pauseState){
        music.pause();
        isMusicStopped=true;
    }
    if(gameState==dialogueState&& player.lwlUp==true){
        lwlUp.update();
    }
        if(gameState==dialogueState&& eHandler.salongiris==true){
            salonGiris.update();

        }
        if (gameState == pauseState && musicThread == null) {
            musicThread = new MusicThread(this);
            musicThread.start();
        } else if (gameState != pauseState && musicThread != null) {
            musicThread.stopMusicThread();
            musicThread = null;
        }
        if (gameState==gameOverState){
            gameOver.update();
        }


    }

    public void drawToTempScreen(){
        //TİTLE SCREEN
        if (gameState==titleState){
            ui.draw(g2);
        }

        else {
            //TILE
            tileM.draw(g2);


            //ADD Entities to the list
            entityList.add(player);
            for (int i=0; i<npc[1].length;i++){
                if (npc[currentMap][i]!=null){
                    entityList.add(npc[currentMap][i]);}
            }
            for (int i =0; i<obj[1].length;i++){
                if (obj[currentMap][i]!=null){
                    entityList.add(obj[currentMap][i]);}
            }
            for (int i =0; i<hareketli[1].length;i++){
                if (hareketli[currentMap][i]!=null){
                    entityList.add(hareketli[currentMap][i]);}
            }
            for (int i=0; i<monster[1].length;i++){
                if (monster[currentMap][i]!=null){
                    entityList.add(monster[currentMap][i]);}
            }
            for (int i=0; i< projectileList.size();i++){
                if (projectileList.get(i)!=null){
                    entityList.add(projectileList.get(i));}
            }
            for (int i=0; i< particleList.size();i++){
                if (particleList.get(i)!=null){
                    entityList.add(particleList.get(i));}
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result=Integer.compare(e1.worldY,e2.worldY);
                    return result;
                }
            });
            for (int i=0; i<entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            //EMpty entity list
            entityList.clear();

            //UI
            ui.draw(g2);}


    }
    public void drawToScreen(){
        Graphics g =getGraphics();
        g.drawImage(tempScreen,0,0,screenwidth2,screenHeight2,null);
        g.dispose();

    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
    public void stopSE(){
        se.stop();
    }
    public void playPauseMenuMusic(int i){
        pauseMenuMusic.setFile(i);
        pauseMenuMusic.play();
    }
    public void stopPauseMenuMusic(int i){
        pauseMenuMusic.stop();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    mouseX=e.getX();
    mouseY=e.getY();
    }
}
