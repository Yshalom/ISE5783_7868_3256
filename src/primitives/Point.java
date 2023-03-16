package primitives;

import java.util.Vector;

public class Point {
    protected Double3 xyz;
    public Point(double x,double y,double z){
        xyz = new Double3(x,y,z);
    }
    public Point (Double3 t){
        xyz = t;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point){
            return xyz.equals((Object)((Point)obj).xyz);
        }
        else if (obj instanceof Vector) {
            return xyz.equals((Object)((Vector)obj).xyz);
        }
        return false;
    }

    @Override
    public String toString() {
        return xyz.toString();
    }
    public Point add(Vector v){
        return (new Point(xyz.add(v.xyz)));
    }
    public Vector subtract(Point a)
    {
        return (new Vector(xyz.subtract(v.xyz)));
    }

    public double distance(Point p)
    {
        return Math.sqrt(distanceSquared(p));
    }

    public double distanceSquared(Point p)
    {
        Double3 Sub = xyz.subtract(p.xyz);
        Double3 Squared = Sub.product(Sub);
        return (Squared.SumCoordinate());
    }

}
