package me.youhavetrouble.mobrrr.server.service.packet;

import me.youhavetrouble.mobrrr.packet.IncomingPacket;

import java.util.HashMap;
import java.util.Map;

public final class PacketRegistry {

    private final Map<Byte, Class<? extends IncomingPacket>> serverboundPackets = new HashMap<>();
    private boolean frozen = false;

    public PacketRegistry() {}

    /**
     * Register a serverbound packet to be associated with a packet ID.<br>
     * This method should be called during the initialization of the server.<br>
     * ID 0 is reserved for the login packet and attempting to register a packet with ID 0 will throw an exception.
     * @param id The packet ID
     * @param packetClass The class of the packet
     */
    public void registerServerboundPacket(byte id, Class<? extends IncomingPacket> packetClass) {
        if (frozen) {
            throw new IllegalStateException("Cannot register packets after the registry has been frozen");
        }
        if (id == 0) {
            throw new IllegalArgumentException("Packet ID 0 is reserved for the login packet");
        }
        serverboundPackets.put(id, packetClass);
    }

    public Class<? extends IncomingPacket> getServerboundPacket(byte id) {
        return serverboundPackets.get(id);
    }

    public void freeze() {
        this.frozen = true;
    }

}
