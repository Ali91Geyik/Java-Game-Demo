package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseHandler extends MouseMotionAdapter {
    GamePanel gp;

    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }

    public String calculateDirection(int mouseX, int mouseY, int playerX, int playerY) {
        double angle = Math.atan2(mouseY - playerY, mouseX - playerX);
        double degree = Math.toDegrees(angle);

        if (degree >= -45 && degree < 45) {
            return "right";
        } else if (degree >= 45 && degree < 135) {
            return "down";
        } else if (degree >= 135 || degree < -135) {
            return "left";
        } else if (degree >= -135 && degree < -45) {
            return "up";
        }

        return ""; // Handle other cases as needed
    }
}
