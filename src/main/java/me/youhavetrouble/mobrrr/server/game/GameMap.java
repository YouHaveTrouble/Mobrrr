package me.youhavetrouble.mobrrr.server.game;

import me.youhavetrouble.mobrrr.server.game.entity.Entity;
import me.youhavetrouble.mobrrr.server.game.entity.EntityTemplate;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class GameMap {

    private int entityIdCounter = 0;
    private final Map<Integer, Entity> entities = new HashMap<>();

    /**
     * Spawns an entity on the map
     * @param entityTemplate the template of the entity to spawn
     * @return the spawned entity
     * @param <T> the type of the entity
     */
    public <T extends Entity> T spawnEntity(EntityTemplate<T> entityTemplate) {
        T entity = entityTemplate.createEntity(++entityIdCounter);
        entities.put(entity.id, entity);
        return entity;
    }

    @Nullable
    public Entity getEntity(int id) {
        return entities.get(id);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity.id);
    }

}
