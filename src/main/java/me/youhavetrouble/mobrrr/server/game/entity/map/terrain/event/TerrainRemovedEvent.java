package me.youhavetrouble.mobrrr.server.game.entity.map.terrain.event;

import me.youhavetrouble.mobrrr.server.game.entity.map.terrain.TerrainComponent;
import org.jetbrains.annotations.NotNull;

public class TerrainRemovedEvent<T extends TerrainComponent> extends TerrainEvent<T> {

    /**
     * @param terrain the terrain component that was removed
     */
    public TerrainRemovedEvent(@NotNull T terrain) {
        super(terrain);
    }

}
