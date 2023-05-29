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
        Color res = scene.ambientLight.getIntensity().add(p.geometry.getEmission()); // kA*IA + IE

        for (LightSource light : scene.lights)
        {
            Vector l = light.getL(p.point),
                n = p.geometry.getNormal(p.point),
                v = ray.getDir(),
                r = l; // l and n are vertical
            if (!isZero(l.dotProduct(n)))
                r = l.add(n.scale(-2 * l.dotProduct(n)));


            if (l.dotProduct(n) * v.dotProduct(n) > 0) // Are there shadow in this area? if yes it'll skip this code.
            {
                Double3 DiffuseParameter = p.geometry.getMaterial().kD.scale(Math.abs(l.dotProduct(n))); // Kd * |l*n|
                Double3 SpecularParameter = Double3.ZERO;
                double VR = v.scale(-1).dotProduct(r);
                if (VR > 0)
                    SpecularParameter = p.geometry.getMaterial().ks.scale(Math.pow(VR, p.geometry.getMaterial().nShininess)); // ks * max(0, -v*r)^nsh

                res = res.add(light.getIntensity(p.point).scale(DiffuseParameter.add(SpecularParameter))); // (Kd * |l*n| + ks * max(0, -v*r)^nsh)*Il
            }
        }

        return res;
    }
}
