package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;
import static primitives.Util.isZero;

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

    /**
     * Gets a ray vector and returns intersection points by GeoPoint format.
     * @param ray a ray
     * @return List of intersection points by GeoPoint format (this, point).
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance)
    {
        List<Point> Intersections = plane.findIntersections(ray);
        if (Intersections == null)
            return null;
        Point p0 = plane.findIntersections(ray).get(0);
        if (ray.getP0().distance(p0) > maxDistance || isZero(ray.getP0().distance(p0) - maxDistance))
            return null;
        if (vertices.contains(p0))
            return null;
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        try {
            Vector n1 = v1.crossProduct(v2);
            Vector n2 = v2.crossProduct(v3);
            Vector n3 = v3.crossProduct(v1);

            // The point is on edge's continuation.
            if (isZero(n1.dotProduct(ray.getDir())) || isZero(n2.dotProduct(ray.getDir())) || isZero(n3.dotProduct(ray.getDir())))
                return null;

            if (n1.dotProduct(ray.getDir()) < 0) {
                if (n2.dotProduct(ray.getDir()) > 0 || n3.dotProduct(ray.getDir()) > 0)
                    return null;
            }
            if (n1.dotProduct(ray.getDir()) > 0) {
                if (n2.dotProduct(ray.getDir()) < 0 || n3.dotProduct(ray.getDir()) < 0)
                    return null;
            }
        }
        catch (Exception e)
        {return null; }

        List<GeoPoint> res = new ArrayList<>();
        res.add(new GeoPoint(this, p0));
        return res;
    }
}