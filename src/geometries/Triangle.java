package geometries;

import primitives.*;

import java.util.List;

/**
 * The Triangle class represents a triangle in 3D space.
 * It extends the Polygon class and is defined by three points.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a new Triangle object with the specified points.
     *
     * @param x The first point of the triangle.
     * @param y The second point of the triangle.
     * @param z The third point of the triangle.
     */
    public Triangle(Point x, Point y, Point z) {
        super(x, y, z);
    }

    @Override
    public List<Point> findIntersections(Ray ray)
    {
        Vector v1 = ((Point)vertices.toArray()[0]).subtract(ray.getP0());
        Vector v2 = ((Point)vertices.toArray()[1]).subtract(ray.getP0());
        Vector v3 = ((Point)vertices.toArray()[2]).subtract(ray.getP0());

        Vector n1 = v1.crossProduct(v2);
        Vector n2 = v2.crossProduct(v3);
        Vector n3 = v3.crossProduct(v1);

        if (n1.dotProduct(ray.getDir()) < 0)
        {
            if (n2.dotProduct(ray.getDir()) > 0 || n3.dotProduct(ray.getDir()) > 0)
                return null;
        }
        if (n1.dotProduct(ray.getDir()) > 0)
        {
            if (n2.dotProduct(ray.getDir()) < 0 || n3.dotProduct(ray.getDir()) < 0)
                return null;
        }

        return plane.findIntersections(ray);
    }
}