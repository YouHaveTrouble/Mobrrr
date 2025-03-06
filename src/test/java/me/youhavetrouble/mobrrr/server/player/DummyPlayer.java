package me.youhavetrouble.mobrrr.server.player;

import me.youhavetrouble.mobrrr.server.game.entity.Entity;
import me.youhavetrouble.mobrrr.server.service.player.Connection;
import me.youhavetrouble.mobrrr.server.service.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class DummyPlayer implements Player {

    private Connection connection;
    public final String name;
    private final Set<Entity<?>> trackedEntities = new HashSet<>();

    public DummyPlayer(DummyPlayerProviderData data) {
        this.name = data.name();
    }

    @Override
    public @Nullable Connection getConnection() {
        return connection;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<? extends Entity<?>> getTrackedEntities() {
        return Collections.unmodifiableCollection(trackedEntities);
    }

    @Override
    public void trackEntity(Entity<?> entity) {
        trackedEntities.add(entity);
    }

    @Override
    public void untrackEntity(Entity<?> entity) {
        trackedEntities.remove(entity);
    }
}
