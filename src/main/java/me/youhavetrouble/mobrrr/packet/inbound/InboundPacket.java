package me.youhavetrouble.mobrrr.packet.inbound;


public abstract class InboundPacket <T extends InboundPacketResponse> {

    public abstract T handle();

}
