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

        // TC01: The Point is in the triangle
        Triangle triangle = new Triangle(new Point(-3.016442487399665, -4.815368353714334, 0), new Point(3.670876869139111, 2.961854754321699, 0), new Point(-3.794700379681267, 9.25261385710887, 4.171576868105575));
        Ray ray = new Ray(new Point(7.74910570454464, -4.541844793493148, 2.639160875740266), new Vector(-13.018119424015962, 7.93812746181392, -1.468565326016762));
        List<Point> intersections = triangle.findIntersections(ray);
        assertTrue(intersections.indexOf(new Point(-2.224595828420366, 1.53987188585355, 1.514034291301992)) != -1);
    }
}



