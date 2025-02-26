package me.youhavetrouble.mobrrr.packet.phase.play;

import me.youhavetrouble.mobrrr.packet.OutgoingPacket;
import me.youhavetrouble.mobrrr.server.game.entity.Entity;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Updates entity's extra data on the client
 */
public class UpdateEntityPacket extends OutgoingPacket {

    public final int entityId;

    public final byte[] extraData;

    public UpdateEntityPacket(Entity<?> entity) {
        this.entityId = entity.id;
        this.extraData = entity.serializeExtraData();
    }

    @Override
    public byte getId() {
        return 3;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeInt(entityId);
        out.writeInt(this.extraData.length);
        out.write(this.extraData);
    }

}
