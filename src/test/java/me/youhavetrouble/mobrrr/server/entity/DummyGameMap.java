package me.youhavetrouble.mobrrr.server.entity;

import me.youhavetrouble.mobrrr.event.EventDispatcher;
import me.youhavetrouble.mobrrr.server.game.map.GameMap;
import me.youhavetrouble.mobrrr.server.game.map.terrain.Terrain;
import me.youhavetrouble.mobrrr.server.game.map.terrain.TerrainComponent;

public class DummyGameMap extends GameMap<TerrainComponent> {

    /**
     * Creates a new game map
     *
     * @param eventDispatcher the event dispatcher to use for events on this map
     */
    public DummyGameMap(EventDispatcher eventDispatcher) {
        super(eventDispatcher, new Terrain<>(eventDispatcher));
    }

    /**
     * Creates a new game map
     *
     * @param eventDispatcher the event dispatcher to use for events on this map
     * @param terrain the terrain of this map
     */
    public DummyGameMap(EventDispatcher eventDispatcher, Terrain<TerrainComponent> terrain) {
        super(eventDispatcher, terrain);
    }

}
