package me.youhavetrouble.mobrrr.server.player;

import me.youhavetrouble.mobrrr.server.service.player.PlayerProviderData;
import org.jetbrains.annotations.NotNull;

public record DummyPlayerProviderData(String name) implements PlayerProviderData {

    public DummyPlayerProviderData(
            @NotNull String name
    ) {
        this.name = name;
    }

}
