package primitives;

public class Point {
    final Double3 xyz;
    public Point(double x,double y,double z){
        xyz = new Double3(x,y,z);
    }
    Point (Double3 t){
        xyz = t;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point){
            return xyz.equals((Object)((Point)obj).xyz);
        }
        return false;
    }

    @Override
    public String toString() {
        return xyz.toString();
    }
    public Point add(Vector v){
        return new Point(xyz.d1 + v.xyz.d1, xyz.d2 + v.xyz.d2, xyz.d3 + v.xyz.d3);
    }
    public Vector subtract(Point a)
    {
        return (new Vector(xyz.d1 - a.xyz.d1, xyz.d2 - a.xyz.d2, xyz.d3 - a.xyz.d3));
    }

    public double distance(Point p)
    {
        return Math.sqrt(distanceSquared(p));
    }

    public double distanceSquared(Point p)
    {
        Double3 Sub = xyz.subtract(p.xyz);
        Double3 Squared = Sub.product(Sub);
        return (Squared.d1 + Squared.d2 + Squared.d3);
    }
}
