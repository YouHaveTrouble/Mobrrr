package me.youhavetrouble.mobrrr.server.handler;

import me.youhavetrouble.mobrrr.packet.phase.login.LoginPacket;
import me.youhavetrouble.mobrrr.server.service.player.Connection;
import me.youhavetrouble.mobrrr.server.service.player.Player;
import org.jetbrains.annotations.Nullable;

public final class LoginPacketEvent extends PacketEvent<LoginPacket> {

    private boolean authenticated = false;
    private Player resolvedPlayer = null;

    public LoginPacketEvent(Connection connection, LoginPacket packet) {
        super(connection, packet);
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public @Nullable Player getResolvedPlayer() {
        return this.resolvedPlayer;
    }

    public void setResolvedPlayer(@Nullable Player resolvedPlayer) {
        this.resolvedPlayer = resolvedPlayer;
    }

}
