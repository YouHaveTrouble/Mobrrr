package me.youhavetrouble.mobrrr.server.entity;

import me.youhavetrouble.mobrrr.server.game.map.GameMap;
import me.youhavetrouble.mobrrr.server.game.Position;
import me.youhavetrouble.mobrrr.server.game.entity.EntityTemplate;
import me.youhavetrouble.mobrrr.server.game.map.terrain.TerrainComponent;

public class DummyEntityTemplate extends EntityTemplate<DummyEntity> {

    public final GameMap<TerrainComponent> gameMap;
    private final Position position;

    public DummyEntityTemplate(GameMap<TerrainComponent> gameMap, Position position) {
        this.gameMap = gameMap;
        this.position = new Position(position.x(), position.y(), position.height());
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
