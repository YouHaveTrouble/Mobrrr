package me.youhavetrouble.mobrrr.server.service.auth;

public interface AuthenticationProvider {

    boolean authenticate(String token);

}
