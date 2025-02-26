package me.youhavetrouble.mobrrr.server.service.player;


import me.youhavetrouble.mobrrr.packet.OutgoingPacket;
import me.youhavetrouble.mobrrr.packet.phase.login.LoginPacket;
import me.youhavetrouble.mobrrr.server.service.auth.AuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class Connection extends Thread {

    public final int id;
    public final Socket socket;
    private Phase phase = Phase.LOGIN;
    private boolean running = true;

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private final AuthenticationProvider authenticationProvider;
    private final Logger logger;

    public Connection(Socket socket, int id, AuthenticationProvider authenticationProvider) {
        super("Connection");
        this.id = id;
        this.socket = socket;
        this.logger = LoggerFactory.getLogger("Connection-" + id);
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public void run() {

        try (
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        ) {

            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;

            if (this.phase == Phase.LOGIN) {
                socket.setSoTimeout(3000);
                byte packetId = dataInputStream.readByte();
                if (packetId != 0) {
                    logger.warn("Expected authentication packet, got packet with id {}", packetId);
                    socket.close();
                    return;
                }
                LoginPacket loginPacket = new LoginPacket(dataInputStream);
                if (authenticationProvider.authenticate(loginPacket.getToken())) {
                    socket.setKeepAlive(true);
                    socket.setTcpNoDelay(true);
                    this.phase = Phase.PLAY;
                    logger.info("Authenticated successfully");
                    // TODO assign to player object

                } else {
                    logger.error("Authentication failed");
                    this.running = false;
                    socket.close();
                }
            }

            socket.setSoTimeout(60 * 1000);

            while (running) {
                byte packetId = dataInputStream.readByte();
                switch (packetId) {
                    default -> logger.error("Unknown packet id {}", packetId);
                }
            }

            socket.close();

        } catch (SocketTimeoutException e) {
            logger.error("Connection {} timed out", id);
        } catch (IOException e) {
            logger.error("Error while handling connection", e);
        }


    }

    public void sendPacket(OutgoingPacket packet) throws IOException {
        dataOutputStream.writeByte(packet.getId());
        packet.write(dataOutputStream);
        dataOutputStream.flush();
    }

    public enum Phase {
        LOGIN,
        PLAY
    }

}
