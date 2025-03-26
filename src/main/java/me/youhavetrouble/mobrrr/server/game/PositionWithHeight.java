package me.youhavetrouble.mobrrr.server.game;

public class PositionWithHeight extends Position {

    private final double height;

    public PositionWithHeight(double x, double y, double height) {
        super(x, y);
        this.height = height;
    }

    public PositionWithHeight(Position position, double height) {
        super(position.getX(), position.getY());
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public PositionWithHeight clone() {
        return new PositionWithHeight(getX(), getY(), height);
    }

}
