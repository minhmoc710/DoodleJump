package main;

import entity.Player;
import entity.Platform;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends javax.swing.JPanel implements Runnable {
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    int FPS = 240;

    Player player = new Player(this, keyHandler);
    ArrayList<Platform> platforms = new ArrayList<>();

    public GamePanel() {
        this.setPreferredSize(new Dimension(400, 700));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        player.setDefaultValue();

        initPlatforms(10);
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            while (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();

        for (Platform platform : platforms) {
            if (player.getSpeed() > 0 &&
                    player.getX() > platform.getX() &&
                    player.getX() < platform.getX() + platform.getWidth() &&
                    player.getY() + 60 > platform.getY() &&
                    player.getY() + 60 < platform.getY() + platform.getHeight()
            ) {
                player.hitPlatform();
            }
        }
    }


    public void initPlatforms(int n) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            platforms.add(new Platform(random.nextInt(this.getWidth() - 80), i * 80, 80, 20));
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        player.draw(g2d);

        for (Platform platform : platforms) {
            platform.draw(g2d);
        }
        g2d.dispose();
    }
}
