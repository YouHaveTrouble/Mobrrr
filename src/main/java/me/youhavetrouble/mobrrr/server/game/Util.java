package me.youhavetrouble.mobrrr.server.game;

import java.awt.geom.*;
import java.util.Collection;

/**
 * Utility class for all kinds of black magic.
 */
public class Util {

    /**
     * Raycasts from point A to point B and returns the first Area the ray hits.
     *
     * @param start the starting point of the ray
     * @param end the ending point of the ray
     * @param areas the collection of Area shapes
     * @return the first Area the ray hits, or null if no Area is hit
     */
    public static Area raycast(Point2D start, Point2D end, Collection<Area> areas) {
        Line2D ray = new Line2D.Double(start, end);
        Area closestArea = null;
        double closestDistance = Double.MAX_VALUE;
        for (Area area : areas) {
            PathIterator pathIterator = area.getPathIterator(null);
            double[] coords = new double[6];
            while (!pathIterator.isDone()) {
                int type = pathIterator.currentSegment(coords);
                if (type != PathIterator.SEG_LINETO && type != PathIterator.SEG_MOVETO) continue;
                Line2D segment = new Line2D.Double(coords[0], coords[1], coords[2], coords[3]);
                Point2D intersection = getIntersection(ray, segment);
                if (intersection == null) continue;
                double distance = start.distanceSq(intersection);
                if (distance >= closestDistance) continue;
                closestDistance = distance;
                closestArea = area;
                pathIterator.next();
            }
        }

        return closestArea;
    }

    private static Point2D getIntersection(Line2D ray, Line2D segment) {
        // Get the starting point and direction vector of the ray
        double r_px = ray.getX1();
        double r_py = ray.getY1();
        double r_dx = ray.getX2() - ray.getX1();
        double r_dy = ray.getY2() - ray.getY1();

        // Get the starting point and direction vector of the segment
        double s_px = segment.getX1();
        double s_py = segment.getY1();
        double s_dx = segment.getX2() - segment.getX1();
        double s_dy = segment.getY2() - segment.getY1();

        // Calculate the magnitude of the direction vectors
        double r_mag = Math.sqrt(r_dx * r_dx + r_dy * r_dy);
        double s_mag = Math.sqrt(s_dx * s_dx + s_dy * s_dy);

        // Check if the lines are parallel (parallel lines do not intersect)
        if (r_dx / r_mag == s_dx / s_mag && r_dy / r_mag == s_dy / s_mag) return null;

        // Calculate the intersection point using the parametric form of the lines
        double T2 = (r_dx * (s_py - r_py) + r_dy * (r_px - s_px)) / (s_dx * r_dy - s_dy * r_dx);
        double T1 = (s_px + s_dx * T2 - r_px) / r_dx;

        // Check if the intersection point is within the bounds of the ray and segment
        if (T1 < 0) return null; // Intersection is behind the start of the ray
        if (T2 < 0 || T2 > 1) return null; // Intersection is outside the segment

        // Calculate and return the intersection point
        return new Point2D.Double(r_px + r_dx * T1, r_py + r_dy * T1);
    }

}
