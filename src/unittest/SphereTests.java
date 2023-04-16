package unittest;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import geometries.*;
import primitives.*;
import static primitives.Util.isZero;

import static org.junit.jupiter.api.Assertions.*;

public class SphereTests {
    /**
     * This function checks the sphere's getNormal function.
     */
    @Test
    @DisplayName("PlaneTest - EQ getNormal test")
    void normal() {
        Sphere sphere = new Sphere(new Point(2,-3.5,1), 4);

        // ensure there are no exceptions
        assertDoesNotThrow(() -> sphere.getNormal(new Point(-2, -3.5, 1)), "");

        // generate the test result
        Vector result = sphere.getNormal(new Point(1, 2, -1));
        // ensure result
        assertTrue(new Vector(-1,0,0).equals(result));
    }
}
