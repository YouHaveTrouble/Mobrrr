package me.youhavetrouble.mobrrr.server.entity;

import me.youhavetrouble.mobrrr.server.game.map.GameMap;
import me.youhavetrouble.mobrrr.server.game.Position;
import me.youhavetrouble.mobrrr.server.game.entity.Entity;
import me.youhavetrouble.mobrrr.server.game.map.terrain.TerrainComponent;
import me.youhavetrouble.mobrrr.server.game.map.vision.Observable;
import me.youhavetrouble.mobrrr.server.game.map.vision.Observer;
import me.youhavetrouble.mobrrr.server.game.map.vision.Vision;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class DummyEntity extends Entity<DummyEntityTemplate> implements Observer, Observable {

    private final double observableRadius = 1;
    private double visionRadius = 10;

    private final Vision vision = new Vision();

    public DummyEntity(int id, int typeId, @NotNull GameMap<TerrainComponent> gameMap, @NotNull Position position) {
        super(id, typeId, gameMap, position);
        updateVision();
    }

    public void setVisionRadius(double visionRadius) {
        this.visionRadius = visionRadius;
        updateVision();
    }

    @Override
    public Area getVisibleArea() {
        double diameter = observableRadius * 2;
        Ellipse2D.Double circle = new Ellipse2D.Double(
                getPosition().x() - observableRadius,
                getPosition().y() - observableRadius,
                diameter,
                diameter
        );
        return new Area(circle);
    }

    @Override
    public @NotNull Vision getVisionArea() {
        return this.vision;
    }

    @Override
    public @NotNull Position getObserverOrigin() {
        return this.getPosition();
    }

    @Override
    public void updateVision() {
        this.vision.reset();
        // Create a circle with the radius of the vision
        double diameter = visionRadius * 2;
        Ellipse2D.Double circle = new Ellipse2D.Double(
                getObserverOrigin().x() - visionRadius,
                getObserverOrigin().y() - visionRadius,
                diameter,
                diameter
        );
        this.vision.add(new Area(circle));
    }

    @Override
    public void tick() {
        this.updateVision();
        super.tick();
    }
}
