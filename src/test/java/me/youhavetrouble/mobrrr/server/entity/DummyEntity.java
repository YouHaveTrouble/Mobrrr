package me.youhavetrouble.mobrrr.server.entity;

import me.youhavetrouble.mobrrr.server.game.entity.map.GameMap;
import me.youhavetrouble.mobrrr.server.game.Position;
import me.youhavetrouble.mobrrr.server.game.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class DummyEntity extends Entity<DummyEntityTemplate> {

    public DummyEntity(int id, int typeId, @NotNull GameMap gameMap, @NotNull Position position) {
        super(id, typeId, gameMap, position);
    }

}
