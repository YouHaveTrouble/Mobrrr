package me.youhavetrouble.mobrrr.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TestClient {

    private static final Logger logger = LoggerFactory.getLogger(TestClient.class);

    public static void main(String[] args) {

        try (Socket socket = new Socket()) {
            InetSocketAddress socketAddress = new InetSocketAddress("localhost", 9999);
            logger.info("Connecting to {}", socketAddress);
            socket.connect(socketAddress);

            logger.info("Connected.");

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            sendLoginPacket(out);

            logger.info("Disconnected.");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }


    }

    private static void sendLoginPacket(DataOutputStream out) throws IOException {
        logger.info("Sending login packet");
        String token = "player1";
        out.writeByte(0);
        out.writeUTF(token);
        out.flush();
    }

}
