package main;

import java.io.*;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }
    public void saveConfig(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            if (gp.fullScreenOn==true){
                bw.write("on");
            }
            if (gp.fullScreenOn==false){
                bw.write("off");
            }
            bw.newLine();
            bw.write(String.valueOf(gp.music.volumeScale));

            bw.newLine();
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.close();

        }catch (Exception e){e.printStackTrace();}

    }
    public void loadConfig(){
        try {
            BufferedReader br= new BufferedReader(new FileReader("config.txt"));
            String s= br.readLine();
            if (s.equals("on")){gp.fullScreenOn=true;}
            if (s.equals("off")){gp.fullScreenOn=false;}
            s=br.readLine();
            gp.music.volumeScale=Integer.parseInt(s);
            s=br.readLine();
            gp.se.volumeScale=Integer.parseInt(s);

            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
