package geometries;

import primitives.*;
import java.util.List;

/**
 * The Intersectable class is an abstract class representing the findIntsersections function to all the geometries.
 * the findIntsersections fuction provides
 */
public abstract class Intersectable {
    public abstract List<Point> findIntsersections(Ray ray);

}
