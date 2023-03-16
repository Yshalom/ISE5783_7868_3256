package primitives;

import jdk.incubator.vector.VectorOperators;

public class Vector extends Point {

    public Vector(double x,double y,double z){
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)){
            throw new IllegalArgumentException();
        }
    }
    public Vector (Double3 t){
        super(t);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector){
            return xyz.equals((Object)((Vector)obj).xyz);
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Vector add(Vector v)
    {
        return new Vector(xyz.add(v.xyz));
    }

    public Vector scale(double d){
        return new Vector(xyz.scale(d));
    }

    public double dotProduct(Vector v){
        return (v.xyz.d1 * xyz.d1 + v.xyz.d2 * xyz.d2 + v.xyz.d3 * xyz.d3);
    }

    public Vector corssProduct(Vector v) {
        Double3 A = xyz;
        Double3 B = v.xyz;
        return new Vector(A.d2*B.d3-A.d3*B.d2, A.d3*B.d1-B.d3*A.d1, A.d1*B.d2-A.d2*B.d1);
    }

    public double lengthSquared()
    {
        return xyz.d1 * 2 + xyz.d2 * 2 + xyz.d3 * 2;
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize()
    {
        return new Vector(xyz.reduce(length()));
    }

}
