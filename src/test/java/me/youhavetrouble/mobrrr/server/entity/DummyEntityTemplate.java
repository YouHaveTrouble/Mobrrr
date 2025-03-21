package me.youhavetrouble.mobrrr.server.entity;

import me.youhavetrouble.mobrrr.server.game.GameMap;
import me.youhavetrouble.mobrrr.server.game.Position;
import me.youhavetrouble.mobrrr.server.game.PositionWithHeight;
import me.youhavetrouble.mobrrr.server.game.entity.EntityTemplate;

public class DummyEntityTemplate extends EntityTemplate<DummyEntity> {

    public final GameMap gameMap;
    private final PositionWithHeight position;

    public DummyEntityTemplate(GameMap gameMap, Position position) {
        this.gameMap = gameMap;
        this.position = new PositionWithHeight(position.getX(), position.getY(), 0);
    }

    @Override
    public int getEntityTypeId() {
        return 0;
    }

    @Override
    public DummyEntity createEntity(int id) {
        return new DummyEntity(id, getEntityTypeId(), this.gameMap, position);
    }

}
