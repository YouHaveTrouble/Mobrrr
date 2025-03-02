package me.youhavetrouble.mobrrr.server.handler;

import me.youhavetrouble.mobrrr.event.EventHandler;
import me.youhavetrouble.mobrrr.server.service.auth.AuthenticationProvider;
import me.youhavetrouble.mobrrr.server.service.player.Player;
import me.youhavetrouble.mobrrr.server.service.player.PlayerProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class LoginPacketEventHandler implements EventHandler<LoginPacketEvent> {

    private final AuthenticationProvider authenticationProvider;
    private final PlayerProvider<?,?,?> playerProvider;

    public LoginPacketEventHandler(
            AuthenticationProvider authenticationProvider,
            PlayerProvider<?,?,?> playerProvider
    ) {
        this.authenticationProvider = authenticationProvider;
        this.playerProvider = playerProvider;
    }

    @Override
    public void handle(@NotNull LoginPacketEvent event) {
        String token = event.packet.token;
        boolean authenticated = authenticationProvider.authenticate(token);
        event.setAuthenticated(authenticated);
        if (!authenticated) return;
        Object playerId = playerProvider.matchPlayerIdFromToken(token);
        if (playerId == null) return;
        event.setResolvedPlayer(getPlayer(playerProvider, playerId));
    }

    @SuppressWarnings("unchecked")
    @Nullable
    private <I> Player getPlayer(@NotNull PlayerProvider<I, ?, ?> playerProvider, @NotNull Object playerId) {
        try {
            return playerProvider.getPlayer((I) playerId);
        } catch (ClassCastException e) {
            return null;
        }
    }
}
