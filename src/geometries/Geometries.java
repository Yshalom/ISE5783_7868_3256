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
     * Gets a ray vector and returns all the intersection points with all the geometries.
     * @param ray a ray
     * @return List of intersection points.
     */
    @Override
    public List<Point> findIntersections(Ray ray)
    {
        if (geometries == null)
            return null;


        List<Point> res = null;

        for (Intersectable g : geometries) {
            List<Point> intersections = g.findIntersections(ray);
            if (intersections != null) {
                if (res == null)
                    res = new LinkedList<Point>();
                res.addAll(intersections);
            }
        }
        return res;
    }
}