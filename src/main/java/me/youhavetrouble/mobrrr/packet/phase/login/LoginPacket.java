package me.youhavetrouble.mobrrr.packet.phase.login;

import me.youhavetrouble.mobrrr.packet.IncomingPacket;

import java.io.DataInputStream;
import java.io.IOException;

public class LoginPacket extends IncomingPacket {

    public final String token;

    public LoginPacket(DataInputStream dataInputStream) throws IOException {
        int tokenLength = Math.min(dataInputStream.readShort(), 512);
        this.token = new String(dataInputStream.readNBytes(tokenLength));
    }

    @Override
    public byte getId() {
        return 0;
    }
}
