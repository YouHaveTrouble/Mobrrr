package me.youhavetrouble.mobrrr.server.game;

public class Position {

    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distanceTo(Position other) {
        return Math.sqrt(distanceSquaredTo(other));
    }

    public double distanceSquaredTo(Position other) {
        return Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2);
    }

    public Position add(Position other) {
        return new Position(x + other.x, y + other.y);
    }

    public Position subtract(Position other) {
        return new Position(x - other.x, y - other.y);
    }

    public Position multiply(double factor) {
        return new Position(x * factor, y * factor);
    }

    public Position divide(double factor) {
        return new Position(x / factor, y / factor);
    }

    public Position normalize() {
        double length = Math.sqrt(x * x + y * y);
        return new Position(x / length, y / length);
    }

    @Override
    public Position clone() {
        return new Position(x, y);
    }

}
