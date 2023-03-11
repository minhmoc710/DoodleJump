package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    boolean isAlive = true;
    int width, height, score  = 0;

    private BufferedImage playerImage;
    private BufferedImage playerLeft;
    private BufferedImage playerRight;


    public int getScore(){
        return score;
    }

    public boolean isAlive(){
        return isAlive;
    }
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        try {
            this.playerRight = ImageIO.read(new FileInputStream("assets/images/player.png"));
        } catch (IOException e) {
            System.out.println("ERROR");
        }
        try {
            this.playerLeft = ImageIO.read(new FileInputStream("assets/images/player_flipped.png"));
        } catch (IOException e) {
            System.out.println("ERROR");
        }
        this.playerImage = this.playerRight;
        this.width = this.playerImage.getWidth();
        this.height = this.playerImage.getHeight();
    }

    public void setDefaultValue() {
        x = 100;
        y = 600;
        speed = 1.5;
        acceleration = 0.04;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    private void jump(){
        speed = -4;
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
            isAlive = false;
        }
        if (x < 0) {
            x = gamePanel.getWidth();
        }
        if (x > gamePanel.getWidth()) {
            x = 0;
        }
        if (keyHandler.leftPressed) {
            if (this.playerImage != this.playerLeft) {
                this.playerImage = this.playerLeft;
            }
            x -= 2;
        }
        if (keyHandler.rightPressed) {
            if (this.playerImage != this.playerRight) {
                this.playerImage = this.playerRight;
            }
            x += 2;
        }
        if (y <= gamePanel.getHeight() / 2.0) {
            y = gamePanel.getHeight() / 2.0;
            if (speed <= 0){
                score ++;
            }
        }
    }

    public Point getPlayerLocation() {
        return new Point((int) x, (int) y);
    }


    public void draw(Graphics2D g2d) {
        g2d.drawImage(playerImage, (int) x, (int) y, null);
    }
}
