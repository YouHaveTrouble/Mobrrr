package me.youhavetrouble.mobrrr.server.game;

public class PositionWithHeight extends Position {

    private double height;

    public PositionWithHeight(double x, double y, double height) {
        super(x, y);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
