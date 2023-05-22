package geometries;

import primitives.*;
import java.util.List;

/**
 * The Intersectable class is an abstract class representing the findIntsersections function to all the geometries.
 *the findIntersections fuction provides intersection points on the geometry interface;
 */
public abstract class Intersectable {
    /**
     * Gets a ray vector and returns intersection points.
     * @param ray a ray
     * @return List of intersection points.
     */
    public abstract List<Point> findIntersections(Ray ray);
}