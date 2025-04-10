package me.youhavetrouble.mobrrr.server.game.map.vision;

import org.jetbrains.annotations.NotNull;

import java.awt.geom.Area;

public interface Observable {

    /**
     * Get visible area of the observable that is used for vision calculations
     * @return visible area
     */
    @NotNull Area getVisibleArea();

}
