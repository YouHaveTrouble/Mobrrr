package me.youhavetrouble.mobrrr.server.player;

import me.youhavetrouble.mobrrr.server.service.player.Connection;
import me.youhavetrouble.mobrrr.server.service.player.Player;
import org.jetbrains.annotations.Nullable;

public class DummyPlayer implements Player {

    private Connection connection;
    private String name;


    public DummyPlayer(DummyPlayerProviderData data) {

    }

    @Override
    public @Nullable Connection getConnection() {
        return connection;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
