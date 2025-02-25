package me.youhavetrouble.mobrrr.server.game.entity;

public abstract class EntityTemplate<T extends Entity<?>> {

    public abstract int getEntityTypeId();

    public abstract T createEntity(int id);

}
