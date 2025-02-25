package me.youhavetrouble.mobrrr.packet.phase.play;

import me.youhavetrouble.mobrrr.packet.OutgoingPacket;
import me.youhavetrouble.mobrrr.server.game.entity.Entity;

import java.io.DataOutputStream;
import java.io.IOException;

public class SpawnEntityPacket extends OutgoingPacket {

    public final Entity<?> entity;

    public SpawnEntityPacket(Entity<?> entity) {
        this.entity = entity;
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        byte[] extraData = entity.serializeExtraData();
        out.writeInt(getId());
        out.writeInt(entity.typeId);
        out.writeInt(entity.id);
        out.writeDouble(entity.getPosition().getX());
        out.writeDouble(entity.getPosition().getY());
        out.writeDouble(entity.getPosition().getHeight());
        out.writeInt(extraData.length);
        out.write(extraData);
    }

}
