package me.youhavetrouble.mobrrr.packet;


import io.netty.buffer.ByteBuf;

public abstract class OutboundPacket {

    public abstract void send(ByteBuf byteBuf);

}
