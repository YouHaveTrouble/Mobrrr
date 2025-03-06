package me.youhavetrouble.mobrrr.server.service.player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @param <I> The type of the player ID. UUID for example.
 * @param <P> The type of the player.
 * @param <D> The type of the player provider data that is used to construct Player object.
 */
public abstract class PlayerProvider<I, P extends Player, D extends PlayerProviderData> {

    private final Map<I, P> registeredPlayers = new HashMap<>();

    /**
     * Create a player object from the data. All created players should be registered using
     * {@link #registerPlayer(Object, Player)} so they can be matched later.
     * @param data The data to create the player object from.
     * @return The created player object.
     */
    public abstract P createPlayer(D data);

    /**
     * Get all players registered in the provider. This is a read-only map.
     * @return A map of all players registered in the provider.
     */
    public final @NotNull Map<I, P> getPlayers() {
        return Collections.unmodifiableMap(registeredPlayers);
    }

    public final @Nullable P getPlayer(I id) {
        return registeredPlayers.get(id);
    }

    /**
     * Register a player object after creating it
     * @param id The ID of the player
     * @param player The player object
     * @throws IllegalArgumentException if a player with the same ID is already registered
     */
    public final void registerPlayer(@NotNull I id, @NotNull P player) throws IllegalArgumentException {
        if (registeredPlayers.containsKey(id)) {
            throw new IllegalArgumentException("Player with id" + id + " is already registered");
        }
        registeredPlayers.put(id, player);
    }

    /**
     * Matches a player from a token after authentication.
     * @param token The token to match the player from.
     * @return The matched player, or null if no player was found.
     */
    public abstract @Nullable I matchPlayerIdFromToken(String token);

}
