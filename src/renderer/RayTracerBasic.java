package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import java.util.List;
import static primitives.Util.isZero;

import geometries.Intersectable.GeoPoint;

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
        List<GeoPoint> list = scene.geometries.findGeoIntersections(ray);

        if (list == null)
            return scene.background;

        // Take the closest point
        GeoPoint p = ray.findClosestGeoPoint(list);

        return calcColor(p, ray);
    }

    /**
     * Calculate the color at a given point.
     * @param p The point
     * @return The color at the point.
     */
    private Color calcColor(GeoPoint p, Ray ray)
    {
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(p, ray));
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray)
    {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir ();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = n.dotProduct(v);
        if (isZero(nv))
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = n.dotProduct(l);
            if (nl * nv > 0) // sign(nl) == sing(nv)
            {
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }

    Double3 calcDiffusive(Material material, double nl)
    {
        return material.kD.scale(Math.abs(nl));
    }
    Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v)
    {
        Vector r = l.subtract(n.scale( 2 * (l.dotProduct(n))));
        if (v.dotProduct(r) >= 0)
            return Double3.ZERO;
        return material.ks.scale(Math.pow(-v.dotProduct(r), material.nShininess));
    }
}
