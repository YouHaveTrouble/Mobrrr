package me.youhavetrouble.mobrrr.packet.phase.play;

import me.youhavetrouble.mobrrr.packet.IncomingPacket;
import me.youhavetrouble.mobrrr.server.game.Position;

import java.io.DataInputStream;
import java.io.IOException;

public class MoveToPositionPacket extends IncomingPacket {

    public final Position position;

    public MoveToPositionPacket(DataInputStream dataInputStream) throws IOException {
        double x = dataInputStream.readDouble();
        double y = dataInputStream.readDouble();
        this.position = new Position(x, y);
    }

    @Override
    public byte getId() {
        return 1;
    }

}
