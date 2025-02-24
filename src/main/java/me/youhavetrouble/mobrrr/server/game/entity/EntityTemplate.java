package me.youhavetrouble.mobrrr.server.game.entity;

public abstract class EntityTemplate<T extends Entity> {

    public abstract T createEntity(int id);

}
