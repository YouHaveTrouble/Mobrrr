package me.youhavetrouble.mobrrr.server.game.map.vision;

import java.awt.geom.Area;
import java.util.Collection;

public class Vision extends Area {

    /**
     * Creates an empty vision area
     */
    public Vision() {}

    /**
     * Create a sum of vision areas from a collection of vision areas
     * @param visionCollection collection of vision areas
     */
    public Vision(Collection<Vision> visionCollection) {
        for (Vision vision : visionCollection) {
            this.add(vision);
        }
    }

    public boolean isWithin(Observable observable) {
        if (this.intersects(observable.getVisibleArea().getBounds2D())) return false; // fail fast if not in bounding box
        Area intersectionArea = new Area(observable.getVisibleArea());
        intersectionArea.intersect(this);
        return !intersectionArea.isEmpty();
    }

}
