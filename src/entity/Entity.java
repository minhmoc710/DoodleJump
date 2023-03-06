package entity;

public class Entity {
    public double x, y, speed, acceleration;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
