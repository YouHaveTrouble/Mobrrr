package me.youhavetrouble.mobrrr.server.service.player;

import me.youhavetrouble.mobrrr.server.game.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface Player {

    /**
     * Get the current socket connection of the player
     * @return the connection of the player, or null if the player is not connected
     */
    @Nullable
    Connection getConnection();

    /**
     * Set the current socket connection of the player
     * @param connection the connection of the player
     */
    void setConnection(Connection connection);

    /**
     * Get a collection of all entities that the player is currently tracking. Expected to be a read-only collection.
     * @return a collection of all entities that the player is currently tracking
     */
    Collection<? extends Entity<?>> getTrackedEntities();

    /**
     * Track an entity so that the player receives updates about it
     * @param entity the entity to track
     */
    void trackEntity(Entity<?> entity);

    /**
     * Untrack an entity so that the player no longer receives updates about it
     * @param entity the entity to untrack
     */
    void untrackEntity(Entity<?> entity);

}
