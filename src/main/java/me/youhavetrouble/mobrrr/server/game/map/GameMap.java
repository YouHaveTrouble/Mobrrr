package me.youhavetrouble.mobrrr.server.game.map;

import me.youhavetrouble.mobrrr.event.EventDispatcher;
import me.youhavetrouble.mobrrr.server.game.entity.Entity;
import me.youhavetrouble.mobrrr.server.game.entity.EntityTemplate;
import me.youhavetrouble.mobrrr.server.game.entity.event.EntityRemoveEvent;
import me.youhavetrouble.mobrrr.server.game.entity.event.EntitySpawnEvent;
import me.youhavetrouble.mobrrr.server.game.map.terrain.Terrain;
import me.youhavetrouble.mobrrr.server.game.map.terrain.TerrainComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class GameMap<T extends TerrainComponent> {

    private static final Logger logger = LoggerFactory.getLogger(GameMap.class);

    public final EventDispatcher eventDispatcher;

    private int entityIdCounter = 0;
    private final Map<Integer, Entity<?>> entities = new HashMap<>();
    public final Terrain<T> terrain;

    /**
     * Creates a new game map
     * @param eventDispatcher the event dispatcher to use for events on this map
     */
    public GameMap(
            @NotNull EventDispatcher eventDispatcher,
            @NotNull Terrain<T> terrain
    ) {
        this.eventDispatcher = eventDispatcher;
        this.terrain = terrain;
    }

    /**
     * Spawns an entity on the map and fires {@link EntitySpawnEvent}
     * @param entityTemplate the template of the entity to spawn
     * @return the spawned entity
     * @param <E> the type of the entity
     */
    @NotNull
    public final <E extends Entity<?>> E spawnEntity(@NotNull EntityTemplate<E> entityTemplate) {
        E entity = entityTemplate.createEntity(++entityIdCounter);
        if (entity == null) {
            throw new IllegalArgumentException("Entity template returned null entity");
        }
        if (entities.containsKey(entity.id)) {
            throw new IllegalArgumentException("Entity with id " + entity.id + " already exists");
        }
        entities.put(entity.id, entity);
        eventDispatcher.dispatchEvent(new EntitySpawnEvent<>(entity));
        logger.debug("Spawned entity of type id {} with id {}", entity.typeId, entity.id);
        return entity;
    }

    @Nullable
    public final Entity<?> getEntity(int id) {
        return entities.get(id);
    }

    public final Map<Integer, Entity<?>> getEntities() {
        return Collections.unmodifiableMap(entities);
    }

    /**
     * Get the terrain of the map
     * @return the terrain of the map
     */
    public final Terrain<T> getTerrain() {
        return terrain;
    }

    /**
     * Removes an entity from the map and fires {@link EntityRemoveEvent}
     * @param entity the entity to remove
     */
    public final void removeEntity(@NotNull Entity<?> entity) {
        if (entities.remove(entity.id) != null) {
            eventDispatcher.dispatchEvent(new EntityRemoveEvent<>(entity));
            return;
        }
        logger.warn("Tried to remove entity with id {}, but it didn't exist", entity.id);
    }

    /**
     * Ticks the map and all entities on it
     */
    public void tick() {
        for (Entity<?> entity : entities.values()) {
            entity.tick();
        }
    }

}
