package me.youhavetrouble.mobrrr.server.providers;


import me.youhavetrouble.mobrrr.server.player.DummyPlayer;
import me.youhavetrouble.mobrrr.server.player.DummyPlayerProviderData;
import me.youhavetrouble.mobrrr.server.service.player.PlayerProvider;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class DummyPlayerProvider extends PlayerProvider<String, DummyPlayer, DummyPlayerProviderData> {

    public static final Map<String, DummyPlayer> players = Map.of(
            "player1", new DummyPlayer(new DummyPlayerProviderData("player1")),
            "player2", new DummyPlayer(new DummyPlayerProviderData("player2")),
            "player3", new DummyPlayer(new DummyPlayerProviderData("player3")),
            "player4", new DummyPlayer(new DummyPlayerProviderData("player4")),
            "player5", new DummyPlayer(new DummyPlayerProviderData("player5")),
            "player6", new DummyPlayer(new DummyPlayerProviderData("player6"))
    );

    @Override
    public DummyPlayer createPlayer(DummyPlayerProviderData data) {
        return new DummyPlayer(data);
    }

    @Override
    public @Nullable String matchPlayerIdFromToken(String token) {
        DummyPlayer dummyPlayer = players.get(token);
        return dummyPlayer == null ? null : dummyPlayer.name;
    }

}
