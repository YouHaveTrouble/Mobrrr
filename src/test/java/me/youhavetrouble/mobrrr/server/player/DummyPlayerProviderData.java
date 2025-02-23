package me.youhavetrouble.mobrrr.server.player;

import me.youhavetrouble.mobrrr.server.service.player.PlayerProviderData;
import org.jetbrains.annotations.NotNull;

public class DummyPlayerProviderData extends PlayerProviderData {

    public final String name;

    public DummyPlayerProviderData(
            @NotNull String name
    ) {
        this.name = name;
    }

}
