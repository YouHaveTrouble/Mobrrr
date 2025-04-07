package me.youhavetrouble.mobrrr.server.game;

import org.jetbrains.annotations.NotNull;

import java.awt.geom.Area;

/**
 * Represents a 3D shape with a base area and height.
 * @param area the base area of the shape
 * @param height the height of the shape
 * @param offsetX offset from the origin point in the X direction
 * @param offsetY offset from the origin point in the Y direction
 * @param offsetZ offset from the origin point in the Z direction
 */
public record Shape(@NotNull Area area, double height, double offsetX, double offsetY, double offsetZ) {

}
