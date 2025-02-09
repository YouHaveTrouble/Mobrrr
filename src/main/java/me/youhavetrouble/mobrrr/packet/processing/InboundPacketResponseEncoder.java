package me.youhavetrouble.mobrrr.packet.processing;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import me.youhavetrouble.mobrrr.packet.OutboundPacket;

public class InboundPacketResponseEncoder extends MessageToByteEncoder<OutboundPacket> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, OutboundPacket outboundPacket, ByteBuf byteBuf) {
        outboundPacket.send(byteBuf);
    }
}
