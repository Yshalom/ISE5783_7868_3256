package unittest;

import primitives.*;
import geometries.*;
import static primitives.Util.isZero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleTests {
    /**
     * This function checks the triangle's getNormal function.
     */
    @Test
    @DisplayName("TriangleTest - EQ getNormal test")
    void normal() {
        // ============ Equivalence Partitions Tests ==============
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
}



