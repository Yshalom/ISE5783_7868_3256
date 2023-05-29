package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.function.Supplier;

public class Geometries extends Intersectable {
    private List<Intersectable> geometries;

    public Geometries()
    {
        geometries = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... geometries)
    {
        this.geometries = new LinkedList<Intersectable>();
        this.geometries.addAll(Arrays.stream(geometries).toList());
    }

    public void add(Intersectable... geometries)
    {
        this.geometries.addAll(Arrays.stream(geometries).toList());
    }

    /**
     * Gets a ray vector and returns all the intersection points with all the geometries by GeoPoint format.
     * @param ray a ray
     * @return List of intersection points by GeoPoint format (this, point).
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
    {
        if (geometries == null)
            return null;

        List<GeoPoint> res = null;

        for (Intersectable g : geometries) {
            List<GeoPoint> intersections = g.findGeoIntersectionsHelper(ray);
            if (intersections != null) {
                if (res == null)
                    res = new LinkedList<GeoPoint>();
                res.addAll(intersections);
            }
        }
        return res;
    }
}