package me.youhavetrouble.mobrrr.event;

import org.jetbrains.annotations.NotNull;

public interface EventHandler<E extends Event> {

    void handle(@NotNull E event);

}
