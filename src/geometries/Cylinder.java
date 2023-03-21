package geometries;

import primitives.*;

public class Cylinder extends Tube {
    private final double height;
    public Cylinder(double t, Ray a ,double b){
        super(a,b);
        height=t;
    }
    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    public double getHeight() {
        return height;
    }
}
