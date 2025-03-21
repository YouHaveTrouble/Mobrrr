package me.youhavetrouble.mobrrr.server.entity;

import me.youhavetrouble.mobrrr.event.EventDispatcher;
import me.youhavetrouble.mobrrr.server.game.GameMap;

public class DummyGameMap extends GameMap {

    /**
     * Creates a new game map
     *
     * @param eventDispatcher the event dispatcher to use for events on this map
     */
    public DummyGameMap(EventDispatcher eventDispatcher) {
        super(eventDispatcher);
    }

}
