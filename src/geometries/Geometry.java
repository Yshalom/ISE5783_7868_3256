package geometries;

import primitives.*;

/**
 * The Geometry class is an abstract class representing a general geometric shape in 3D Cartesian coordinate system.
 * It provides an abstract method for calculating the normal vector to the shape at a given point.
 */
public abstract class Geometry {

    /**
     * Gets the normal vector to the geometry at the specified point.
     * @param p The point on the surface of the geometry to get the normal vector at.
     * @return The normal vector to the geometry at the specified point.
     */
    public abstract Vector getNormal(Point p);
}