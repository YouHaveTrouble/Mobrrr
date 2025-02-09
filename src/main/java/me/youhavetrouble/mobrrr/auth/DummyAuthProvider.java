package me.youhavetrouble.mobrrr.auth;

public class DummyAuthProvider implements AuthenticationProvider {

    @Override
    public boolean authenticate(String token) {
        return true;
    }

}
