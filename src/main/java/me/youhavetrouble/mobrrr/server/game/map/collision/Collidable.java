package me.youhavetrouble.mobrrr.server.game.map.collision;

import org.jetbrains.annotations.NotNull;

import java.awt.geom.Area;

public interface Collidable {

    /**
     * Get the collision area of this collidable
     * @return the collision area
     */
    @NotNull Area getCollisionArea();

}
