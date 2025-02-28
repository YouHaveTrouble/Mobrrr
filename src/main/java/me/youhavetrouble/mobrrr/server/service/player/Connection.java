package me.youhavetrouble.mobrrr.server.service.player;


import me.youhavetrouble.mobrrr.packet.IncomingPacket;
import me.youhavetrouble.mobrrr.packet.OutgoingPacket;
import me.youhavetrouble.mobrrr.packet.phase.login.LoginPacket;
import me.youhavetrouble.mobrrr.packet.phase.play.MoveToPositionPacket;
import me.youhavetrouble.mobrrr.server.service.auth.AuthenticationProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Map;


public class Connection extends Thread {

    public static final Map<Byte, Class<? extends IncomingPacket>> INCOMING_PACKETS = Map.of(
            (byte) 1, MoveToPositionPacket.class
    );

    public final int id;
    public final Socket socket;
    private Phase phase = Phase.LOGIN;
    private boolean running = true;

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private final AuthenticationProvider authenticationProvider;
    private final PlayerProvider<?, ?, ?> playerProvider;
    private final Logger logger;

    private Player player;

    public Connection(Socket socket, int id, AuthenticationProvider authenticationProvider, PlayerProvider<?, ?, ?> playerProvider) {
        super("Connection");
        this.id = id;
        this.socket = socket;
        this.logger = LoggerFactory.getLogger("Connection-" + id);
        this.authenticationProvider = authenticationProvider;
        this.playerProvider = playerProvider;
    }

    @Override
    public void run() {

        try (
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        ) {
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;

            // connect token to player and assign connection to player
            if (this.phase == Phase.LOGIN && !handleLoginPhase()) this.running = false;

            while (running) {
                handlePlayPhasePacket();
            }
            socket.close();
        } catch (SocketTimeoutException e) {
            logger.error("Connection {} timed out", id);
        } catch (Exception e) {
            logger.error("Error while handling connection", e);
        } finally {
            if (this.player != null) this.player.setConnection(null);
        }
    }

    private boolean handleLoginPhase() throws IOException {
        socket.setSoTimeout(3000);
        byte packetId = dataInputStream.readByte();
        if (packetId != 0) {
            logger.warn("Expected authentication packet, got packet with id {}", packetId);
            return false;
        }
        LoginPacket loginPacket = new LoginPacket(dataInputStream);
        if (!authenticationProvider.authenticate(loginPacket.token)) {
            logger.info("Authentication failed");
            return false;
        }
        logger.info("Authenticated successfully");
        socket.setSoTimeout(60 * 1000); // lenghten timeout after login
        socket.setKeepAlive(true);

        Object playerId = this.playerProvider.matchPlayerIdFromToken(loginPacket.token);
        if (playerId == null) {
            logger.error("Could not match any registered players from the token. This is an implementation issue!");
            return false;
        }

        this.player = getPlayer(playerProvider, playerId);
        if (this.player == null) {
            logger.error("Could not find player with id {}. This is an implementation issue!", playerId);
            return false;
        }



        logger.debug("Switching to play phase");
        this.phase = Phase.PLAY;

        Connection oldConnection = this.player.getConnection();
        if (oldConnection != null) oldConnection.disconnect();
        this.player.setConnection(this);
        return true;
    }

    private void handlePlayPhasePacket() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        byte packetId = dataInputStream.readByte();

        Class<? extends IncomingPacket> packetClass = INCOMING_PACKETS.get(packetId);

        if (packetClass == null) {
            logger.warn("Unknown packet id {}", packetId);
            throw new IOException("Unknown packet id " + packetId);
        }

        IncomingPacket incomingPacket = packetClass.getConstructor(DataInputStream.class).newInstance(this.dataInputStream);
        // TODO pass to event system

    }

    private <I> @Nullable Player getPlayer(@NotNull PlayerProvider<I, ?, ?> playerProvider, @NotNull Object playerId) {
        try {
            return playerProvider.getPlayer((I) playerId);
        } catch (ClassCastException e) {
            return null;
        }
    }

    public void disconnect() {
        this.running = false;
        try {
            socket.close();
        } catch (IOException e) {
            logger.error("Error while closing socket", e);
        }
    }

    public void sendPacket(@NotNull OutgoingPacket packet) throws IOException {
        dataOutputStream.writeByte(packet.getId());
        packet.write(dataOutputStream);
        dataOutputStream.flush();
    }

    public enum Phase {
        LOGIN,
        PLAY
    }

}
