package me.youhavetrouble.mobrrr.packet.processing;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import me.youhavetrouble.mobrrr.packet.inbound.InboundPacket;

public class ProcessingHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        if (msg instanceof InboundPacket<?> packet) {
            ChannelFuture future = ctx.writeAndFlush(packet.handle());
            future.addListener(ChannelFutureListener.CLOSE);
        }

        throw new IllegalArgumentException("Unknown packet type");

    }

}
