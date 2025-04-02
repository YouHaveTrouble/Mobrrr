package me.youhavetrouble.mobrrr.test;

import me.youhavetrouble.mobrrr.event.EventDispatcher;
import me.youhavetrouble.mobrrr.server.entity.DummyEntityTemplate;
import me.youhavetrouble.mobrrr.server.entity.DummyGameMap;
import me.youhavetrouble.mobrrr.server.game.entity.map.GameMap;
import me.youhavetrouble.mobrrr.server.game.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for default entity functionality
 */
public class EntityTest {

    @Test
    public void spawning() {
        GameMap gameMap = new DummyGameMap(new EventDispatcher());
        gameMap.spawnEntity(new DummyEntityTemplate(gameMap, new Position(0, 0, 0)));
        assertEquals(1, gameMap.getEntities().size(), "Entity was not spawned or is not tracked");
    }

}
