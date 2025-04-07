package me.youhavetrouble.mobrrr.server.game.map.terrain.event;

import me.youhavetrouble.mobrrr.server.game.map.terrain.TerrainComponent;
import org.jetbrains.annotations.NotNull;

public class TerrainAddedEvent<T extends TerrainComponent> extends TerrainEvent<T> {
    /**
     * @param terrain the terrain component that was added
     */
    public TerrainAddedEvent(@NotNull T terrain) {
        super(terrain);
    }

}
