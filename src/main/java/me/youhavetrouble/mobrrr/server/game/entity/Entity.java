package me.youhavetrouble.mobrrr.server.game.entity;

import me.youhavetrouble.mobrrr.server.game.map.GameMap;
import me.youhavetrouble.mobrrr.server.game.Position;
import me.youhavetrouble.mobrrr.server.game.map.terrain.TerrainComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Abstract class for all entities in the game
 * @param <T> The template class for the entity
 */
public abstract class Entity<T extends EntityTemplate<?>> {

    public final int id;
    public final int typeId;
    public final GameMap<TerrainComponent> gameMap;
    private Position position;

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
            @NotNull GameMap<TerrainComponent> gameMap,
            @NotNull Position position
    ) {
        this.id = id;
        this.typeId = typeId;
        this.gameMap = gameMap;
        this.position = position;
    }

    public GameMap<? extends TerrainComponent> getMap() {
        return gameMap;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position.clone();
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
