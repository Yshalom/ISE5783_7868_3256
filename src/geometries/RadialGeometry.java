package geometries;

/**
 * The RadialGeometry class is an abstract base class representing a geometry object with a radial component.
 * It extends the Geometry class and adds a radius field.
 */
abstract public class RadialGeometry extends Geometry {

    /**
     * The radius of the radial geometry object.
     */
    protected final double radius;

    /**
     * Constructs a new RadialGeometry object with the specified radius.
     * @param a The radius of the radial geometry object.
     */
    public RadialGeometry(double a){
        radius=a;
    }

    /**
     * Gets the radius of the radial geometry object.
     * @return The radius of the radial geometry object.
     */
    public double getRadius() {
        return radius;
    }
}
