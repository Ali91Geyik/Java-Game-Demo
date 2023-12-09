package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed, shotKeyPressed,projectileKeyPressed, nextLineRequest;

    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //TİTLE STATE
        if (gp.gameState==gp.titleState){titleState(code);}
        //PlayState
        else if(gp.gameState== gp.playState){playState(code);}
        //PauseState
        else if (gp.gameState==gp.pauseState){pauseState(code);}
        //DialogueState
        else if (gp.gameState==gp.dialogueState){dialogueState(code);}
        //CharacterState
        else if (gp.gameState== gp.characterState) {characterState(code);}
        //optionsState
        else if (gp.gameState== gp.optionsState) {optionsState(code);}
        //GameOverState
        else if (gp.gameState== gp.gameOverState) {gameOverState(code);}
        //Cutscene State
        else if (gp.gameState==gp.cutSceneState){cutSceneState(code);}
        //TradeState
        else if (gp.gameState==gp.tradeState){tradeState(code);}
    }
    public void titleState(int code){

        if (code==KeyEvent.VK_W){
            gp.ui.commandNum--;
            gp.playSE(7);
            if (gp.ui.commandNum==-1){
                gp.ui.commandNum=2;
                gp.playSE(7);
            }
        }
        if (code==KeyEvent.VK_S){
            gp.ui.commandNum++;
            gp.playSE(7);
            if (gp.ui.commandNum==3){
                gp.ui.commandNum=0;
                gp.playSE(7);
            }
        }
        if (code==KeyEvent.VK_ENTER){
            switch (gp.ui.commandNum){
                case 0:
                    gp.gameState= gp.playState;
                    gp.stopMusic();
                    gp.playMusic(0);
                    gp.playSE(8);
                    break;
                case 1:
                    break;
                case 2:
                    System.exit(0);
                    break;

            }

        }
    }
    public void playState(int code){


        if(code==KeyEvent.VK_W){
            upPressed=true;
        }
        if(code==KeyEvent.VK_A){
            leftPressed=true;
        }
        if(code==KeyEvent.VK_S){
            downPressed= true;
        }
        if(code==KeyEvent.VK_D){
            rightPressed=true;
        }
        if (code==KeyEvent.VK_F){
            shotKeyPressed=true;
        }
        if (code==KeyEvent.VK_Q){
            projectileKeyPressed=true;
        }
        if(code==KeyEvent.VK_P){
            gp.gameState=gp.pauseState;
            gp.stopMusic();
            gp.playMusic(4);
        }
        if (code==KeyEvent.VK_C){
            gp.gameState=gp.characterState;
        }
        if (code==KeyEvent.VK_ENTER){
            enterPressed=true;
        }
        if (code==KeyEvent.VK_ESCAPE){
            gp.gameState=gp.optionsState;
        }

    }
    public void pauseState(int code){

        if(code==KeyEvent.VK_P){
            gp.gameState=gp.playState;
            gp.stopMusic();
            gp.playMusic(0);

        }

    }
    public void dialogueState(int code){

        if (code==KeyEvent.VK_ENTER){
            gp.gameState= gp.playState;
        }
        if (code==KeyEvent.VK_SPACE){
            nextLineRequest=true;
        }

    }
    public void cutSceneState(int code){
        if (code==KeyEvent.VK_ESCAPE){
            gp.gameState= gp.playState;
            gp.player.lwlUp=false;
            gp.eHandler.salongiris=false;
            gp.player.invincible=true;
        }
    }
    public void characterState(int code){

        if (code==KeyEvent.VK_C){gp.gameState= gp.playState;}
        if (code==KeyEvent.VK_ESCAPE){gp.gameState= gp.playState;}

        if (code==KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
        playerInventory(code);



    }
    public void optionsState(int code){
        if (code==KeyEvent.VK_ESCAPE){
            gp.gameState=gp.playState;
        }
        if (code==KeyEvent.VK_ENTER){
            enterPressed=true;
        }
        int maxCommandNum=0;
        switch (gp.ui.subState){
            case 0: maxCommandNum=5; break;
            case 3: maxCommandNum=1; break;}
            if(code==KeyEvent.VK_W){
            gp.ui.commandNum--;
             gp.player.changeableSoundEffects= new int[]{19,20,21,22,23,24,25};
             gp.playSE(gp.player.soundEffectChanger(7, gp.player.changeableSoundEffects));
            if (gp.ui.commandNum<0){
                gp.ui.commandNum=maxCommandNum;}
         }
         if(code==KeyEvent.VK_S){
             gp.ui.commandNum++;
             gp.player.changeableSoundEffects= new int[]{19,20,21,22,23,24,25};
             gp.playSE(gp.player.soundEffectChanger(7, gp.player.changeableSoundEffects));
             if (gp.ui.commandNum>maxCommandNum){
                 gp.ui.commandNum=0;}
                }
         if (code==KeyEvent.VK_A){
             if (gp.ui.subState==0){
             if (gp.ui.commandNum==1&&gp.music.volumeScale>0){
                gp.music.volumeScale--;
                gp.music.checkVolume();
                gp.playSE(6);
             }
                 if (gp.ui.commandNum==2&&gp.se.volumeScale>0){
                     gp.se.volumeScale--;
                     gp.playSE(6);
                 }
         }}
                if (code==KeyEvent.VK_D){
                    if (gp.ui.subState==0){
                    if (gp.ui.commandNum==1&&gp.music.volumeScale<5){
                        gp.music.volumeScale++;
                        gp.music.checkVolume();
                        gp.playSE(6);
                    }
                        if (gp.ui.commandNum==2&&gp.se.volumeScale<5){
                            gp.se.volumeScale++;

                            gp.playSE(6);
                        }
                }}



        }
    public void gameOverState(int code){
        if (code== KeyEvent.VK_W){
            gp.ui.commandNum--;
            gp.playSE(6);
            if (gp.ui.commandNum<0){
                gp.ui.commandNum=1;
            }
        }
        if (code== KeyEvent.VK_S){
            gp.ui.commandNum++;
            gp.playSE(6);
            if (gp.ui.commandNum>1){
                gp.ui.commandNum=0;
            }
        }
        if (code==KeyEvent.VK_ENTER){
            if (gp.ui.commandNum==0){
                gp.gameState=gp.playState;
                gp.retry();
            }
            if (gp.ui.commandNum==1){
                gp.gameState=gp.titleState;
                gp.restart();
            }

        }

    }
    public void tradeState(int code){
        if (code==KeyEvent.VK_ENTER){
            enterPressed=true;
        }
        if (gp.ui.subState==0){
            if (code==KeyEvent.VK_W){
                gp.ui.commandNum--;
                if (gp.ui.commandNum<0){
                    gp.ui.commandNum=2;
                }
            gp.playSE(6);
            }
            if (code==KeyEvent.VK_S){
                gp.ui.commandNum++;
                if (gp.ui.commandNum>2){
                    gp.ui.commandNum=0;
                }
                gp.playSE(6);
            }

        }
        if (gp.ui.subState==1){
            npcInventory(code);
            if (code==KeyEvent.VK_ESCAPE){gp.ui.subState=0;}
        }
        if (gp.ui.subState==2){
            playerInventory(code);
            if (code==KeyEvent.VK_ESCAPE){gp.ui.subState=0;}
        }

    }
    public void playerInventory(int code){
        if (gp.ui.playerSlotRow != 0) {if (code==KeyEvent.VK_W){gp.ui.playerSlotRow--;gp.playSE(17);}}
        if (gp.ui.playerSlotCol !=0){if (code==KeyEvent.VK_A){gp.ui.playerSlotCol--;gp.playSE(17);}}
        if (gp.ui.playerSlotCol !=4){if (code==KeyEvent.VK_D){gp.ui.playerSlotCol++;gp.playSE(17);}}
        if (gp.ui.playerSlotRow !=3){if (code==KeyEvent.VK_S){gp.ui.playerSlotRow++;gp.playSE(17);}}
    }
    public void npcInventory(int code){
        if (gp.ui.npcSlotRow != 0) {if (code==KeyEvent.VK_W){gp.ui.npcSlotRow--;gp.playSE(17);}}
        if (gp.ui.npcSlotCol !=0){if (code==KeyEvent.VK_A){gp.ui.npcSlotCol--;gp.playSE(17);}}
        if (gp.ui.npcSlotCol !=4){if (code==KeyEvent.VK_D){gp.ui.npcSlotCol++;gp.playSE(17);}}
        if (gp.ui.npcSlotRow !=3){if (code==KeyEvent.VK_S){gp.ui.npcSlotRow++;gp.playSE(17);}}

    }



    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W){
            upPressed=false;
        }
        if(code==KeyEvent.VK_A){
            leftPressed=false;
        }
        if(code==KeyEvent.VK_S){
            downPressed= false;
        }
        if(code==KeyEvent.VK_D){
            rightPressed=false;
        }
        if (code==KeyEvent.VK_F){
            shotKeyPressed=false;
        }
        if (code==KeyEvent.VK_ENTER){
            enterPressed=false;
        }
        if (code==KeyEvent.VK_Q){
            projectileKeyPressed=false;
        }



    }
}
