package geometries;
import primitives.*;
import static primitives.Util.isZero;
/**
 * The Tube class represents a tube in 3D space.
 * It extends the RadialGeometry class and is defined by an axis ray and a radius.
 */
public class Tube extends RadialGeometry {

    /**
     * The axis ray of the tube.
     */
    protected final Ray axiRay;

    /**
     * Constructs a new Tube object with the specified axis ray and radius.
     * @param a The axis ray of the tube.
     * @param b The radius of the tube.
     */
    public Tube(Ray a, double b) {
        super(b);
        axiRay = a;
    }

    /**
     * Returns the axis ray of the tube.
     * @return The axis ray of the tube.
     */
    public Ray getAxiRay() {
        return axiRay;
    }

    @Override
    public Vector getNormal(Point p) {
        double t = axiRay.getDir().dotProduct(p.subtract(axiRay.getP0()));
        if (isZero(t))
            return p.subtract(axiRay.getP0()).normalize();
        Point O = axiRay.getP0().add(axiRay.getDir().scale(t));
        return p.subtract(O).normalize();
    }
}
