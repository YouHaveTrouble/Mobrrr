package me.youhavetrouble.mobrrr.server.game.entity.map.vision;

public interface Observer {

    Vision getVisionArea();

    /**
     * Recalculates vision area
     */
    void updateVision();

}
