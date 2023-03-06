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
        speed = 2;
        acceleration = 0.04;
    }

    private void jump(){
        speed = -5;
        y += speed;
    }

    public void hitPlatform() {
        jump();
    }

    public void update() {
        if (y + speed <= gamePanel.getHeight() - 60) {
            speed += acceleration;
            y += speed;
        } else {
            y = gamePanel.getHeight() - 60;
            jump();
        }
        if (x < 0) {
            x = gamePanel.getWidth();
        }
        if (x > gamePanel.getWidth()) {
            x = 0;
        }
        if (keyHandler.leftPressed) {
            x -= 2;
        }
        if (keyHandler.rightPressed) {
            x += 2;
        }
    }

    public Point getPlayerLocation() {
        return new Point((int) x, (int) y);
    }


    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) x, (int) y, 30, 60);
    }

}
