package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    private long pausedPosition =0;
    Clip clip ;
    URL soundUrl[] = new URL[50];
    FloatControl fc;
    int volumeScale=5;
    float volume;

    public Sound (){
        soundUrl[0]=getClass().getResource("/sound/seyis.wav");
        soundUrl[1]=getClass().getResource("/sound/severimbaba.wav");
        soundUrl[2]=getClass().getResource("/sound/Oh_dunya_varmıs.wav");
        soundUrl[3]=getClass().getResource("/sound/ayhost.wav");
        soundUrl[4]=getClass().getResource("/sound/duydumkibensiz.wav");
        soundUrl[5]=getClass().getResource("/sound/okey.wav");
        soundUrl[6]=getClass().getResource("/sound/cursor.wav");
        soundUrl[7]=getClass().getResource("/sound/yeabuddy.wav");
        soundUrl[8]=getClass().getResource("/sound/ok_zirink.wav");
        soundUrl[9]=getClass().getResource("/sound/titlemusic.wav");
        soundUrl[10]=getClass().getResource("/sound/damage_yumruk.wav");
        soundUrl[11]=getClass().getResource("/sound/eaa.wav");
        soundUrl[12]=getClass().getResource("/sound/eyyaa.wav");
        soundUrl[13]=getClass().getResource("/sound/öoo.wav");
        soundUrl[14]=getClass().getResource("/sound/missedPunch.wav");
        soundUrl[15]=getClass().getResource("/sound/Lvl_up.wav");
        soundUrl[16]=getClass().getResource("/sound/fsst_bum.wav");
        soundUrl[17]=getClass().getResource("/sound/cursor.wav");
        soundUrl[18]=getClass().getResource("/sound/Cash.wav");
        soundUrl[19]=getClass().getResource("/sound/INVcomeon.wav");
        soundUrl[20]=getClass().getResource("/sound/INVlightweıght.wav");
        soundUrl[21]=getClass().getResource("/sound/INVowowow.wav");
        soundUrl[22]=getClass().getResource("/sound/INVyeabuddy1.wav");
        soundUrl[23]=getClass().getResource("/sound/INVyeabuddy2.wav");
        soundUrl[24]=getClass().getResource("/sound/INVyouarechampion.wav");
        soundUrl[25]=getClass().getResource("/sound/INVyoubuddy.wav");
        soundUrl[26]=getClass().getResource("/sound/yeme.wav");
        soundUrl[27]=getClass().getResource("/sound/Pu.wav");
        soundUrl[28]=getClass().getResource("/sound/slime_hit.wav");
        soundUrl[29]=getClass().getResource("/sound/fsst.wav");
        soundUrl[30]=getClass().getResource("/sound/bum.wav");
        soundUrl[31]=getClass().getResource("/sound/Oh_dunya_varmıs.wav");
        soundUrl[32]=getClass().getResource("/sound/doorbreak.wav");
        soundUrl[33]=getClass().getResource("/sound/GameOver.wav");
        soundUrl[34]=getClass().getResource("/sound/GameOver2.wav");
        soundUrl[35]=getClass().getResource("/sound/gameover3.wav");
        soundUrl[36]=getClass().getResource("/sound/gameover4.wav");
        soundUrl[37]=getClass().getResource("/sound/gameover5.wav");
        soundUrl[38]=getClass().getResource("/sound/gameover6.wav");
        soundUrl[39]=getClass().getResource("/sound/gameover7.wav");
        soundUrl[40]=getClass().getResource("/sound/gameover8.wav");
        soundUrl[41]=getClass().getResource("/sound/gameover9.wav");
        soundUrl[42]=getClass().getResource("/sound/gameover10.wav");
        soundUrl[43]=getClass().getResource("/sound/gameover11.wav");
        soundUrl[44]=getClass().getResource("/sound/gameover12.wav");
        soundUrl[45]=getClass().getResource("/sound/agır_ilim.wav");
        soundUrl[46]=getClass().getResource("/sound/salongiris.wav");
        soundUrl[47]=getClass().getResource("/sound/kabus.wav");
        soundUrl[48]=getClass().getResource("/sound/ver_baba.wav");






    }
    public void setFile(int i){
        try {
            AudioInputStream ais= AudioSystem.getAudioInputStream(soundUrl[i]);
            clip= AudioSystem.getClip();
            clip.open(ais);
            fc=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void play(){
    clip.start();
    }
    public void loop(){
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
    clip.stop();
    }
    public void pause(){
        pausedPosition=clip.getMicrosecondPosition();
        clip.stop();
    }
    public void resume(){
        clip.setMicrosecondPosition(pausedPosition);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void checkVolume(){
        switch (volumeScale){
            case 0: volume=-80f; break;
            case 1: volume=-20f; break;
            case 2: volume=-12f; break;
            case 3: volume=-5f; break;
            case 4: volume=1f; break;
            case 5: volume=6f; break;
        }
        fc.setValue(volume);

    }

}
