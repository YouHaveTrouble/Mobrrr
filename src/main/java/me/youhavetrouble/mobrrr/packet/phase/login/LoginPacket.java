package me.youhavetrouble.mobrrr.packet.phase.login;

import me.youhavetrouble.mobrrr.packet.IncomingPacket;

import java.io.DataInputStream;
import java.io.IOException;

public class LoginPacket extends IncomingPacket {

    public final String token;

    public LoginPacket(DataInputStream dataInputStream) throws IOException {
        this.token = dataInputStream.readUTF();
    }

    @Override
    public byte getId() {
        return 0;
    }
}
