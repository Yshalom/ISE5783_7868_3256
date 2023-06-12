package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;
import static primitives.Util.isZero;

/**
 * The Sphere class represents a sphere in 3D space.
 * It extends the RadialGeometry class and adds a center field.
 */
public class Sphere extends RadialGeometry {

    /**
     * The center point of the sphere.
     */
    private final Point center;
    private final double radius;
    /**
     * Constructs a new Sphere object with the specified center point and radius.
     * @param a The center point of the sphere.
     * @param b The radius of the sphere.
     */
    public Sphere(Point a, double b){
        super(b);
        radius = b;
        center = a;
    }

    /**
     * Gets the radius of the sphere.
     * @return The radius of the sphere.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Gets the center point of the sphere.
     * @return The center point of the sphere.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Calculates and returns the normal vector of the sphere at the specified point.
     * @param p The point at which to calculate the normal vector.
     * @return The normal vector of the sphere at the specified point.
     */
    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    /**
     * Gets a ray vector and returns intersection points by GeoPoint format.
     * @param ray a ray
     * @return List of intersection points by GeoPoint format (this, point).
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance)
    {
        double d = 0;
        double tm = 0;
        if (!center.equals(ray.getP0())) // the ray's point is same as
        {
            Vector u = center.subtract(ray.getP0());
            tm = u.dotProduct(ray.getDir());
            if (isZero(u.lengthSquared() - tm * tm))
                d = 0;
            else
                d = Math.sqrt(u.lengthSquared() - tm * tm);
        }
        if (d > radius || isZero(d - radius))
            return null; // there are no intersections.
        double th = Math.sqrt(radius * radius - d * d);
        double t1 = tm - th;
        double t2 = tm + th;

        if ((t1 < 0 || isZero(t1)) && (t2 < 0 || isZero(t2)))
            return null; // there are no intersections.

        List<GeoPoint> res = new ArrayList<>();
        if (t1 > 0 && !isZero(t1) || isZero(t1 - maxDistance) || t1 > maxDistance) // there's intersection.
            res.add(new GeoPoint(this, ray.getPoint(t1)));
        if (t2 > 0 && !isZero(t2) || isZero(t2 - maxDistance) || t2 > maxDistance) // there's intersection.
            res.add(new GeoPoint(this, ray.getPoint(t2)));
        return res;
    }
}