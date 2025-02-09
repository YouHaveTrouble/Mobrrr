package me.youhavetrouble.mobrrr.packet.inbound;

public class LoginPacketResponse extends InboundPacketResponse {

    public final boolean success;

    public LoginPacketResponse(boolean success) {
        this.success = success;
    }

}
