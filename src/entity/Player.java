package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
    }

    public void setDefaultValue() {
        x = 100;
        y = 100;
        speed = 1;
        acceleration = 0.01;
    }

    private void jump(){
        speed = -2;
        y += speed;
    }

    public void update() {
        if (y + speed <= gamePanel.getHeight() - 60) {
            speed += acceleration;
            y += speed;
        } else {
            y = gamePanel.getHeight() - 60;
        }
        if (keyHandler.leftPressed) {
            x -= speed;
        }
        if (keyHandler.rightPressed) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) x, (int) y, 30, 60);
    }

}
