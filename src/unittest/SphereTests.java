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
        Sphere sphere = new Sphere(new Point(2.5,1,-3), 3);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> sphere.getNormal(new Point(4.776055460839629, 2.204445679436092, -1.460882673585333)), "");
        // generate the test result
        Vector result = sphere.getNormal(new Point(4.776055460839629, 2.204445679436092, -1.460882673585333));
        // ensure result
        assertTrue(new Vector(0.75868515361321, 0.401481893145364, 0.513039108804889).equals(result));
    }
}
