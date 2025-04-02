package me.youhavetrouble.mobrrr.server.game.entity.map.terrain;

import org.jetbrains.annotations.NotNull;

import java.awt.geom.Area;

public class TerrainComponent {

    private final Area area;
    private final double height;

    public TerrainComponent(@NotNull Area area, double height) {
        this.area = area;
        this.height = height;
    }

    public @NotNull Area getArea() {
        return area;
    }

    public double getHeight() {
        return height;
    }

    public static class TerrainBuilder {
        private final Area area;
        private double height;

        public TerrainBuilder() {
            this.area = new Area();
        }

        public TerrainBuilder setHeight(double height) {
            this.height = height;
            return this;
        }
        public TerrainBuilder addArea(Area area) {
            this.area.add(area);
            return this;
        }

        public @NotNull TerrainComponent build() {
            return new TerrainComponent(area, height);
        }
    }

}
