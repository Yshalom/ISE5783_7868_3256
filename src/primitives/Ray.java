package primitives;


public class Ray
{
    private Point p0;
    private Vector dir;
    public Ray(Vector a){
       Vector b=a.normalize();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ray){
            return xyz.equals((Object)((Ray)obj).xyz);
        }
        return false;
    }
    @Override
    public String toString() {
        return xyz.toString();
    }
}

