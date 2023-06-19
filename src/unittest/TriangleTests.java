package unittest;

import primitives.*;
import geometries.*;
import static primitives.Util.isZero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TriangleTests {
    /**
     * This function checks the triangle's getNormal function.
     */
    @Test
    @DisplayName("TriangleTest - EQ getNormal test")
    void normal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Creating a triangle and checking the getNormal function - (Is the normal vector's length equals 1? Is it perpendicular to the triangle?)
        Point[] pts =
                { new Point(-1, 0, 2), new Point(2, -3, 0), new Point(0, 2, 0) };
        Triangle triangle = new Triangle(pts[0], pts[1], pts[2]);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> triangle.getNormal(new Point(-0.1, 0, 1)), "");
        // generate the test result
        Vector result = triangle.getNormal(new Point(-0.1, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Triangle's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 2; ++i)
            assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 2 : i - 1]))),
                    "Triangle's normal is not orthogonal to one of the edges");
    }


    @Test
    @DisplayName("TriangleTests - testFindIntersections tests")
    void testFindIntersectionPoints()
    {
        // ============ Equivalence Partitions Tests ==============

        // TC01: The Point is on the triangle
        Triangle triangle = new Triangle(new Point(-1.582330077979341, -2.299323910595358, 1.277013326971096), new Point(1.322758940265583, 2.348914309294042, -0.4682382198894), new Point(-0.99627593058881, 2.765600530137782, 2.596593764841225));
        Ray ray = new Ray(new Point(2.616212398061027, -3.251412796833307, 1.170595549723505), new Vector(-4.496887943191137, 6.588838393550958, -0.332645803159163));
        List<Point> intersections = triangle.findIntersections(ray);
        assertTrue(intersections.indexOf(new Point(-0.265691207964898, 0.971151199818517, 0.957414089174703)) != -1);
        // TC02: The point is outside and against edge
        ray = new Ray(new Point(2.616212398061027, -3.251412796833307, 1.170595549723505), new Vector(-6.486436300419531, 3.472641383816587, 1.421799574118616));
        intersections = triangle.findIntersections(ray);
        assertTrue(intersections == null);
        // TC03: The point is outside the triangle and against vertex
        ray = new Ray(new Point(2.616212398061027, -3.251412796833307, 1.170595549723505), new Vector(-8.418056004665601, -2.781124346629511, -0.170595549723505));
        intersections = triangle.findIntersections(ray);
        assertTrue(intersections == null);

        // ============ Equivalence Partitions Tests ==============
        // TC04: The point is on edge
        ray = new Ray(new Point(2.616212398061027, -3.251412796833307, 1.170595549723505), new Vector(-1.794937791274396, 1.535410772220864, -0.309924741582222));
        intersections = triangle.findIntersections(ray);
        assertTrue(intersections == null);

        // TC05: The point is in vertex
        ray = new Ray(new Point(2.616212398061027, -3.251412796833307, 1.170595549723505), new Vector(-8.454382502662167, 1.917170938896994, 0.214287838950896));
        intersections = triangle.findIntersections(ray);
        assertTrue(intersections == null);

        // TC06: The point is on edge's continuation
        ray = new Ray(new Point(2.616212398061027, -3.251412796833307, 1.170595549723505), new Vector(-2.600716892646798, -0.923598515618852, 0.542599096836337));
        intersections = triangle.findIntersections(ray);
        assertTrue(intersections == null);
    }
    @Test
    @DisplayName("SphereTest -  testFindIntersections with distance test")
    void testFindIntersectionWithDistance()
    {
        // TC01: There is one intersection point
        Triangle triangle = new Triangle(new Point(-1.582330077979341, -2.299323910595358, 1.277013326971096), new Point(1.322758940265583, 2.348914309294042, -0.4682382198894), new Point(-0.99627593058881, 2.765600530137782, 2.596593764841225));
        Ray ray = new Ray(new Point(2.616212398061027, -3.251412796833307, 1.170595549723505), new Vector(-4.496887943191137, 6.588838393550958, -0.332645803159163));
        List<Intersectable.GeoPoint> intersections = triangle.findGeoIntersections(ray, 10);
        assertTrue(intersections.size()==1);

        // TC02: There are no intersection point
        intersections = triangle.findGeoIntersections(ray, 1);
        assertTrue(intersections==null);
    }
}



