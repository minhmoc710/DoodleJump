package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Star extends Entity{
    public double width = 80, height = 20;
    Random random = new Random();

    GamePanel gamePanel;
    private BufferedImage starImage;
    public Star(GamePanel gamePanel, double x, double y) {
        this.gamePanel = gamePanel;
        this.x = x;
        this.y = y;

        try {
            this.starImage = ImageIO.read(new FileInputStream("assets/images/star.png"));
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(this.starImage, (int) x, (int) y, null);
    }

    public void update(){
        if (y > gamePanel.getHeight()) {
            y = - random.nextInt(64) + 32;
            x = random.nextInt(gamePanel.getHeight());
        }
    }
}
