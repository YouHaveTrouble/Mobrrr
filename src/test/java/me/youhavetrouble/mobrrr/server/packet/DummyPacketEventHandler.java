package me.youhavetrouble.mobrrr.server.packet;

import me.youhavetrouble.mobrrr.event.EventHandler;
import org.jetbrains.annotations.NotNull;

public class DummyPacketEventHandler implements EventHandler<DummyPacketEvent> {

    @Override
    public void handle(@NotNull DummyPacketEvent event) {
        event.packet.setHandled(true);
    }

}
