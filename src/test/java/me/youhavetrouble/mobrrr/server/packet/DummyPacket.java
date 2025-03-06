package me.youhavetrouble.mobrrr.server.packet;

import me.youhavetrouble.mobrrr.packet.Packet;

import java.io.DataInputStream;

public class DummyPacket extends Packet {

    private boolean handled = false;

    public DummyPacket(DataInputStream in) {}

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    @Override
    public byte getId() {
        return 1;
    }
}
