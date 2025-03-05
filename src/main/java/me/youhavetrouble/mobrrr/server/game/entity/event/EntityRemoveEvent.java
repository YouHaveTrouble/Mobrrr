package me.youhavetrouble.mobrrr.server.game.entity.event;

import me.youhavetrouble.mobrrr.event.Event;
import me.youhavetrouble.mobrrr.server.game.entity.Entity;

/**
 * Event called when an entity is removed from a map
 * @param <E> entity type
 */
public class EntityRemoveEvent<E extends Entity<?>> extends Event implements EntityEvent<E> {

    private final E entity;

    public EntityRemoveEvent(E entity) {
        this.entity = entity;
    }

    @Override
    public E getEntity() {
        return this.entity;
    }

}
