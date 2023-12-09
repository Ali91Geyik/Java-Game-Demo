package main;

import java.util.Random;

public class MusicThread extends Thread{
    private GamePanel gamePanel;
    private boolean isRunning = true;

    public MusicThread(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        gamePanel.playPauseMenuMusic(4); // Change 'yourMusicIndex' to the appropriate music index

        while (isRunning) {
            // Continue playing music as long as the thread is running
            try {
                Thread.sleep(10); // Adjust the sleep duration as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        gamePanel.stopPauseMenuMusic(4); // Stop the music when the thread stops
    }

    public void stopMusicThread() {
        isRunning = false;
    }
}
