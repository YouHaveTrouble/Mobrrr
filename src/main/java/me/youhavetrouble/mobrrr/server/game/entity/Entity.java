package me.youhavetrouble.mobrrr.server.game.entity;

import me.youhavetrouble.mobrrr.server.game.GameMap;
import me.youhavetrouble.mobrrr.server.game.Position;
import org.jetbrains.annotations.NotNull;

public abstract class Entity {

    public final int id;
    public final GameMap gameMap;
    private final Position position;

    public Entity(
            int id,
            @NotNull GameMap gameMap,
            @NotNull Position position
    ) {
        this.id = id;
        this.gameMap = gameMap;
        this.position = position;
    }

    public GameMap getMap() {
        return gameMap;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }



}
