package me.youhavetrouble.mobrrr.packet.clientbound;

import me.youhavetrouble.mobrrr.packet.OutgoingPacket;
import org.jetbrains.annotations.Nullable;

import java.io.DataOutputStream;
import java.io.IOException;

public final class DisconnectPacket extends OutgoingPacket {

    public final String reason;

    public DisconnectPacket(@Nullable String reason) {
        if (reason != null && reason.length() > Short.MAX_VALUE) {
            throw new IllegalArgumentException("Kick reason is too long. Should be at most " + Short.MAX_VALUE + " characters.");
        }
        this.reason = reason;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        if (reason == null) return;
        out.writeUTF(reason);
    }

    @Override
    public byte getId() {
        return 0;
    }
}
