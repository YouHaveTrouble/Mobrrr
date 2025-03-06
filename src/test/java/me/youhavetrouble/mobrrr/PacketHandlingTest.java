package me.youhavetrouble.mobrrr;

import me.youhavetrouble.mobrrr.event.EventDispatcher;
import me.youhavetrouble.mobrrr.server.packet.DummyPacket;
import me.youhavetrouble.mobrrr.server.packet.DummyPacketEvent;
import me.youhavetrouble.mobrrr.server.packet.DummyPacketEventHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PacketHandlingTest {

    @Test
    public void dispatchAndHandlePacketEvent() {
        EventDispatcher dispatcher = new EventDispatcher();
        dispatcher.registerEventHandler(DummyPacketEvent.class, new DummyPacketEventHandler());

        DummyPacketEvent dummyPacketEvent = new DummyPacketEvent(new DummyPacket(null));
        assertFalse(dummyPacketEvent.packet.isHandled());

        dispatcher.dispatchEvent(dummyPacketEvent);

        assertTrue(dummyPacketEvent.packet.isHandled());
    }

}
