package me.youhavetrouble.mobrrr.server.game.map.terrain.event;

import me.youhavetrouble.mobrrr.event.Event;
import me.youhavetrouble.mobrrr.server.game.map.terrain.TerrainComponent;
import org.jetbrains.annotations.NotNull;

public abstract class TerrainEvent<T extends TerrainComponent> extends Event {

    public final T terrainComponent;

    public TerrainEvent(@NotNull T terrain) {
        this.terrainComponent = terrain;
    }

}
