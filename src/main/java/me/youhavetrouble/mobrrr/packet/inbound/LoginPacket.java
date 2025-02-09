package me.youhavetrouble.mobrrr.packet.inbound;

import io.netty.buffer.ByteBuf;
import me.youhavetrouble.mobrrr.auth.AuthenticationProvider;

import java.nio.charset.StandardCharsets;

public class LoginPacket extends InboundPacket<LoginPacketResponse> {

    public final String token;
    private final AuthenticationProvider authenticationProvider;

    public LoginPacket(String token, AuthenticationProvider authenticationProvider) {
        this.token = token;
        this.authenticationProvider = authenticationProvider;
    }

    public LoginPacket(ByteBuf byteBuf, AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        this.token = byteBuf.readCharSequence(byteBuf.readInt(), StandardCharsets.UTF_8).toString();
    }

    @Override
    public LoginPacketResponse handle() {
        return new LoginPacketResponse(authenticationProvider.authenticate(token));
    }
}
