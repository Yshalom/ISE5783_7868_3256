package geometries;

import primitives.*;

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
}