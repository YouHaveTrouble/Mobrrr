package me.youhavetrouble.mobrrr.server.service.player;

public abstract class PlayerProvider<P extends Player, D extends PlayerProviderData> {

    public abstract P createPlayer(D data);

}
