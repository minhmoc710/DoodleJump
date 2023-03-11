package main;

import entity.Player;
import entity.Platform;
import entity.Star;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends javax.swing.JPanel implements Runnable {
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    int FPS = 240;

    Player player = new Player(this, keyHandler);
    ArrayList<Platform> platforms = new ArrayList<>();
    ArrayList<Star> stars = new ArrayList<>();

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

        initPlatforms(8);
        initStars();
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

    private void initStars() {
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            stars.add(new Star(this, random.nextInt(this.getWidth()), random.nextInt(this.getHeight())));
        }
    }

    public void update() {
        player.update();
        for (Platform platform : platforms) {
            platform.update();
            if (player.getY() <= this.getHeight() / 2.0 && player.getSpeed() < 0) {
                platform.setLocation(platform.x, platform.y - player.getSpeed());
            }
            if (player.getSpeed() > 0 &&
                    (
                        (player.getX() > platform.getX() && player.getX() < platform.getX() + platform.getWidth()) ||
                        (player.getX() + player.getWidth() > platform.getX() && player.getX() + player.getWidth() < platform.getX() + platform.getWidth())
                    ) &&
                    player.getY() + player.getHeight() > platform.getY() &&
                    player.getY() + player.getHeight() < platform.getY() + platform.getHeight()
            ) {
                player.hitPlatform();
            }
        }

        for (Star star: stars) {
            star.update();
            if (player.getY() <= this.getHeight() / 2.0 && player.getSpeed() < 0) {
                star.setLocation(star.x, star.y - player.getSpeed() * 0.3);
            }
        }
    }


    public void initPlatforms(int n) {
        Random random = new Random();
        platforms.add(new Platform(this, 100, this.getHeight() - 30));
        for (int i = 0; i < n; i++) {
            platforms.add(new Platform(this, random.nextInt(this.getWidth() - 80), i * 80));
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        this.setBackground(new Color(14, 30, 73));

        if (player.isAlive()) {
            Font font = new Font("Verdana", Font.BOLD, 14);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString(String.format("Score: %d", player.getScore()), 295, 15);

            for (Star star: stars) {
                star.draw(g2d);
            }
            for (Platform platform : platforms) {
                platform.draw(g2d);
            }
            player.draw(g2d);
        } else {
            Font font = new Font("Verdana", Font.BOLD, 40);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("Game Over", 30, 100);
            g.drawString(String.format("Score: %d", player.getScore()), 30, 200);
        }


        g2d.dispose();
    }
}
