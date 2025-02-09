package me.youhavetrouble.mobrrr.packet.processing;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import me.youhavetrouble.mobrrr.auth.AuthenticationProvider;
import me.youhavetrouble.mobrrr.packet.inbound.LoginPacket;
import me.youhavetrouble.mobrrr.packet.inbound.InboundPacket;
import java.util.List;

public class InboundPacketDecoder extends ReplayingDecoder<InboundPacket<?>> {

    private AuthenticationProvider authenticationProvider;

    public InboundPacketDecoder(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;

    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        int id = byteBuf.readInt();

        switch (id) {
            case 0 -> list.add(new LoginPacket(byteBuf, authenticationProvider));
            default -> {}
        }

    }
}
