package geometries;

import primitives.*;

public class Plane extends Geometry {
    private final Point q0;
    private final Vector normal;

    public Plane(Point p1, Point p2, Point p3)
    {
        q0 = p1;
        normal = null;

        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        Vector v3 =  v1.corssProduct(v2).normalize();
    }

    public Plane(Point p, Vector v)
    {
        q0 = p;
        normal = v.normalize();
    }

    public Vector getNormal()
    {
        return normal;
    }

    @Override
    public Vector getNormal(Point p)
    {
        return normal;
    }

}
