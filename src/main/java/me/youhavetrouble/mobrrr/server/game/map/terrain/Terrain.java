package me.youhavetrouble.mobrrr.server.game.map.terrain;

import me.youhavetrouble.mobrrr.event.EventDispatcher;
import me.youhavetrouble.mobrrr.server.game.map.terrain.event.TerrainAddedEvent;
import me.youhavetrouble.mobrrr.server.game.map.terrain.event.TerrainRemovedEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Area;
import java.util.HashSet;

public class Terrain<T extends TerrainComponent> extends HashSet<T> {

    private final EventDispatcher eventDispatcher;
    private final Area totalArea = new Area();

    public Terrain(@NotNull EventDispatcher eventDispatcher) {
        super();
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public final boolean add(T terrainComponent) {
        if (terrainComponent == null) return false;
        boolean collectionChanged = super.add(terrainComponent);
        if (collectionChanged) {
            this.eventDispatcher.dispatchEvent(new TerrainAddedEvent<>(terrainComponent));
            this.updateTotalArea();
        }
        return collectionChanged;
    }

    @Override
    public final boolean remove(Object o) {
        if (!(o instanceof TerrainComponent)) return false;
        boolean collectionChanged = super.remove(o);
        T terrainComponent = (T) o;
        if (collectionChanged) {
            this.eventDispatcher.dispatchEvent(new TerrainRemovedEvent<>(terrainComponent));
            this.updateTotalArea();
        }
        return collectionChanged;
    }

    @Override
    public final void clear() {
        super.clear();
        this.totalArea.reset();
    }

    /**
     * Get the total area of all terrain components.
     * @return the total area of all terrain components
     */
    public Area getTotalArea() {
        return new Area(totalArea);
    }

    /**
     * Get the highest point at the given coordinates.
     * @return the highest point at the given coordinates
     */
    public double getHighestPointAt(double x, double y) {
        if (totalArea.isEmpty()) return 0;
        if (!totalArea.contains(x, y)) return 0;
        double highestPoint = 0;
        for (TerrainComponent terrainComponent : this) {
            if (!terrainComponent.area().contains(x, y)) continue;
            highestPoint = Math.max(highestPoint, terrainComponent.height());
        }
        return highestPoint;
    }

    private void updateTotalArea() {
        totalArea.reset();
        for (TerrainComponent terrainComponent : this) {
            totalArea.add(terrainComponent.area());
        }
    }

}
