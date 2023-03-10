package entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Platform extends Entity{
    public double width = 80, height = 20;
    Random random = new Random();

    GamePanel gamePanel;
    private BufferedImage platformImage;

    public double getWidth(){
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }
    public Platform(GamePanel gamePanel, double x, double y) {
        this.gamePanel = gamePanel;
        this.x = x;
        this.y = y;

        try {
            this.platformImage = ImageIO.read(new FileInputStream("assets/images/platform.png"));
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(this.platformImage, (int) x, (int) y, null);
    }

    public void update(){
        if (y > gamePanel.getHeight()) {
            y = -20;
        }
    }

}
