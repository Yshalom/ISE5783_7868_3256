package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * The Intersectable class is an abstract class representing the findIntsersections function to all the geometries.
 *the findIntersections fuction provides intersection points on the geometry interface;
 */
public abstract class Intersectable {
    /***
     * geometry - The Geometry of the GeoPoint
     * point - The Point of the GeoPoint that's on the geometry interface.
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /***
         * Constructor for GeoPoint class.
         * @param geometry The Geometry of the GeoPoint
         * @param point The Point of the GeoPoint that's on the geometry interface.
         */
        GeoPoint(Geometry geometry, Point point)
        {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * Checks if this GeoPoint class is equal to the specified object.
         *
         * @param obj the object to compare this point to
         * @return true if this GeoPoint class is equal to the specified object, false otherwise
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj instanceof GeoPoint) {
                return (geometry.equals(((GeoPoint) obj).geometry) && point.equals(((GeoPoint) obj).point));
            }
            return false;
        }

        /**
         * Returns a string representation of this GeoPoint in the form "@geometry \n @point".
         *
         * @return a string representation of this point
         */
        @Override
        public String toString() {
            return geometry.toString() + "/n" + point.toString();
        }
    }


    /**
     * Gets a ray vector and returns intersection points.
     * @param ray a ray
     * @return List of intersection points.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

}
