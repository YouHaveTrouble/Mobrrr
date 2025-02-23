package me.youhavetrouble.mobrrr.server.service.player;

import org.jetbrains.annotations.Nullable;

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

}
