package me.youhavetrouble.mobrrr.packet.clientbound;

import me.youhavetrouble.mobrrr.packet.OutgoingPacket;
import me.youhavetrouble.mobrrr.server.game.Position;
import me.youhavetrouble.mobrrr.server.game.entity.Entity;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Spawns an entity on the client
 */
public class SpawnEntityPacket extends OutgoingPacket {

    public final int entityId, entityTypeId;
    public final Position position;
    public final byte[] extraData;

    public SpawnEntityPacket(Entity<?> entity) {
        this.entityId = entity.id;
        this.entityTypeId = entity.typeId;
        this.position = entity.getPosition().clone();
        this.extraData = entity.serializeExtraData();
    }

    @Override
    public byte getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeInt(entityTypeId);
        out.writeInt(entityId);
        out.writeDouble(position.x());
        out.writeDouble(position.y());
        out.writeDouble(position.height());
        out.writeInt(extraData.length);
        out.write(extraData);
    }

}
