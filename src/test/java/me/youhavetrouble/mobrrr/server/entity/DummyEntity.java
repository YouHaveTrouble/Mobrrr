package me.youhavetrouble.mobrrr.server.entity;

import me.youhavetrouble.mobrrr.server.game.map.GameMap;
import me.youhavetrouble.mobrrr.server.game.Position;
import me.youhavetrouble.mobrrr.server.game.entity.Entity;
import me.youhavetrouble.mobrrr.server.game.map.terrain.TerrainComponent;
import org.jetbrains.annotations.NotNull;

public class DummyEntity extends Entity<DummyEntityTemplate> {

    public DummyEntity(int id, int typeId, @NotNull GameMap<TerrainComponent> gameMap, @NotNull Position position) {
        super(id, typeId, gameMap, position);
    }

}
