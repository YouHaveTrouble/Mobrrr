package me.youhavetrouble.mobrrr.server;

import me.youhavetrouble.mobrrr.server.providers.DummyAuthProvider;
import me.youhavetrouble.mobrrr.server.providers.DummyPlayerProvider;

import java.net.InetSocketAddress;

public class TestServer {

    public static void main(String[] args) {

        MobaServer server = new MobaServer(
                new InetSocketAddress("localhost", 9999),
                new DummyAuthProvider(),
                new DummyPlayerProvider()
        );
        server.start();

    }

}
