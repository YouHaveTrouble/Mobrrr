package me.youhavetrouble.mobrrr.server.game.entity.map.terrain.event;

import me.youhavetrouble.mobrrr.server.game.entity.map.terrain.TerrainComponent;
import org.jetbrains.annotations.NotNull;

public class TerrainAddedEvent<T extends TerrainComponent> extends TerrainEvent<T> {
    /**
     * @param terrain the terrain component that was added
     */
    public TerrainAddedEvent(@NotNull T terrain) {
        super(terrain);
    }

}
