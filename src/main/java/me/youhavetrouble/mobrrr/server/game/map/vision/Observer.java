package me.youhavetrouble.mobrrr.server.game.map.vision;

import me.youhavetrouble.mobrrr.server.game.Position;
import org.jetbrains.annotations.NotNull;

public interface Observer {

    @NotNull Vision getVisionArea();

    @NotNull Position getObserverOrigin();

    /**
     * Recalculates vision area
     */
    void updateVision();

}
