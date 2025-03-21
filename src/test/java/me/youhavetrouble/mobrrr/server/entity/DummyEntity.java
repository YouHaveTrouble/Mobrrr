package me.youhavetrouble.mobrrr.server.entity;

import me.youhavetrouble.mobrrr.server.game.GameMap;
import me.youhavetrouble.mobrrr.server.game.PositionWithHeight;
import me.youhavetrouble.mobrrr.server.game.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class DummyEntity extends Entity<DummyEntityTemplate> {

    public DummyEntity(int id, int typeId, @NotNull GameMap gameMap, @NotNull PositionWithHeight position) {
        super(id, typeId, gameMap, position);
    }

}
