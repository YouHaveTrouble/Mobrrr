package me.youhavetrouble.mobrrr.server.game;

public record Position(double x, double y, double height) {

    public double distanceTo(Position other) {
        return Math.sqrt(distanceSquaredTo(other));
    }

    public double distanceSquaredTo(Position other) {
        return Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2);
    }

    @Override
    public Position clone() {
        return new Position(x, y, height);
    }

}
