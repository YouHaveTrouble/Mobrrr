package me.youhavetrouble.mobrrr.server.providers;

import me.youhavetrouble.mobrrr.server.service.auth.AuthenticationProvider;

public class DummyAuthProvider implements AuthenticationProvider {

    @Override
    public boolean authenticate(String token) {
        return DummyPlayerProvider.players.containsKey(token);
    }

}
