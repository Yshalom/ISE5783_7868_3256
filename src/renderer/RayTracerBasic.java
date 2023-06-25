package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import java.util.List;
import static primitives.Util.isZero;

import geometries.Intersectable.GeoPoint;

public class RayTracerBasic extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    private double SnellParameter = 1; // An optional value for snell law calculation.

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
        GeoPoint geoPoint = scene.geometries.findClosestIntersection(ray);

        if (geoPoint == null)
            return scene.background;

        return calcColor(geoPoint, ray);
    }

    /**
     * Calculate the color at a given point.
     * @param gp The point
     * @return The color at the point.
     */
    private Color calcColor(GeoPoint gp, Ray ray)
    {
        SnellParameter = 1;

        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, Double3.ONE)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * This function helps to calculate the color at a given point,
     * this function works as a recursive function, it calculates the reflected light and the refracted light,
     * and the color which have to be set to the point p.
     * @param gp The point to calculate the color on.
     * @return The color at the point that have come from reflected light and the refracted light.
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k)
    {
        Color color = calcLocalEffects(gp, ray);
        return 1 == level ? color
                : color.add(calcGlobalEffects(gp, ray, level, k));
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
                Color iL = lightSource.getIntensity(gp.point).scale(transparency(gp, lightSource, l, n));
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

    private boolean unshaded(GeoPoint gp, Vector l, Vector n, Vector v, double nl, LightSource LS){
        Ray ray = new Ray(gp.point,l.scale(-1), n);
        List<GeoPoint> list = scene.geometries.findGeoIntersections(ray, LS.getDistance(gp.point));
        if (list == null)
            return true;
        for (GeoPoint GP1 : list)
        {
            if (!GP1.geometry.getMaterial().kT.equals(Double3.ZERO))
                return true;
        }
        return false;
    }

    /***
     * This function calculate the strength of the shadow in a certain point.
     * @param gp The point to calculate the strength of the shadow at.
     * @param LS The light source which we calculate the shadow for.
     * @param l A vector from the camera to the point.
     * @param n A normal vector to the geometry's surface at the point gp.
     * @return The shadow's strength.
     */
    private Double3 transparency(GeoPoint gp, LightSource LS, Vector l, Vector n)
    {
        Ray ray = new Ray(gp.point,l.scale(-1), n);
        Double3 ktr = Double3.ONE;
        List<GeoPoint> list = scene.geometries.findGeoIntersections(ray, LS.getDistance(gp.point));
        if (list == null)
            return ktr;

        for (GeoPoint GP1 : list)
        {
            ktr = ktr.product(GP1.geometry.getMaterial().kT);
            if (ktr.equals(Double3.ZERO))
                return Double3.ZERO;
        }
        return ktr;
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructReflectedRay(gp, v, n),level, k, material.kR)
                .add(calcGlobalEffect(constructRefractedRay(gp, v, n),level, k, material.kT));
    }

    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx)
    {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = scene.geometries.findClosestIntersection(ray);
        if (gp == null)
            return scene.background.scale(kx);
        return isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir())) ? Color.BLACK
                : calcColor(gp, ray, level-1, kkx).scale(kx);
    }

    /**
     * This function calculate the reflected ray by several given parameters.
     * @param gp A given GeoPoint to calculate the reflected ray on its point.
     * @param v A given vector to calculate the reflected vector with it.
     * @param n The normal to the geometry.
     * @return The reflected vector.
     */
    Ray constructReflectedRay(GeoPoint gp, Vector v, Vector n)
    {
        Vector r = v.subtract(n.scale( 2 * (v.dotProduct(n))));
        return new Ray(gp.point, r, n);
    }

    /***
     * This function calculate the refracted ray by several given parameters.
     * @param gp A given GeoPoint to calculate the refracted ray on its point.
     * @param v A given vector to calculate the refracted vector with it.
     * @param n The normal to the geometry.
     * @return The refracted vector.
     */
    Ray constructRefractedRay(GeoPoint gp, Vector v, Vector n)
    {
        // Without snell low:
        if (SnellParameter == gp.geometry.getMaterial().SnellParameter)
            return new Ray(gp.point, v, n);

        // With snell low:
        double Theta1 = Math.acos(- v.dotProduct(n));
        double Sin_Theta2 = Math.sin(Theta1) * SnellParameter / gp.geometry.getMaterial().SnellParameter;
        if (Sin_Theta2*Sin_Theta2 > 1 || isZero(Sin_Theta2*Sin_Theta2 - 1)) // There's a full reflected.
        {
            SnellParameter = gp.geometry.getMaterial().SnellParameter;
            return constructReflectedRay(gp, v, n);
        }
        double Theta2 = Math.asin(Sin_Theta2);
        Vector nvn = n.crossProduct(v).crossProduct(n).normalize();
        Vector r = n.scale(-Math.cos(Theta2)).add(nvn.scale(Sin_Theta2));
        SnellParameter = gp.geometry.getMaterial().SnellParameter;
        return new Ray(gp.point, r, n);
    }
}
