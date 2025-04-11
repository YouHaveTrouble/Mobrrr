package me.youhavetrouble.mobrrr.server.service.physics;

import me.youhavetrouble.mobrrr.server.game.Position;
import me.youhavetrouble.mobrrr.server.game.map.GameMap;
import me.youhavetrouble.mobrrr.server.game.map.collision.Collidable;
import me.youhavetrouble.mobrrr.server.game.map.terrain.TerrainComponent;
import org.jetbrains.annotations.NotNull;

public interface PhysicsProvider<T extends TerrainComponent> {

    /**
     * Get the game map this physics provider is for
     * @return the game map
     */
    GameMap<T> getGameMap();

    /**
     * Check if the given position would collide with any entities or terrain
     * @param collidable the collidable to check
     * @param position the position to check
     * @return true if it would collide, false otherwise
     */
    boolean wouldCollideAt(@NotNull Collidable collidable, @NotNull Position position);

    /**
     * Get a position that collidable should be at to not collide with anything.<br>
     * Mainly used to determine the position of collidable that was spawned or moved inside another collidable
     * @param collidable the collidable to check
     * @return the position that collidable should be at
     */
    @NotNull Position getSafePosition(@NotNull Collidable collidable);

}
