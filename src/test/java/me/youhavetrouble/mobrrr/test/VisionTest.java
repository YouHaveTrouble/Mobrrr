package me.youhavetrouble.mobrrr.test;

import me.youhavetrouble.mobrrr.event.EventDispatcher;
import me.youhavetrouble.mobrrr.server.entity.DummyEntity;
import me.youhavetrouble.mobrrr.server.entity.DummyEntityTemplate;
import me.youhavetrouble.mobrrr.server.entity.DummyGameMap;
import me.youhavetrouble.mobrrr.server.game.Position;
import me.youhavetrouble.mobrrr.server.game.map.terrain.Terrain;
import me.youhavetrouble.mobrrr.server.game.map.terrain.TerrainComponent;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Area;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VisionTest {

    @Test
    public void basicVision() {
        DummyGameMap gameMap = new DummyGameMap(new EventDispatcher());

        DummyEntity entity1 = gameMap.spawnEntity(new DummyEntityTemplate(gameMap, new Position(0, -5, 0)));
        DummyEntity entity2 = gameMap.spawnEntity(new DummyEntityTemplate(gameMap, new Position(0, 5, 0)));

        // Set the vision radius of entity2 to be smaller than the distance to entity1 + entity1's visible radius
        entity2.setVisionRadius(9);

        // Check if entity1 can see entity2
        assertTrue(
                entity1.getVisionArea().isVisibleForObservers(entity2, Set.of(entity1), gameMap.terrain),
                "Entity1 cannot see Entity2 and it should"
        );

        // Check if entity2 can see entity1
        assertFalse(
                entity2.getVisionArea().isVisibleForObservers(entity1, Set.of(entity2), gameMap.terrain),
                "Entity2 can see Entity1 and it shouldn't"
        );
    }

    @Test
    public void terrainBlocksVision() {
        Terrain<TerrainComponent> terrain = new Terrain<>(new EventDispatcher());
        // Create a wall
        Rectangle wall = new Rectangle(-10,-1,20,2);
        terrain.add(new TerrainComponent(new Area(wall), 10));

        DummyGameMap gameMap = new DummyGameMap(new EventDispatcher(), terrain);

        DummyEntity entity1 = gameMap.spawnEntity(new DummyEntityTemplate(gameMap, new Position(0, -5, 0)));
        DummyEntity entity2 = gameMap.spawnEntity(new DummyEntityTemplate(gameMap, new Position(0, 5, 0)));

        // Check if vision is blocked by the wall
        assertFalse(
                entity1.getVisionArea().isVisibleForObservers(entity2, Set.of(entity1), gameMap.terrain),
                "Entity1 can see Entity2 and it shouldn't, because vision is blocked by the wall"
        );
    }

}
