package geometries;

import primitives.*;

public class Sphere extends RadialGeometry {
    private final Point center;
    private final double radius;
    public Sphere (Point a,double b){
        radius=b;
        center =a;

    }

    public double getRadius() {
        return radius;
    }

    public Point getCenter() {
        return center;
    }
    public Vector getNormal(Point a){

    }
}
