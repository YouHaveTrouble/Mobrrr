package me.youhavetrouble.mobrrr.packet.clientbound;

import me.youhavetrouble.mobrrr.packet.OutgoingPacket;
import me.youhavetrouble.mobrrr.server.game.Position;
import me.youhavetrouble.mobrrr.server.game.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.io.DataOutputStream;
import java.io.IOException;

public class MoveEntityPacket extends OutgoingPacket {

    public final int entityId;
    private final Position position;

    public MoveEntityPacket(@NotNull Entity<?> entity) {
        this.entityId = entity.id;
        this.position = entity.getPosition().clone();
    }

    @Override
    public byte getId() {
        return 5;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeInt(entityId);
        out.writeDouble(position.x());
        out.writeDouble(position.y());
        out.writeDouble(position.height());
    }

}
