package entity;

import java.awt.*;

public class Platform extends Entity{
    public double width, height;

    public double getWidth(){
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }
    public Platform(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) x, (int) y, (int) width, (int) height);
    }

}
