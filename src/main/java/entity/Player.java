package entity;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import object.OBJ_Bardak;
import object.OBJ_CanavarElma;
import object.OBJ_CarKey;
import object.projectiles.OBJ_Dumbell10kg;
import object.projectiles.OBJ_Slime;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class Player extends Entity{
    //GamePanel gp;                           //Gamepanel ve Keyhandler'ı buraya çekiyoruz ki tüm bunlarla olan işlerimizi(edit2) sonradan entitiy'e gamepanel atadık, buna gerek kalmadı
    KeyHandler keyH;                        // ana classlara taşıyıp kalabalık yapmayalım, çünkü daha diğer entitylerin işleri var
    MouseHandler mouseHandler;
    public final int screenX;
    public final int screenY;
    public boolean lwlUp=false;
    public boolean attackCancelled=false;

    private long lastSoundEffectTime =0;
    public int yumrukSeviyesi=1;



    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseHandler) {
        super(gp);
        this.keyH = keyH;
        this.mouseHandler=mouseHandler;

        screenX=(gp.screenWidth/2)-gp.tileSize/2;
        screenY=(gp.screenHeight/2)-gp.tileSize/2;

        // collision area for player
        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.height=32;
        solidArea.width=32;
        width=gp.tileSize;
        height=gp.tileSize;
        attackArea.width=48;
        attackArea.height=48;


        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultValues(){
        worldX=gp.tileSize*15;
        worldY=gp.tileSize*28;

        direction= "down";
        //PLAYER STATUS
        defaultSpeed=4;
        speed=defaultSpeed;
        level=1;
        maxLife=6;
        life=maxLife;
        maxMana=4;
        mana=maxMana;
        ammo=0;
        strength=1;
        dexterity=1;
        exp=0;
        nextLevelExp=25;
        coin=100;
        type=type_player;
        currentWeapon =null ;
        currentShield =null ;
        projectile= new OBJ_Slime(gp);
        projectile2= new OBJ_Dumbell10kg(gp);
        attack= getAttack();
        defence = getDefence();
    }
    public void setDefaultPositions(){
    worldX=gp.tileSize*18;
    worldY=gp.tileSize*18;
    direction="down";
    gp.currentMap=1;
    }
    public void restoreLifeMana(){
    life=maxLife;
    mana=maxMana;
    invincible=false;
    }
    public void setItems(){

        inventory.clear();
        inventory.add(new OBJ_CanavarElma(gp));
        inventory.add(new OBJ_Bardak(gp));
        inventory.add(new OBJ_CarKey(gp));
        inventory.add(new OBJ_CarKey(gp));
        inventory.add(new OBJ_CarKey(gp));
        inventory.add(new OBJ_CarKey(gp));
        inventory.add(new OBJ_CarKey(gp));

    }
    public int getAttack(){
        if (currentWeapon!=null){
            attackArea=currentWeapon.attackArea;
            return attack=strength*currentWeapon.attackValue;
        }
        else{
            return attack=strength*yumrukSeviyesi;
        }

    }
    public int getDefence(){
        if (currentShield!=null){
        return defence=dexterity* currentShield.defenceValue;}
        else {
            return  defence=dexterity;
        }
    }
    public void getPlayerImage(){

            up1=setup("/player/seyis_up_1",width,height);
            up2=setup("/player/seyis_up_2",width,height);
            up3=setup("/player/seyis_up_3",width,height);
            up4=setup("/player/seyis_up_4",width,height);
            up5=setup("/player/seyis_up_5",width,height);
            up6=setup("/player/seyis_up_6",width,height);
            up7=setup("/player/seyis_up_7",width,height);
            up8=setup("/player/seyis_up_8",width,height);


            down1 = setup("/player/seyis_down_1",width,height);
            down2 = setup("/player/seyis_down_2",width,height);
            down3 = setup("/player/seyis_down_3",width,height);
            down4 = setup("/player/seyis_down_4",width,height);
            down5 = setup("/player/seyis_down_5",width,height);
            down6 = setup("/player/seyis_down_6",width,height);
            down7 = setup("/player/seyis_down_7",width,height);
            down8 = setup("/player/seyis_down_8",width,height);


            left1 = setup("/player/seyis_left_1",width,height);
            left2 = setup("/player/seyis_left_2",width,height);
            left3 = setup("/player/seyis_left_3",width,height);
            left4 = setup("/player/seyis_left_4",width,height);
            left5 = setup("/player/seyis_left_5",width,height);


            right1= setup("/player/seyis_right_1",width,height);
            right2= setup("/player/seyis_right_2",width,height);
            right3= setup("/player/seyis_right_3",width,height);
            right4= setup("/player/seyis_right_4",width,height);
            right5= setup("/player/seyis_right_5",width,height);
            avatar1=setup("/player/avatarScared",width,height);
            avatar2=setup("/player/avatarHappy",width,height);

    }
    public void getPlayerAttackImage(){
        meleeAttackdown = setup1(new BufferedImage[26],"/playerAttack/melee1/zbarAttack_down",96,168);
        meleeAttackup= setup1(new BufferedImage[26],"/playerAttack/melee1/zbarAttack_up",96,168 );
        meleeAttackleft= setup1(new BufferedImage[26],"/playerAttack/melee1/zbarAttack_left",168,96 );
        meleeAttackright= setup1(new BufferedImage[26],"/playerAttack/melee1/zbarAttack_right",168,96 );


        attackdown1= setup("/playerAttack/Kademe1Yumruk_1_down1", 72,72);
        attackdown2= setup("/playerAttack/Kademe1Yumruk_1_down2", 72,72);
        attackdown3= setup("/playerAttack/Kademe1Yumruk_1_down3", 72,72);
        attackdown4= setup("/playerAttack/Kademe1Yumruk_1_down4", 72,72);
        attackdown5= setup("/playerAttack/Kademe1Yumruk_1_down5", 72,72);
        attackdown6= setup("/playerAttack/Kademe1Yumruk_1_down6", 72,72);

        attackup1= setup("/playerAttack/Kademe1Yumruk_1_up1", 72,72);
        attackup2= setup("/playerAttack/Kademe1Yumruk_1_up2", 72,72);
        attackup3= setup("/playerAttack/Kademe1Yumruk_1_up3", 72,72);
        attackup4= setup("/playerAttack/Kademe1Yumruk_1_up4", 72,72);
        attackup5= setup("/playerAttack/Kademe1Yumruk_1_up5", 72,72);
        attackup6= setup("/playerAttack/Kademe1Yumruk_1_up6", 72,72);

        attackleft1= setup("/playerAttack/Kademe1Yumruk_1_left1", 72,72);
        attackleft2= setup("/playerAttack/Kademe1Yumruk_1_left2", 72,72);
        attackleft3= setup("/playerAttack/Kademe1Yumruk_1_left3", 72,72);
        attackleft4= setup("/playerAttack/Kademe1Yumruk_1_left4", 72,72);
        attackleft5= setup("/playerAttack/Kademe1Yumruk_1_left5", 72,72);
        attackleft6= setup("/playerAttack/Kademe1Yumruk_1_left6", 72,72);

        attackright1= setup("/playerAttack/Kademe1Yumruk_1_right1", 72,72);
        attackright2= setup("/playerAttack/Kademe1Yumruk_1_right2", 72,72);
        attackright3= setup("/playerAttack/Kademe1Yumruk_1_right3", 72,72);
        attackright4= setup("/playerAttack/Kademe1Yumruk_1_right4", 72,72);
        attackright5= setup("/playerAttack/Kademe1Yumruk_1_right5", 72,72);
        attackright6= setup("/playerAttack/Kademe1Yumruk_1_right6", 72,72);






    }

    public void update() {

        direction= mouseHandler.calculateDirection(gp.mouseX,gp.mouseY,screenX,screenY);
        System.out.println(direction);
        if (attacking == true && currentWeapon == null) {
            attacking();
        } else if (attacking == true && currentWeapon != null) {
            attackingMelee();
        } else if (keyH.leftPressed == true || keyH.downPressed == true || keyH.upPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {



           /* if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.rightPressed == true) {
                direction = "right";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            }*/
            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex, gp.obj);

            //check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            //check hareketli collision
            int hareketliIndex = gp.cChecker.checkEntity(this, gp.hareketli);
            if (hareketliIndex <= gp.hareketli[1].length && gp.hareketli[gp.currentMap][hareketliIndex].type != type_visual) {
                pickUpObject(hareketliIndex, gp.hareketli);
            }


            //check Monster Collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            //check Interactive tile collision


            //check event
            gp.eHandler.checkEvent();


            //IF COLLISION IS FALSE PLAYER CAN MOVE
            if (collisionOn == false && keyH.enterPressed == false) {
                moveCoordinator();
            }
            if (keyH.enterPressed == true && attackCancelled == false) {
                gp.playSE(14);
                attacking = true;
                spriteCounter = 0;
            }
            attackCancelled = false;
            spriteCounter3++;
            spriteCounter2++;
            spriteCounter++;
            animationSpriteChanger();
            if (spriteCounter > 4) {
                switch (spriteNum) {
                    case 1:
                        spriteNum = 2;
                        spriteCounter = 0;
                        break;
                    case 2:
                        spriteNum = 3;
                        spriteCounter = 0;
                        break;
                    case 3:
                        spriteNum = 4;
                        spriteCounter = 0;
                        break;
                    case 4:
                        spriteNum = 5;
                        spriteCounter = 0;
                        break;
                    case 5:
                        spriteNum = 6;
                        spriteCounter = 0;
                        break;
                    case 6:
                        spriteNum = 7;
                        spriteCounter = 0;
                        break;
                    case 7:
                        spriteNum = 8;
                        spriteCounter = 0;
                        break;
                    case 8:
                        spriteNum = 1;
                        spriteCounter = 0;
                        break;
                }
            }
            if (spriteCounter2 > 5) {
                switch (spriteNum2) {
                    case 1:
                        spriteNum2 = 2;
                        spriteCounter2 = 0;
                        break;
                    case 2:
                        spriteNum2 = 3;
                        spriteCounter2 = 0;
                        break;
                    case 3:
                        spriteNum2 = 4;
                        spriteCounter2 = 0;
                        break;
                    case 4:
                        spriteNum2 = 1;
                        spriteCounter2 = 0;
                        break;
                }
            }

        }
        //TÜKÜRÜK FULL SALDIRISI
        if (gp.keyH.shotKeyPressed == true && shotAvailableCounter >= 60 && projectile.haveResourceMethod(this) == true) {
            //SET DEFAULT COORDİNATES, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);
            //SUBTRACT THE COST
            projectile.subtractResource(this);
            //ADD IT TO THE LIST
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
            gp.stopSE();
            gp.playSE(27);
        }
        //PROJECTİLE FULL SALDIRI
        if (gp.keyH.projectileKeyPressed == true && projectileAvailableCounter >= 60 && projectile2.haveResourceMethod(this) == true) {
            //SET DEFAULT COORDİNATES, DIRECTION AND USER
            projectile2.set(worldX, worldY, direction, true, this);
            //SUBTRACT THE COST
            projectile2.subtractResource(this);
            //ADD IT TO THE LIST
            gp.projectileList.add(projectile2);
            projectileAvailableCounter = 0;
            gp.stopSE();
            gp.playSE(29);
        }
        if (shotAvailableCounter < 60) {
            shotAvailableCounter += dexterity;
        }
        if (projectileAvailableCounter < 60) {
            projectileAvailableCounter += dexterity;
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter >= 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (life > maxLife) {
            life = maxLife;
        }
        if (mana > maxMana) {
            mana = maxMana;
        }
        if (life<=0){
        gp.gameState=gp.gameOverState;
        gp.ui.commandNum=-1;
        gp.stopMusic();
        changeableSoundEffects=new int[]{33,34,35,36,37,38,39,40,41,42,43,44};
        gp.playSE(soundEffectChanger(12,changeableSoundEffects));
        }

    }
    public void attacking(){
        spriteCounter++;
        if (spriteCounter<4){spriteNum=1;}
        if (spriteCounter>=4&& spriteCounter<8){spriteNum=2;}
        if (spriteCounter>=8&& spriteCounter<12){spriteNum=3;}
        if (spriteCounter>=12&& spriteCounter<16){spriteNum=4;}
        if (spriteCounter>=16&& spriteCounter<20){spriteNum=5;}
        if (spriteCounter>=20&& spriteCounter<30){spriteNum=6;}
        if (spriteCounter>=30)
        {spriteNum=1; spriteCounter=0; attacking=false; }

        //attack için düzenlenecek değerler kaydediliyor

        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;
        //attack için düzenlenen değerler
        switch (direction){
            case "up": worldY-=attackArea.height; break;
            case "down": worldY+=attackArea.height; break;
            case "left": worldX-=attackArea.width; break;
            case "right": worldX+=attackArea.width; break;
        }
        //attackArea Becomes Solid Area
        solidArea.width= attackArea.width;
        solidArea.height= attackArea.height;
        //check monster collision with updated worldx-y
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        damageMonster(monsterIndex,this,attack);
        //check interactive tile collision for breaking stuff
        int hareketliIndex=gp.cChecker.checkEntity(this,gp.hareketli);
        if (hareketliIndex<=gp.hareketli[1].length&& gp.hareketli[gp.currentMap][hareketliIndex].type==type_breakable){
            damageObject(hareketliIndex);}


        //değerleri eski haline getirme
        worldX=currentWorldX;
        worldY=currentWorldY;
        solidArea.width= solidAreaWidth;
        solidArea.height =solidAreaHeight;
    }
    public void pickUpObject(int i, Entity[][] target){
    if (i!=999 && target[gp.currentMap][i].type!=type_structure&&target[gp.currentMap][i].type!=type_breakable){
        if (target[gp.currentMap][i].type==type_PickUpOnly){
            target[gp.currentMap][i].use(this);
            target[gp.currentMap][i]=null;
        } else if (target[gp.currentMap][i].type==type_interactive) {
            if (keyH.enterPressed==true){
                attackCancelled=true;
                target[gp.currentMap][i].interact();
            }
            
        } else {
        String text="";
        if (canObtainItem(target[gp.currentMap][i])==true){
            gp.playSE(18);
            text=target[gp.currentMap][i].name+" alındı.";
            target[gp.currentMap][i]= null;
        }else if(inventory.size()==maxInventorySize) {
            text="yer yok babo götüne mi sokucan?";
        }
        gp.ui.addMessage(text);
    }
    }
    }
    public void interactNPC(int i){

        if (gp.keyH.enterPressed==true){
            if (i!=999){
                attackCancelled=true;
            gp.gameState = gp.dialogueState;
            gp.npc[gp.currentMap][i].speak();}

            }
        }
    public void contactMonster(int i){
        if (i!=999){

            if (invincible==false&&attacking==false&&gp.monster[gp.currentMap][i].dying==false){
                changeableSoundEffects = new int[]{11,12,13};
                gp.playSE(soundEffectChanger(3, changeableSoundEffects));
                int damage= gp.monster[gp.currentMap][i].attack-defence;
                if (damage<=0){damage=1;}
            life-=damage;
            invincible=true;
            }
        }
    }
    public void damageMonster(int i, Entity attacker, int attack){
        if (i!=999){
            if (gp.monster[gp.currentMap][i].invincible==false){
                setKnockBack(gp.monster[gp.currentMap][i], attacker);
                damageReaction();
                int damage= attack-gp.monster[gp.currentMap][i].defence;
                if (damage<=0){
                    damage=1;
                }
                gp.monster[gp.currentMap][i].life-=damage;
                generateParticle(gp.monster[gp.currentMap][i]);
                gp.ui.addMessage(damage+" birim hasar verildi.");
                gp.monster[gp.currentMap][i].invincible=true;
                if (gp.monster[gp.currentMap][i].life<=0){
                    gp.monster[gp.currentMap][i].dying=true;
                    gp.ui.addMessage(gp.monster[gp.currentMap][i].name+" otobana atıldı!");
                    exp+=gp.monster[gp.currentMap][i].exp;
                    gp.ui.addMessage(gp.monster[gp.currentMap][i].exp+" ilim alındı!");
                    checkLevelUp();
                }
            }
        }
    }
    public void damageObject(int i){
        if (i!=999 && gp.hareketli[gp.currentMap][i].destructable==true&&gp.hareketli[gp.currentMap][i].invincible==false){
            gp.hareketli[gp.currentMap][i].life--;
            gp.ui.addMessage(gp.hareketli[gp.currentMap][i].life+" can kaldı.");
            gp.hareketli[gp.currentMap][i].invincible=true;
            //GENERATE PARTİCLE
            generateParticle(gp.hareketli[gp.currentMap][i]);

            if (gp.hareketli[gp.currentMap][i].life<=0){
                gp.playSE(32);
                gp.hareketli[gp.currentMap][i]=null;
            }
        }
    }
    public void checkLevelUp(){
        if (exp>= nextLevelExp){
            lwlUp=true;
            level++;
            nextLevelExp=nextLevelExp*2;
            maxLife+=2;
            strength++;
            dexterity++;
            attack= getAttack();
            defence= getDefence();
            gp.playSE(15);
            gp.gameState=gp.cutSceneState;
            gp.ui.avatar=gp.player.avatar2;
            gp.ui.currentDialogue="İlimde çağ atladın babo!!";

        }
    }
    public void selectItem(){
        int itemIndex= gp.ui.getItemIdx(gp.ui.playerSlotCol,gp.ui.playerSlotRow);
        if (itemIndex<inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type==type_melee && selectedItem==currentWeapon){
                currentWeapon=null;
                attack=getAttack();
                getPlayerAttackImage();
            }else
            if (selectedItem.type==type_melee){
                gp.player.changeableSoundEffects= new int[]{19,20,21,22,23,24,25};
                gp.playSE(gp.player.soundEffectChanger(7, gp.player.changeableSoundEffects));
                currentWeapon=selectedItem;
                attack= getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type==type_shield&&selectedItem==currentShield){
                currentShield=null;
                defence=getDefence();
            }
            else if (selectedItem.type==type_shield){
                gp.player.changeableSoundEffects= new int[]{19,20,21,22,23,24,25};
                gp.playSE(gp.player.soundEffectChanger(7, gp.player.changeableSoundEffects));
                currentShield=selectedItem;
                defence= getDefence();
            }
            if (selectedItem.type==type_consumable|| selectedItem.type==type_projectile){
                if (selectedItem.use(this)==true){
                    if (selectedItem.amount>1){
                        selectedItem.amount--;
                    }
                    else {
                inventory.remove(itemIndex);

                    }}

            }

        }
    }
    public int searchItemInventory(String itemName){
    int itemIndex=999;
    for (int i=0; i<inventory.size();i++){
        if (inventory.get(i).name.equals(itemName)){
            itemIndex=i;
            break;
        }

    }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item){
        boolean canObtain=false;
        if(item.stackable==true){
            int index = searchItemInventory(item.name);
            if (index!=999){
                inventory.get(index).amount++;
                canObtain=true;
            }
            else {
                if (inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else {
            if (inventory.size() != maxInventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
    }
    public void draw(Graphics2D g2){
        // g2.setColor(Color.WHITE);                           g2'ye çizim yaptırıyoruz
        // g2.fillRect(x   ,y,gp.tileSize,gp.tileSize);         dörtgen çizdirip konum ve boyut belirtiyoruz
        BufferedImage image=null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction){
            case "up":
                if (attacking==false){
                if (spriteNum==1){
                image=up1;}
                if (spriteNum==2){
                    image=up2;}
                if (spriteNum==3){
                    image=up3;}
                if (spriteNum==4){
                    image=up4;}
                if (spriteNum==5){
                    image=up5;}
                if (spriteNum==6){
                    image=up6;}
                if (spriteNum==7){
                    image=up7;}
                if (spriteNum==8){
                    image=up8;}}
                if (attacking==true &&currentWeapon==null){
                    tempScreenY=screenY-30;
                    tempScreenX=screenX-7;
                    if (spriteNum==1){
                        image=attackup1;}
                    if (spriteNum==2){
                        image=attackup2;}
                    if (spriteNum==3){
                        image=attackup3;}
                    if (spriteNum==4){
                        image=attackup4;}
                    if (spriteNum==5){
                        image=attackup5;}
                    if (spriteNum==6){
                        image=attackup6;}
                }
                else if (attacking==true&&currentWeapon!=null) {
                    tempScreenX=screenX-30;
                    tempScreenY=screenY-68;
                   image=(BufferedImage)animationImageSetter(meleeAttackup);
                }
                break;
            case "down":
                if (attacking==false){
                if (spriteNum==1){
                    image=down1;}
                if (spriteNum==2){
                    image=down2;}
                if (spriteNum==3){
                    image=down3;}
                if (spriteNum==4){
                    image=down4;}
                if (spriteNum==5){
                    image=down5;}
                if (spriteNum==6){
                    image=down6;}
                if (spriteNum==7){
                    image=down7;}
                if (spriteNum==8){
                    image=down8;}}
                if (attacking==true&&currentWeapon==null){
                    if (spriteNum==1){
                        image=attackdown1;}
                    if (spriteNum==2){
                        image=attackdown2;}
                    if (spriteNum==3){
                        image=attackdown3;}
                    if (spriteNum==4){
                        image=attackdown4;}
                    if (spriteNum==5){
                        image=attackdown5;}
                    if (spriteNum==6){
                        image=attackdown6;}
                }else if (attacking==true&&currentWeapon!=null) {
                    tempScreenX=screenX-25;
                    tempScreenY=screenY-48;
                   image=(BufferedImage)animationImageSetter(meleeAttackdown);
                }

                break;
            case "left":
                if (attacking==false){
                if (spriteNum2==1){
                    image=left1;}
                if (spriteNum2==2){
                    image=left2;}
                if (spriteNum2==3){
                    image=left3;}
                if (spriteNum2==4){
                    image=left4;}}
                if (attacking==true&&currentWeapon==null){
                    tempScreenY=screenY-6;
                    tempScreenX=screenX-40;

                    if (spriteNum==1){
                        image=attackleft1;}
                    if (spriteNum==2){
                        image=attackleft2;}
                    if (spriteNum==3){
                        image=attackleft3;}
                    if (spriteNum==4){
                        image=attackleft4;}
                    if (spriteNum==5){
                        image=attackleft5;}
                    if (spriteNum==6){
                        image=attackleft6;}
                }else if (attacking==true&&currentWeapon!=null) {
                    tempScreenX=screenX-87;
                    tempScreenY=screenY-29;
                   image= (BufferedImage) animationImageSetter(meleeAttackleft);
                }
                break;
            case "right":
                if (attacking==false){
                if (spriteNum2==1){
                    image=right1;}
                if (spriteNum2==2){
                    image=right2;}
                if (spriteNum2==3){
                    image=right3;}
                if (spriteNum2==4){
                    image=right4;}}
                if (attacking==true&&currentWeapon==null){
                    tempScreenY=screenY-6;
                    if (spriteNum==1){
                        image=attackright1;}
                    if (spriteNum==2){
                        image=attackright2;}
                    if (spriteNum==3){
                        image=attackright3;}
                    if (spriteNum==4){
                        image=attackright4;}
                    if (spriteNum==5){
                        image=attackright5;}
                    if (spriteNum==6){
                        image=attackright6;}
                } else if (attacking==true&&currentWeapon!=null) {
                    tempScreenX=screenX-29;
                    tempScreenY=screenY-32;
                   image=(BufferedImage)animationImageSetter(meleeAttackright);
                }
                break;
        }
        if (invincible==true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2.drawImage(image,tempScreenX,tempScreenY,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

    }
    public void animationSpriteChanger(){
        if (spriteCounter3 > 3) {
            switch (spriteNum3) {
                case 1:
                    spriteNum3 = 2;
                    spriteCounter3 = 0;
                    break;
                case 2:
                    spriteNum3 = 3;
                    spriteCounter3 = 0;
                    break;
                case 3:
                    spriteNum3 = 4;
                    spriteCounter3 = 0;
                    break;
                case 4:
                    spriteNum3 = 5;
                    spriteCounter3 = 0;
                    break;
                case 5:
                    spriteNum3 = 6;
                    spriteCounter3 = 0;
                    break;
                case 6:
                    spriteNum3 = 7;
                    spriteCounter3 = 0;
                    break;
                case 7:
                    spriteNum3 = 8;
                    spriteCounter3 = 0;
                    break;
                case 8:
                    spriteNum3 = 9;
                    spriteCounter3 = 0;
                    break;
                case 9:
                    spriteNum3 = 10;
                    spriteCounter3 = 0;
                    break;
                case 10:
                    spriteNum3 = 11;
                    spriteCounter3 = 0;
                    break;
                case 11:
                    spriteNum3 = 12;
                    spriteCounter3 = 0;
                    break;
                case 12:
                    spriteNum3 = 13;
                    spriteCounter3 = 0;
                    break;
                case 13:
                    spriteNum3 = 14;
                    spriteCounter3 = 0;
                    break;
                case 14:
                    spriteNum3 = 15;
                    spriteCounter3 = 0;
                    break;
                case 15:
                    spriteNum3 = 16;
                    spriteCounter3 = 0;
                    break;
                case 16:
                    spriteNum3 = 17;
                    spriteCounter3 = 0;
                    break;
                case 17:
                    spriteNum3 = 18;
                    spriteCounter3 = 0;
                    break;
                case 18:
                    spriteNum3 = 19;
                    spriteCounter3 = 0;
                    break;
                case 19:
                    spriteNum3 = 20;
                    spriteCounter3 = 0;
                    break;
                case 20:
                    spriteNum3 = 21;
                    spriteCounter3 = 0;
                    break;
                case 21:
                    spriteNum3 = 22;
                    spriteCounter3 = 0;
                    break;
                case 22:
                    spriteNum3 = 23;
                    spriteCounter3 = 0;
                    break;
                case 23:
                    spriteNum3 =24;
                    spriteCounter3 = 0;
                    break;
                case 24:
                    spriteNum3 = 25;
                    spriteCounter3 = 0;
                    break;
                case 25:
                    spriteNum3 = 26;
                    spriteCounter3 = 0;
                    break;
                case 26:
                    spriteNum3 = 1;
                    spriteCounter3 = 0;
                    break;

            }
        }

    }
    public void attackingMelee(){
        spriteCounter3++;
        int i=2;
        if (spriteCounter3<i){spriteNum3=1;}
        if (spriteCounter3>=i&& spriteCounter3<i*2){spriteNum3=2;}
        if (spriteCounter3>=i*2&& spriteCounter3<i*3){spriteNum3=3;}
        if (spriteCounter3>=i*3&& spriteCounter3<i*4){spriteNum3=4;}
        if (spriteCounter3>=i*4&& spriteCounter3<i*5){spriteNum3=5;}
        if (spriteCounter3>=i*5&& spriteCounter3<i*6){spriteNum3=6;}
        if (spriteCounter3>=i*6&& spriteCounter3<i*7){spriteNum3=7;}
        if (spriteCounter3>=i*7&& spriteCounter3<i*8){spriteNum3=8;}
        if (spriteCounter3>=i*8&& spriteCounter3<i*9){spriteNum3=9;}
        if (spriteCounter3>=i*9&& spriteCounter3<i*10){spriteNum3=10;}
        if (spriteCounter3>=i*10&& spriteCounter3<i*11){spriteNum3=11;}
        if (spriteCounter3>=i*11&& spriteCounter3<i*12){spriteNum3=12;}
        if (spriteCounter3>=i*12&& spriteCounter3<i*13){spriteNum3=13;}
        if (spriteCounter3>=i*13&& spriteCounter3<i*14){spriteNum3=14;}
        if (spriteCounter3>=i*14&& spriteCounter3<i*15){spriteNum3=15;}
        if (spriteCounter3>=i*15&& spriteCounter3<i*16){spriteNum3=16;}
        if (spriteCounter3>=i*16&& spriteCounter3<i*17){spriteNum3=17;}
        if (spriteCounter3>=i*17&& spriteCounter3<i*18){spriteNum3=18;}
        if (spriteCounter3>=i*18&& spriteCounter3<i*19){spriteNum3=19;}
        if (spriteCounter3>=i*19&& spriteCounter3<i*20){spriteNum3=20;}
        if (spriteCounter3>=i*20&& spriteCounter3<i*21){spriteNum3=21;}
        if (spriteCounter3>=i*21&& spriteCounter3<i*22){spriteNum3=22;}
        if (spriteCounter3>=i*22&& spriteCounter3<i*23){spriteNum3=23;}
        if (spriteCounter3>=i*23&& spriteCounter3<i*24){spriteNum3=24;}
        if (spriteCounter3>=i*24&& spriteCounter3<i*25){spriteNum3=25;}
        if (spriteCounter3>=i*25&& spriteCounter3<i*26){spriteNum3=26;}



        if (spriteCounter3>=i*26)
        {spriteNum3=1; spriteCounter3=0; attacking=false; }

        //attack için düzenlenecek değerler kaydediliyor

        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;
        //attack için düzenlenen değerler
        switch (direction){
            case "up": worldY-=attackArea.height; break;
            case "down": worldY+=attackArea.height; break;
            case "left": worldX-=attackArea.width; break;
            case "right": worldX+=attackArea.width; break;
        }
        //attackArea Becomes Solid Area
        solidArea.width= attackArea.width;
        solidArea.height= attackArea.height;
        //check monster collision with updated worldx-y
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        damageMonster(monsterIndex,this,attack);

        //check interactive tile collision for breaking stuff
        int breakableIndex=gp.cChecker.checkEntity(this,gp.hareketli);
        if (breakableIndex<=gp.hareketli[1].length&& gp.hareketli[gp.currentMap][breakableIndex].type==type_breakable){
            damageObject(breakableIndex);}

        //değerleri eski haline getirme
        worldX=currentWorldX;
        worldY=currentWorldY;
        solidArea.width= solidAreaWidth;
        solidArea.height =solidAreaHeight;
    }
    public Image animationImageSetter(Image[] images){
        int imageIdx = spriteNum3 - 1;
        if (imageIdx >= 0 && imageIdx < images.length) {
            return images[imageIdx];
        } else {
            return null; // Return a default image or handle this case as needed
        }
    }
    public void moveCoordinator(){
        if (keyH.leftPressed){worldX-=speed;}
        else if (keyH.rightPressed){worldX+=speed;}
        else if (keyH.upPressed){worldY-=speed;}
        else if (keyH.downPressed){worldY+=speed;}

    }
}
