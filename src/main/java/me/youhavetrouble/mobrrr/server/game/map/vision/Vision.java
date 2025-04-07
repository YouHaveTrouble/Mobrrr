package me.youhavetrouble.mobrrr.server.game.map.vision;

import me.youhavetrouble.mobrrr.server.game.Util;
import me.youhavetrouble.mobrrr.server.game.map.terrain.Terrain;
import me.youhavetrouble.mobrrr.server.game.map.terrain.TerrainComponent;

import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    public boolean isVisibleForObservers(
            Observable observable,
            Set<Observer> observers,
            Terrain<? extends TerrainComponent> terrain
    ) {
        if (!isWithin(observable)) return false; // shape is not within the vision area
        Rectangle2D observableBounds = observable.getVisibleArea().getBounds2D();

        // Check if any of the observers can see at least one corner of the observable
        // TODO probably a better LOS check
        for (Observer observer : observers) {
            Point2D observerOrigin = new Point2D.Double(observer.getObserverOrigin().x(), observer.getObserverOrigin().y());
            Point2D[] observerPoints = new Point2D[4];
            observerPoints[0] = new Point2D.Double(observableBounds.getMinX(), observableBounds.getMinY());
            observerPoints[1] = new Point2D.Double(observableBounds.getMinX(), observableBounds.getMaxY());
            observerPoints[2] = new Point2D.Double(observableBounds.getMaxX(), observableBounds.getMinY());
            observerPoints[3] = new Point2D.Double(observableBounds.getMaxX(), observableBounds.getMaxY());

            // raycast from observer to each  corner of the observable
            for (Point2D point : observerPoints) {
                Area obstacleHit = Util.raycast(observerOrigin, point, List.of(terrain.getTotalArea()));
                if (obstacleHit != null) continue; // hit a wall
                return true; // spotted!
            }
        }

        return false;
    }

}
