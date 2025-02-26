package me.youhavetrouble.mobrrr.packet;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class OutgoingPacket extends Packet {

    /**
     * Write the packet data to the output stream.<br>
     * Packet id is written before this method is called.<br>
     * Flush is called after this method is called.
     * @param out output stream
     * @throws IOException if an I/O error occurs
     */
    public abstract void write(DataOutputStream out) throws IOException;

}
