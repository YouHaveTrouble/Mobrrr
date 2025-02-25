package me.youhavetrouble.mobrrr.packet;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class OutgoingPacket extends Packet{

    public abstract void write(DataOutputStream out) throws IOException;

}
