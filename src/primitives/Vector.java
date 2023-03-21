package primitives;

/**
 * This class represents a 3D vector.
 * It extends the Point class, which represents a point in 3D space.
 */
public class Vector extends Point {

    /**
     * Constructs a new Vector with the given x, y, and z components.
     *
     * @param x the x component of the vector
     * @param y the y component of the vector
     * @param z the z component of the vector
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);

        // Throws an exception if the vector is the zero vector
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructs a new Vector with the given Double3 object.
     *
     * @param t the Double3 object representing the vector
     */
    Vector (Double3 t){
        super(t);

        // Throws an exception if the vector is the zero vector
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns true if this vector is equal to the given object.
     * Two vectors are considered equal if they have the same x, y, and z components.
     *
     * @param obj the object to compare to this vector
     * @return true if this vector is equal to the given object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector){
            return xyz.equals((Object)((Vector)obj).xyz);
        }
        return false;
    }

    /**
     * Returns a string representation of this vector in the format "(x, y, z)".
     *
     * @return a string representation of this vector
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Returns a new vector that is the result of adding this vector to the given vector.
     *
     * @param v the vector to add to this vector
     * @return a new vector that is the sum of this vector and the given vector
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * Returns a new vector that is the result of scaling this vector by the given scalar value.
     *
     * @param d the scalar value to multiply this vector by
     * @return a new vector that is the result of scaling this vector by the given scalar value
     */
    public Vector scale(double d) {
        return new Vector(xyz.d1 * d, xyz.d2 * d, xyz.d3 * d);
    }

    /**
     * Returns the dot product of this vector and the given vector.
     *
     * @param v the vector to compute the dot product with
     * @return the dot product of this vector and the given vector
     */
    public double dotProduct(Vector v) {
        return (v.xyz.d1 * xyz.d1 + v.xyz.d2 * xyz.d2 + v.xyz.d3 * xyz.d3);
    }

    /**
     * Returns the cross product of this vector and the given vector.
     *
     * @param v the vector to compute the cross product with
     * @return the cross product of this vector and the given vector
     */
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