package me.youhavetrouble.mobrrr.server.game.map.terrain;

import org.jetbrains.annotations.NotNull;

import java.awt.geom.Area;

public record TerrainComponent(@NotNull Area area, double height) {

    public TerrainComponent(@NotNull Area area, double height) {
        this.area = area;
        this.height = height;
    }

}
