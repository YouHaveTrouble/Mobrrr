package me.youhavetrouble.mobrrr.server;

import me.youhavetrouble.mobrrr.server.service.auth.AuthenticationProvider;
import me.youhavetrouble.mobrrr.server.service.player.Connection;
import me.youhavetrouble.mobrrr.server.service.player.Player;
import me.youhavetrouble.mobrrr.server.service.player.PlayerProvider;
import me.youhavetrouble.mobrrr.server.service.player.PlayerProviderData;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ThreadFactory;

public class MobaServer {

    private final InetSocketAddress address;
    private final Logger logger = LoggerFactory.getLogger("Server");
    private ServerSocket serverSocket;
    private boolean running = false;
    private int connectionId = 0;

    public final AuthenticationProvider authenticationProvider;
    public final PlayerProvider<? extends Player, ? extends PlayerProviderData> playerProvider;

    public MobaServer(
            @NotNull InetSocketAddress address,
            @NotNull AuthenticationProvider authenticationProvider,
            @NotNull PlayerProvider<? extends Player, ? extends PlayerProviderData> playerProvider
    ) {
        this.address = address;
        this.authenticationProvider = authenticationProvider;
        this.playerProvider = playerProvider;
    }

    public void start() {
        if (running) throw new IllegalStateException("Server is already running");
        this.running = true;

        ThreadFactory factory = Thread.ofVirtual().name("Socket").factory();

        try {
            this.serverSocket = new ServerSocket(address.getPort(), 50);
            logger.info("Starting server on {}:{}", address.getHostString(), address.getPort());

            while (running) {
                Socket socket = serverSocket.accept();
                factory.newThread(new Connection(socket, ++connectionId, authenticationProvider)).start();
            }

            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void stop() throws IOException {
        if (!running) throw new IllegalStateException("Server is not running");
        serverSocket.close();
        this.running = false;
    }

}
