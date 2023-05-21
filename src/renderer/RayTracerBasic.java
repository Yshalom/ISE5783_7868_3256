package renderer;

import primitives.*;
import scene.Scene;
import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene)
    {
        super(scene);
    }

    /**
     * Calculating the color of the ray - The color which the pixel that makes the ray will set into.
     * @param ray The ray
     * @return The color
     */
    public Color traceRay(Ray ray)
    {
        List<Point> list = scene.geometries.findIntersections(ray);

        if (list == null)
            return scene.background;

        // Take the closest point
        Point p = list.get(0);
        for (int i = 1; i < list.size(); i++)
            if (ray.getP0().distanceSquared(list.get(i)) < ray.getP0().distanceSquared(p))
                p = list.get(i);

        return calcColor(p);
    }

    /**
     * Calculate the color at a given point.
     * @param p The point
     * @return The color at the point.
     */
    private Color calcColor(Point p)
    {
        return scene.ambientLight.getIntensity();
    }
}
