package me.youhavetrouble.mobrrr.test;

import me.youhavetrouble.mobrrr.event.EventDispatcher;
import me.youhavetrouble.mobrrr.server.entity.DummyEntity;
import me.youhavetrouble.mobrrr.server.entity.DummyEntityTemplate;
import me.youhavetrouble.mobrrr.server.entity.DummyGameMap;
import me.youhavetrouble.mobrrr.server.game.Position;
import me.youhavetrouble.mobrrr.server.game.map.vision.Vision;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VisionTest {

    private final DummyGameMap gameMap;

    public VisionTest() {
        EventDispatcher eventDispatcher = new EventDispatcher();
        this.gameMap = new DummyGameMap(eventDispatcher);
    }

    @Test
    public void testBasicVision() {
        DummyEntity entity1 = this.gameMap.spawnEntity(new DummyEntityTemplate(this.gameMap, new Position(0, -5, 0)));
        DummyEntity entity2 = this.gameMap.spawnEntity(new DummyEntityTemplate(this.gameMap, new Position(0, 5, 0)));

        // Set the vision radius of entity2 to be smaller than the distance to entity1 + entity1's visible radius
        entity2.setVisionRadius(9);

        Vision visionOfEntity1 = entity1.getVisionArea();
        Vision visionOfEntity2 = entity2.getVisionArea();

        // Check if entity1 can see entity2
        assertTrue(
                visionOfEntity1.isVisibleForObservers(entity2, Set.of(entity1), gameMap.terrain),
                "Entity1 cannot see Entity2 and it should"
        );

        // Check if entity2 can see entity1
        assertFalse(
                visionOfEntity2.isVisibleForObservers(entity1, Set.of(entity2), gameMap.terrain),
                "Entity2 can see Entity1 and it shouldn't"
        );

        // teardown
        gameMap.removeEntity(entity1);
        gameMap.removeEntity(entity2);
    }

}
