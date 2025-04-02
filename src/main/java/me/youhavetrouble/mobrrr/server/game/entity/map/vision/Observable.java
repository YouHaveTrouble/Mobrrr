package me.youhavetrouble.mobrrr.server.game.entity.map.vision;

import java.awt.geom.Area;

public interface Observable {

    /**
     * Get visible area of the observable that is used for vision calculations
     * @return visible area
     */
    Area getVisibleArea();

}
