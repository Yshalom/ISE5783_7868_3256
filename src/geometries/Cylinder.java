package geometries;

import primitives.*;
import java.util.List;
import static primitives.Util.isZero;

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
        //The point is on the base whom the cylinder's ray's point is on
        if (p.equals(axiRay.getP0()) // p == p0
                || isZero(p.subtract(axiRay.getP0()).dotProduct(axiRay.getDir())))
            return axiRay.getDir().scale(-1);

        //The point is on the base whom nat the cylinder's ray's point is on
        if (p.equals(axiRay.getPoint(height)) // p == (p0 + height * v)
                 || isZero(p.subtract(axiRay.getPoint(height)).dotProduct(axiRay.getDir())))
            return axiRay.getDir();

        return super.getNormal(p);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

    /**
     * Gets the height of the cylinder.
     * @return The height of the cylinder.
     */
    public double getHeight() {
        return height;
    }
}
