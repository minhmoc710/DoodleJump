package entity;

import main.GamePanel;

public class MovingPlatform extends Platform{
    private int movingSpeed = 2;

    public MovingPlatform(GamePanel gamePanel, double x, double y) {
        super(gamePanel, x, y);
    }

    public void update(){
        if (y > gamePanel.getHeight()) {
            y = -20;
        }
    }
}
