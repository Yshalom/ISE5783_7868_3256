package unittest;

import primitives.*;
import geometries.*;
import static primitives.Util.isZero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class PlaneTests {
    /**
     * This function checks the plane's getNormal function.
     */
    @Test
    @DisplayName("PlaneTest - EQ getNormal test")
    void normal() {
        // ============ Equivalence Partitions Tests ==============
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
        for (int i = 0; i < 3; ++i)
            assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1]))),
                    "Plane's normal is not orthogonal to one of the edges");
    }
}
