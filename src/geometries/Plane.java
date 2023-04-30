package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;


/**
 * The Plane class represents a plane shape in 3D Cartesian coordinate system.
 * It is defined by a point on the plane and a normal vector to the plane.
 */
public class Plane extends Geometry {

    /**
     * The point on the plane.
     */
    private final Point q0;

    /**
     * The normal vector to the plane.
     */
    private final Vector normal;

    /**
     * Constructs a plane object using three points on the plane.
     * @param p1 A point on the plane.
     * @param p2 A second point on the plane.
     * @param p3 A third point on the plane.
     */
    public Plane(Point p1, Point p2, Point p3)
    {
        if(p1.equals(p2)||p2.equals(p3)||p3.equals(p1))
            throw new IllegalArgumentException();
        if(isZero(p3.subtract(p2).dotProduct(p1.subtract(p2)) - p3.subtract(p2).length()* (p1.subtract(p2)).length()))
            throw new IllegalArgumentException();
        q0 = p1;
        normal =(p3.subtract(p2).crossProduct(p1.subtract(p2))).normalize();

        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        Vector v3 =  v1.crossProduct(v2).normalize();
    }

    /**
     * Constructs a plane object using a point on the plane and a normal vector to the plane.
     * @param p A point on the plane.
     * @param v The normal vector to the plane.
     */
    public Plane(Point p, Vector v)
    {
        q0 = p;
        normal = v.normalize();
    }

    /**
     * Gets the normal vector to the plane.
     * @return The normal vector to the plane.
     */
    public Vector getNormal()
    {
        return normal;
    }

    /**
     * Gets the normal vector to the plane at the specified point (which may not necessarily be on the plane).
     * @param p The point to get the normal vector at.
     * @return The normal vector to the plane at the specified point.
     */
    @Override
    public Vector getNormal(Point p)
    {
        return normal;
    }

    /**
     * Gets the point on the plane.
     * @return The point on the plane.
     */
    public Point getQ0() {
        return q0;
    }
    @Override
    public List<Point> findIntersections(Ray ray)
    {
      double t= (getNormal().dotProduct((q0.subtract(ray.getP0()))))/(getNormal().dotProduct(ray.getDir()));
      if (t<0)
          return null;
        List<Point> res = new ArrayList<>();
        res.add(ray.getPoint(t));
      return res;
    }
}
