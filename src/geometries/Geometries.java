package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

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

    public void add(Intersectable... geomertries)
    {
        this.geometries.addAll(geometries.stream().toList());
    }

    @Override
    public List<Point> findIntersections(Ray ray)
    {
        return null;
    }
}