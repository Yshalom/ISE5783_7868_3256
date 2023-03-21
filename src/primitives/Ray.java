package primitives;


public class Ray
{
    private final Point p0;
    private final Vector dir;
    public Ray(Point p, Vector v){
        p0 = p;
        dir = v.normalize();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ray){
            return (p0.equals((Object)((Ray)obj).p0) && dir.equals((Object)((Ray)obj).dir));
        }
        return false;
    }
    @Override
    public String toString() {
        String s = "p0=" + p0.toString() + ", dir=" + dir.toString();
        return s;
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }
}
