package primitives;

import java.util.List;

/**
 * This class represents a ray in 3D space, defined by a starting point (p0) and a direction vector (dir).
 */
public class Ray
{
    private final Point p0; // The starting point of the ray
    private final Vector dir; // The direction vector of the ray (normalized)

    /**
     * Constructs a new Ray object with the specified starting point and direction vector.
     *
     * @param p The starting point of the ray.
     * @param v The direction vector of the ray.
     */
    public Ray(Point p, Vector v){
        p0 = p;
        dir = v.normalize();
    }

    /**
     * Determines whether the specified object is equal to this Ray object.
     * Two rays are considered equal if their starting points and direction vectors are equal.
     *
     * @param obj The object to compare to this Ray object.
     * @return true if the specified object is equal to this Ray object; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ray){
            return (p0.equals((Object)((Ray)obj).p0) && dir.equals((Object)((Ray)obj).dir));
        }
        return false;
    }

    /**
     * Returns a string representation of this Ray object.
     *
     * @return A string representation of this Ray object.
     */
    @Override
    public String toString() {
        String s = "p0=" + p0.toString() + ", dir=" + dir.toString();
        return s;
    }

    /**
     * Returns the starting point of this Ray object.
     *
     * @return The starting point of this Ray object.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the direction vector of this Ray object.
     *
     * @return The direction vector of this Ray object.
     */
    public Vector getDir() {
        return dir;
    }

    public Point getPoint(double t)
    {
        return p0.add(dir.scale(t));
    }

    /**
     * Find the closest point to the ray's head between a list of points.
     * @param l The list of point to run the function on.
     * @return The closest point which was found.
     */
    public Point findClosestPoint(List<Point> l)
    {
        if(l==null || l.size() == 0)
            return null;
        double Min = l.get(0).distanceSquared(p0);
        int index = 0;

        for (int i = 0; i < l.size(); i++)
        {
            double Distance = l.get(i).distanceSquared(p0);
            if (Min > Distance)
            {
                index = i;
                Min = Distance;
            }
        }

        return l.get(index);
    }
}
