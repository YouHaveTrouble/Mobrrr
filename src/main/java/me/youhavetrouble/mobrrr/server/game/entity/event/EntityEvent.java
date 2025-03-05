package me.youhavetrouble.mobrrr.server.game.entity.event;

import me.youhavetrouble.mobrrr.server.game.entity.Entity;

public interface EntityEvent<E extends Entity<?>> {

    /**
     * Get entity involved in the event
     * @return entity
     */
    E getEntity();

}
