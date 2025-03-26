package me.youhavetrouble.mobrrr.server.service.player;


import me.youhavetrouble.mobrrr.packet.IncomingPacket;
import me.youhavetrouble.mobrrr.packet.OutgoingPacket;
import me.youhavetrouble.mobrrr.packet.Packet;
import me.youhavetrouble.mobrrr.packet.clientbound.DisconnectPacket;
import me.youhavetrouble.mobrrr.packet.serverbound.LoginPacket;
import me.youhavetrouble.mobrrr.server.MobaServer;
import me.youhavetrouble.mobrrr.server.handler.LoginPacketEvent;
import me.youhavetrouble.mobrrr.server.handler.PacketEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;


public class Connection extends Thread {

    public final int id;
    public final Socket socket;
    private Phase phase = Phase.LOGIN;

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private final MobaServer server;
    private final Logger logger;

    private Player player;

    public Connection(
            Socket socket,
            int id,
            MobaServer server
    ) {
        super("Connection");
        this.id = id;
        this.socket = socket;
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.server = server;
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
            if (this.phase == Phase.LOGIN) {
                handleLoginPhase();
            }

            while (phase == Phase.PLAY) {
                handlePlayPhasePacket();
            }
            socket.close();
        } catch (SocketTimeoutException e) {
            logger.warn("Connection timed out");
        } catch (SocketException e) {
            logger.info("Connection closed");
        }
        catch (EOFException e) {
            logger.info("Connection closed by client");
        } catch (Exception e) {
            logger.error("Error while handling connection", e);
        } finally {
            if (this.player != null) this.player.setConnection(null);
        }
    }

    private void handleLoginPhase() throws IOException {
        socket.setSoTimeout(3000);
        byte packetId = dataInputStream.readByte();
        if (packetId != 0) {
            logger.warn("Expected authentication packet, got packet with id {}", packetId);
            this.disconnect("Expected authentication packet");
            return;
        }
        LoginPacket loginPacket = new LoginPacket(dataInputStream);
        LoginPacketEvent loginPacketEvent = new LoginPacketEvent(this, loginPacket);
        this.server.eventDispatcher.dispatchEvent(loginPacketEvent);

        if (!loginPacketEvent.isAuthenticated()) {
            logger.info("Authentication failed");
            this.disconnect("Authentication failed");
            return;
        }
        logger.info("Authenticated successfully");
        socket.setSoTimeout(0);
        socket.setKeepAlive(true);

        this.player = loginPacketEvent.getResolvedPlayer();
        if (this.player == null) {
            logger.error("Could not resolve player from login token. This is auth/player resolver implementation error");
            this.disconnect();
            return;
        }

        logger.debug("Player resolved. Switching to play phase");
        this.phase = Phase.PLAY;

        Connection oldConnection = this.player.getConnection();
        if (oldConnection != null) oldConnection.disconnect("Logged in from another location");
        this.player.setConnection(this);
    }

    private void handlePlayPhasePacket() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        byte packetId = dataInputStream.readByte();

        Class<? extends IncomingPacket> packetClass = this.server.packetRegistry.getServerboundPacket(packetId);

        if (packetClass == null) {
            logger.warn("Unknown packet id {}", packetId);
            throw new IOException("Unknown packet id " + packetId);
        }

        IncomingPacket incomingPacket = packetClass.getConstructor(DataInputStream.class).newInstance(this.dataInputStream);
        PacketEvent<? extends Packet> packetEvent = new PacketEvent<>(this, incomingPacket);
        this.server.eventDispatcher.dispatchEvent(packetEvent);

    }

    public void disconnect(@Nullable String reason) {
        logger.info("Disconnecting");
        this.phase = Phase.DISCONNECTED;
        if (this.player != null) this.player.setConnection(null);
        try {
            sendPacket(new DisconnectPacket(reason));
            socket.close();
        } catch (IOException e) {
            logger.error("Error while closing socket", e);
        }
    }

    public void disconnect() {
        disconnect(null);
    }

    public void sendPacket(@NotNull OutgoingPacket packet) throws IOException {
        dataOutputStream.writeByte(packet.getId());
        packet.write(dataOutputStream);
        dataOutputStream.flush();
    }

    public enum Phase {
        LOGIN,
        PLAY,
        DISCONNECTED
    }

}
