package me.youhavetrouble.mobrrr.server.handler;

import me.youhavetrouble.mobrrr.event.Event;
import me.youhavetrouble.mobrrr.packet.Packet;
import me.youhavetrouble.mobrrr.server.service.player.Connection;

public class PacketEvent<P extends Packet> extends Event {

    public final Connection connection;
    public final P packet;

    public PacketEvent(Connection connection, P packet) {
        this.connection = connection;
        this.packet = packet;
    }

}
