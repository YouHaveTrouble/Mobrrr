package me.youhavetrouble.mobrrr.packet.phase.play;

import me.youhavetrouble.mobrrr.packet.OutgoingPacket;

import java.io.DataOutputStream;
import java.io.IOException;

public class DespawnEntityPacket extends OutgoingPacket {

    public final int entityId;

    public DespawnEntityPacket(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public byte getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeInt(this.entityId);
    }

}
