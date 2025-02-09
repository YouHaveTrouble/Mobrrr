package me.youhavetrouble.mobrrr;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.youhavetrouble.mobrrr.auth.AuthenticationProvider;
import me.youhavetrouble.mobrrr.auth.DummyAuthProvider;
import me.youhavetrouble.mobrrr.packet.processing.InboundPacketDecoder;
import me.youhavetrouble.mobrrr.packet.processing.InboundPacketResponseEncoder;
import me.youhavetrouble.mobrrr.packet.processing.ProcessingHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.util.concurrent.ThreadFactory;

public class MobaServer {

    private final SocketAddress address;
    private final Logger logger = LoggerFactory.getLogger("Server");
    private boolean running = false;

    private AuthenticationProvider authenticationProvider;

    public MobaServer(
            @NotNull SocketAddress address,
            @Nullable AuthenticationProvider authenticationProvider
    ) {
        this.address = address;
        this.authenticationProvider = authenticationProvider != null ? authenticationProvider : new DummyAuthProvider();
    }

    public MobaServer setAuthProvider(@NotNull AuthenticationProvider provider) {
        if (running) throw new IllegalStateException("cannot set auth provider while server is running");
        this.authenticationProvider = provider;
        return this;
    }

    public AuthenticationProvider getAuthProvider() {
        return authenticationProvider;
    }

    public void start() {
        if (running) throw new IllegalStateException("Server is already running");
        this.running = true;

        ThreadFactory factory = Thread.ofVirtual().name("Netty").factory();
        EventLoopGroup bossGroup = new NioEventLoopGroup(factory);
        EventLoopGroup workerGroup = new NioEventLoopGroup(factory);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(
                                    new InboundPacketDecoder(authenticationProvider),
                                    new InboundPacketResponseEncoder(),
                                    new ProcessingHandler()
                            );
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            logger.info("Starting server on {}", address);
            ChannelFuture future = bootstrap.bind(address).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("Failed to start server", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
