package me.youhavetrouble.mobrrr.server.packet;

import me.youhavetrouble.mobrrr.server.handler.PacketEvent;

public class DummyPacketEvent extends PacketEvent<DummyPacket> {

    public DummyPacketEvent(DummyPacket packet) {
        super(null, packet);
    }

}
