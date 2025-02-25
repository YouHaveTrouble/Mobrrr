package me.youhavetrouble.mobrrr.server.game.entity;

import me.youhavetrouble.mobrrr.server.game.GameMap;
import me.youhavetrouble.mobrrr.server.game.Position;
import me.youhavetrouble.mobrrr.server.game.PositionWithHeight;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Entity<T extends EntityTemplate<?>> {

    public final int id;
    public final int typeId;
    public final GameMap gameMap;
    private final PositionWithHeight position;

    public Entity(
            int id,
            int typeId,
            @NotNull GameMap gameMap,
            @NotNull PositionWithHeight position
    ) {
        this.id = id;
        this.typeId = typeId;
        this.gameMap = gameMap;
        this.position = position;
    }

    public GameMap getMap() {
        return gameMap;
    }

    public PositionWithHeight getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public void tick() {
    }

    /**
     * Converting the entity to a template to save or use to clone the entity
     * @return the entity as a template
     */
    public @Nullable T asTemplate() {
        return null;
    }

    /**
     * Serializes extra data of the entity to be sent to the client
     * @return the serialized extra data
     */
    public byte[] serializeExtraData() {
        return new byte[0];
    }

}
