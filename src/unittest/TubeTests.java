package unittest;

import primitives.*;
import geometries.*;
import static primitives.Util.isZero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class TubeTests {
    /**
     * This function checks the tube's getNormal function.
     */
    @Test
    @DisplayName("TubeTest - EQ && BVA getNormal tests")
    void normal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Creating a tube and checking the getNormal function - (Is the normal vector equals to the program's output?)
        Tube tube = new Tube(new Ray(new Point(2.4,0.2,0.4), new Vector(-8,1,2)), 3);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> tube.getNormal(new Point(2.339920055118838, 2.528842653992836, 2.466763387277472)), "");
        // generate the test result
        Vector result = tube.getNormal(new Point(2.339920055118838, 2.528842653992836, 2.466763387277472));
        // ensure result
        assertTrue(new Vector(0.248302201468485, 0.742739778444003, 0.621838916651939).equals(result));


        // =============== Boundary Values Tests ==================
        // TC02: Creating a tube and checking the getNormal function when vector between P0 & p is perpendiculars to the direction vector - (Is the normal vector equals to the program's output?)
        // ensure there are no exceptions
        assertDoesNotThrow(() -> tube.getNormal(new Point(2.496523417813168, -2.309608863142369, 2.040898102823856)), "");
        // generate the test result
        result = tube.getNormal(new Point(2.496523417813168, -2.309608863142369, 2.040898102823856));
        // ensure result
        assertTrue(new Vector(0.032174472604389, -0.836536287714123, 0.546966034274619).equals(result));
    }
}
