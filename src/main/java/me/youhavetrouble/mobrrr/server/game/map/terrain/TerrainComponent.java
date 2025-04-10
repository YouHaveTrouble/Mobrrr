package me.youhavetrouble.mobrrr.server.game.map.terrain;

import org.jetbrains.annotations.NotNull;

import java.awt.geom.Area;

public class TerrainComponent {

    private final Area area;
    private final double height;

    public TerrainComponent(
            @NotNull Area area,
            double height
    ) {
        this.area = area;
        this.height = height;
    }

    /**
     * Returns a copy of the collision area
     * @return a copy of the collision area
     */
    public @NotNull Area area() {
        return new Area(area);
    }

    /**
     * Returns the height of the terrain component
     * @return the height of the terrain component
     */
    public double height() {
        return height;
    }

}
