package me.youhavetrouble.mobrrr.server.game;

import me.youhavetrouble.mobrrr.server.service.player.Player;

import java.util.Collection;

/**
 * Represents a game that can be played by multiple players.
 * @param <I> The type of the player ID
 * @param <P> The type of the player
 */
public abstract class Game<I, P extends Player> {

    public Game() {

    }

    /**
     * Get all players in the game. This should be a read-only collection.
     * @return A collection of all players in the game.
     */
    public abstract Collection<P> getPlayers();

    public abstract P getPlayer(I playerId);

    /**
     * Progress the game by one tick.
     */
    public abstract void tick();

}
