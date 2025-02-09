package me.youhavetrouble.mobrrr;

import java.net.InetSocketAddress;

public class TestServer {

    public static void main(String[] args) {

        MobaServer server = new MobaServer(
                new InetSocketAddress("0.0.0.0", 10000),
                null
        );
        server.start();

    }

}
