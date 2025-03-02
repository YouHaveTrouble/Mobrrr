package me.youhavetrouble.mobrrr.server.service.player;


import me.youhavetrouble.mobrrr.event.EventDispatcher;
import me.youhavetrouble.mobrrr.packet.IncomingPacket;
import me.youhavetrouble.mobrrr.packet.OutgoingPacket;
import me.youhavetrouble.mobrrr.packet.phase.login.LoginPacket;
import me.youhavetrouble.mobrrr.packet.phase.play.MoveToPositionPacket;
import me.youhavetrouble.mobrrr.server.handler.LoginPacketEvent;
import org.jetbrains.annotations.NotNull;
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

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private final EventDispatcher eventDispatcher;
    private final Logger logger;

    private Player player;

    public Connection(
            Socket socket,
            int id,
            EventDispatcher eventDispatcher
    ) {
        super("Connection");
        this.id = id;
        this.socket = socket;
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.eventDispatcher = eventDispatcher;
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
        } catch (EOFException e) {
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
            this.disconnect();
            return;
        }
        LoginPacket loginPacket = new LoginPacket(dataInputStream);
        LoginPacketEvent loginPacketEvent = new LoginPacketEvent(this, loginPacket);
        this.eventDispatcher.dispatchEvent(loginPacketEvent);

        if (!loginPacketEvent.isAuthenticated()) {
            logger.info("Authentication failed");
            this.disconnect();
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
        if (oldConnection != null) oldConnection.disconnect();
        this.player.setConnection(this);
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



    public void disconnect() {
        logger.info("Disconnecting");
        this.phase = Phase.DISCONNECTED;
        if (this.player != null) this.player.setConnection(null);
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
        PLAY,
        DISCONNECTED
    }

}
