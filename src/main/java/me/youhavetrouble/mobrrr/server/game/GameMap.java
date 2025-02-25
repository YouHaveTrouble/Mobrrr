package me.youhavetrouble.mobrrr.server.game;

import me.youhavetrouble.mobrrr.server.game.entity.Entity;
import me.youhavetrouble.mobrrr.server.game.entity.EntityTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class GameMap {

    private static final Logger logger = LoggerFactory.getLogger(GameMap.class);

    private int entityIdCounter = 0;
    private final Map<Integer, Entity<?>> entities = new HashMap<>();

    /**
     * Spawns an entity on the map
     * @param entityTemplate the template of the entity to spawn
     * @return the spawned entity
     * @param <T> the type of the entity
     */
    public <T extends Entity<?>> T spawnEntity(EntityTemplate<T> entityTemplate) {
        T entity = entityTemplate.createEntity(++entityIdCounter);
        entities.put(entity.id, entity);
        logger.debug("Spawned entity of type id {} with id {}", entity.typeId, entity.id);
        return entity;
    }

    @Nullable
    public Entity<?> getEntity(int id) {
        return entities.get(id);
    }

    public void removeEntity(@NotNull Entity<?> entity) {
        if (entities.remove(entity.id) != null) return;
        logger.warn("Tried to remove entity with id {}, but it didn't exist", entity.id);
    }

    public void tick() {
        for (Entity<?> entity : entities.values()) {
            entity.tick();
        }
    }

}
