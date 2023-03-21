package primitives;

import jdk.incubator.vector.VectorOperators;

public class Vector extends Point {

    public Vector(double x,double y,double z){
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)){
            throw new IllegalArgumentException();
        }
    }
    Vector (Double3 t){
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
        return new Vector(xyz.d1 * d, xyz.d2 * d, xyz.d3 * d);
    }

    public double dotProduct(Vector v){
        return (v.xyz.d1 * xyz.d1 + v.xyz.d2 * xyz.d2 + v.xyz.d3 * xyz.d3);
    }

    public Vector crossProduct(Vector v) {
        Double3 A = xyz;
        Double3 B = v.xyz;
        return new Vector(A.d2*B.d3-A.d3*B.d2, A.d3*B.d1-B.d3*A.d1, A.d1*B.d2-A.d2*B.d1);
    }

    public double lengthSquared()
    {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize()
    {
        double len = length();
        return new Vector(xyz.d1 / len, xyz.d2 / len, xyz.d3 / len);
    }

}
