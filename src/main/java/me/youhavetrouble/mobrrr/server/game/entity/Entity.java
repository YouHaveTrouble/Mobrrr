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
    private PositionWithHeight position;

    /**
     * This constructor should not be used directly, it's called by {@link EntityTemplate#createEntity(int)} within the
     * {@link GameMap#spawnEntity(EntityTemplate)}
     *
     * @param id This id needs to be passed to the super constructor for tracking to work properly.
     * @param typeId Number identifying the type of the entity for network serialization
     * @param gameMap The map the entity is on
     * @param position The position of the entity
     */
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

    // FIXME probably needs to accept position with height
    public void setPosition(Position position) {
        this.position = new PositionWithHeight(position.getX(), position.getY(), 0);
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
