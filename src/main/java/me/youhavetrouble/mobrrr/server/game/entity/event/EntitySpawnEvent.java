package me.youhavetrouble.mobrrr.server.game.entity.event;

import me.youhavetrouble.mobrrr.event.Event;
import me.youhavetrouble.mobrrr.server.game.entity.Entity;

/**
 * Event called when an entity is spawned on a map
 * @param <E> entity type
 */
public class EntitySpawnEvent<E extends Entity<?>> extends Event implements EntityEvent<E> {

    private final E entity;

    public EntitySpawnEvent(E entity) {
        this.entity = entity;
    }

    @Override
    public E getEntity() {
        return this.entity;
    }

}
