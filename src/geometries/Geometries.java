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


        //It's impossible to not create a list object if it's not needed because a lambda function gets only final objects.
        //** and using the forEach loop twice instead of once will take a lot of unneeded run time.
        final List<Point> res = new LinkedList<>();

        geometries.forEach( (i) -> {
            List<Point> intersections = i.findIntersections(ray);
            if (intersections != null)
                res.addAll(intersections);
            } );

        if (res.size() == 0)
            return null;
        return res;
    }
}