package primitives;

/**
 * A 3D point in space, defined by its x, y, and z coordinates.
 */
public class Point {
    /**
     * The 3D coordinates of this point.
     */
    final Double3 xyz;

    /**
     * Constructs a new point with the given x, y, and z coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param z the z-coordinate of the point
     */

    /** Zero triad (0,0,0) */
    public static final Point ZERO = new Point(0, 0, 0);


    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a new point with the given 3D coordinates.
     *
     * @param t the 3D coordinates of the point
     */
    Point(Double3 t) {
        xyz = t;
    }

    /**
     * Checks if this point is equal to the specified object. Two points are considered equal if their coordinates are equal.
     *
     * @param obj the object to compare this point to
     * @return true if this point is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Point) {
            return xyz.equals(((Point) obj).xyz);
        }
        return false;
    }

    /**
     * Returns a string representation of this point in the form "(x, y, z)".
     *
     * @return a string representation of this point
     */
    @Override
    public String toString() {
        return xyz.toString();
    }

    /**
     * Returns a new point whose coordinates are the sum of this point's coordinates and the given vector's coordinates.
     *
     * @param v the vector to add to this point
     * @return a new point whose coordinates are the sum of this point's coordinates and the given vector's coordinates
     */
    public Point add(Vector v) {
        return new Point(xyz.d1 + v.xyz.d1, xyz.d2 + v.xyz.d2, xyz.d3 + v.xyz.d3);
    }

    /**
     * Returns a new vector whose coordinates are the difference between this point's coordinates and the given point's coordinates.
     *
     * @param a the point to subtract from this point
     * @return a new vector whose coordinates are the difference between this point's coordinates and the given point's coordinates
     */
    public Vector subtract(Point a) {
        return (new Vector(xyz.d1 - a.xyz.d1, xyz.d2 - a.xyz.d2, xyz.d3 - a.xyz.d3));
    }

    /**
     * Returns the Euclidean distance between this point and the given point.
     *
     * @param p the point to calculate the distance to
     * @return the Euclidean distance between this point and the given point
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    /**
     * Returns the square of the Euclidean distance between this point and the given point.
     *
     * @param p the point to calculate the distance to
     * @return the square of the Euclidean distance between this point and the given point
     */
    public double distanceSquared(Point p) {
        double d1 = xyz.d1 - p.xyz.d1;
        double d2 = xyz.d2 - p.xyz.d2;
        double d3 = xyz.d3 - p.xyz.d3;
        return (d1 * d1 + d2 * d2 + d3 * d3);
    }
}