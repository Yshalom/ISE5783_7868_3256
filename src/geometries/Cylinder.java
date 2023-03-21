package geometries;

import primitives.Point;
import primitives.Vector;

public class Cylinder extends Tube {
    private double height;
    public Cylinder(double t, Ray a ,double b){
        super(a,b);
        height=t;
    }
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
