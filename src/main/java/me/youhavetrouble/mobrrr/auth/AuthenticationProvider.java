package me.youhavetrouble.mobrrr.auth;

public interface AuthenticationProvider {

    boolean authenticate(String token);

}
