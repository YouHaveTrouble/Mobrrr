package me.youhavetrouble.mobrrr.server.providers;


import me.youhavetrouble.mobrrr.server.player.DummyPlayer;
import me.youhavetrouble.mobrrr.server.player.DummyPlayerProviderData;
import me.youhavetrouble.mobrrr.server.service.player.PlayerProvider;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class DummyPlayerProvider extends PlayerProvider<String, DummyPlayer, DummyPlayerProviderData> {

    public static final Map<String, DummyPlayerProviderData> players = Map.of(
            "player1", new DummyPlayerProviderData("player1"),
            "player2", new DummyPlayerProviderData("player2"),
            "player3", new DummyPlayerProviderData("player3"),
            "player4", new DummyPlayerProviderData("player4"),
            "player5", new DummyPlayerProviderData("player5"),
            "player6", new DummyPlayerProviderData("player6")
    );

    public DummyPlayerProvider() {
        for (DummyPlayerProviderData data : players.values()) {
            DummyPlayer dummyPlayer = createPlayer(data);
            registerPlayer(data.name(), dummyPlayer);
        }
    }

    @Override
    public DummyPlayer createPlayer(DummyPlayerProviderData data) {
        return new DummyPlayer(data);
    }

    @Override
    public @Nullable String matchPlayerIdFromToken(String token) {
        return getPlayer(token) == null ? null : token;
    }

}
