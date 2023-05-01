package unittest;

import primitives.*;
import geometries.*;
import static primitives.Util.isZero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlaneTests {
    /**
     * This function checks the plane's getNormal function.
     */
    @Test
    @DisplayName("PlaneTest - EQ getNormal test")
    void normal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Creating a plane and checking the getNormal function - (Is the normal vector's length equals 1? Is it perpendicular to the plan?)
        Point[] pts =
                { new Point(-1, 0, 2), new Point(2, -3, 0), new Point(0, 2, 0) };
        Plane plane = new Plane(pts[0], pts[1], pts[2]);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> plane.getNormal(new Point(1, 2, -1)), "");
        // generate the test result
        Vector result = plane.getNormal(new Point(1, 2, -1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 2; ++i)
            assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 2 : i - 1]))),
                    "Plane's normal is not orthogonal to one of the edges");
    }

    @Test
    @DisplayName("PlaneTest -  testFindIntersections test")
    void testFindIntersectionPoints() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: A ray that starts outside the plane, is not parallel to the plane, makes a non-right angle with the plane, and cuts the plane
        Plane p=new Plane(new Point(-4.138806049343,5.683421073213,0.5355724968185),new Point(-4.878757880743,4.170256527275,-1.383431104219), new Point(1.871275564824,-2.601144353237,3.511786649171));
        Ray ray=new Ray(new Point(-8.852193208605,-4.015001985440,-0.3743721971615),new Vector(15.33690201582, 8.669882352719, 1.818379243356));
        List<Point> intersections = p.findIntersections(ray);
        assertTrue(intersections.indexOf(new Point(-1.613222858762, 0.07715568911586, 0.4838971856541)) != -1);

        // TC02: A ray that starts outside the plane, is not parallel to the plane, makes a non-right angle with the plane, and does not intersect the plane
        ray=new Ray(new Point(2.000000000000,0.000000000000,3.161895862457),new Vector(3.274747811327, 3.900471083023, 0.2322749124949));
        intersections = p.findIntersections(ray);
        assertTrue(intersections==null);

        // =============== Boundary Values Tests ==================

        // TC03: A ray parallel to the plane is out of the plane.
        ray=new Ray(new Point(2,1,1),new Vector(3.099702143682, -0.4619545964766, 3.870647610685));
        intersections = p.findIntersections(ray);
        assertTrue(intersections==null);

        // TC04: A ray parallel to the plane is on of the plane.
        ray=new Ray(new Point(2,1,1),new Vector(-0.2998429736477, -1.438328584111, 1.315056004158));
        intersections = p.findIntersections(ray);
        assertTrue(intersections==null);

        // TC05:A ray perpendicular to the plane is initially inside the plane.
        ray=new Ray(new Point(-0.8192477845502, 1.882752226942, 2.654527898248),new Vector(4.587690655411, 2.098278277433, -3.423500818726));
        intersections = p.findIntersections(ray);
        assertTrue(intersections==null);

        // TC06:A ray perpendicular to the plane initially after in the plane.
        ray=new Ray(new Point(-0.1912522330315, 2.169979401364, 2.185894887379),new Vector(4.587690655411, 2.098278277433, -3.423500818726));
        intersections = p.findIntersections(ray);
        assertTrue(intersections==null);

        // TC07:A ray perpendicular to a plane is initially perpendicular to the plane.
        ray=new Ray(new Point(-2.073547940014, 1.309071269076, 3.590532073703),new Vector(4.587690655411, 2.098278277433, -3.423500818726));
        intersections = p.findIntersections(ray);
        assertTrue(intersections.indexOf(new Point(-0.8192477845502,1.882752226942,2.654527898248)) != -1);

        // TC07:One case of a ray that is neither parallel nor perpendicular to the plane but starts inside the plane.
        ray=new Ray(new Point(-0.8192477845502, 1.882752226942, 2.654527898248),new Vector((3.640248346358, -0.5047581041989, -2.654527898248)));
        intersections = p.findIntersections(ray);
        assertTrue(intersections==null);

        // TC07:One case of a ray that is neither parallel nor perpendicular to the plane but starts inside the plane.
        ray=new Ray(new Point(-0.8192477845502, 1.882752226942, 2.654527898248),new Vector((3.640248346358, -0.5047581041989, -2.654527898248)));
        intersections = p.findIntersections(ray);
        assertTrue(intersections==null);
    }


}
