package geometries;

import primitives.*;

/**
 * The Cylinder class represents a cylinder shape in 3D Cartesian coordinate system.
 * It is defined by its height and a base tube shape, which is inherited from the Tube class.
 */
public class Cylinder extends Tube {

    /**
     * The height of the cylinder.
     */
    private final double height;

    /**
     * Constructs a cylinder object with the given height and base tube shape.
     * @param height The height of the cylinder.
     * @param baseRay The base tube shape, represented by a ray and radius.
     * @param radius The radius of the cylinder.
     */
    public Cylinder(double height, Ray baseRay, double radius){
        super(baseRay, radius);
        this.height = height;
    }

    /**
     * Gets the normal vector to the cylinder at the specified point.
     * @param p The point on the surface of the cylinder to get the normal vector at.
     * @return The normal vector to the cylinder at the specified point.
     */
    @Override
    public Vector getNormal(Point p) {
        return null; // TODO: Implement this method.
    }

    /**
     * Gets the height of the cylinder.
     * @return The height of the cylinder.
     */
    public double getHeight() {
        return height;
    }
}
