package main;



import javax.swing.*;

public class Main {

    public static JFrame window;
    public static void main(String[]args){


            window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Feriştah King Rais");
            window.setUndecorated(true);

            GamePanel gamePanel = new GamePanel();
            window.add(gamePanel);//game paneli waaaassaindowa yerleştirdik

            gamePanel.config.loadConfig();
            if (gamePanel.fullScreenOn==true){
                window.setUndecorated(true);
            }

            window.pack();        //window boyutunu içeriğini(gamepaneli) kavrayacak ölçüde ayarlamaya yarar

            window.setLocationRelativeTo(null);
            window.setVisible(true);
            gamePanel.setupGame();
            gamePanel.startGameThread();


    }
}
