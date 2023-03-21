package geometries;

import primitives.*;

/**
 * The Triangle class represents a triangle in 3D space.
 * It extends the Polygon class and is defined by three points.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a new Triangle object with the specified points.
     * @param x The first point of the triangle.
     * @param y The second point of the triangle.
     * @param z The third point of the triangle.
     */
    public Triangle(Point x, Point y, Point z) {
        super(x, y, z);
    }
}

