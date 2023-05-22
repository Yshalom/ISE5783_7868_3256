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

    /*
    @Test
    @DisplayName("TubeTests - testFindIntersections tests")
    void testFindIntersectionPoints()
    {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray starts before and crosses the tube (2 points)
        Tube tube = new Tube(new Ray(new Point(-6.76671995074253, -4.27969724472397, 3.213816872877256), new Vector(0.734805054927259, 0.636717349024194, -0.233778845717378)), 3);
        Ray ray = new Ray(new Point(-1.71375963487867, -6.831681240429461, 3.87474551344917), new Vector(0.001126393950297, 0.963973351129804, -0.265996446495523));
        List<Point> intersections = tube.findIntersections(ray);
        assertTrue(
                (intersections.indexOf(new Point(3.746587725597012, 0.941420340459638, 3.674325184287449)) != -1)
                        && (intersections.indexOf(new Point(0.253412274402988, 2.058579659540362, -1.074325184287449)) != -1)
                        && intersections.size() == 2
        );

        // TC02: Ray start inside the tube (1 point)
        ray = new Ray(new Point(-0.301702496784081, 0.101733284851552, 1.727334620417823), new Vector(-0.155842765637593, 0.958599509407116, -0.238327532951739));
        intersections = tube.findIntersections(ray);
        assertTrue(
                intersections.size() == 1
                && intersections.get(0).equals(new Point(-1.040435109076641, 4.645728104434399, 0.597604149591211))
        );

        // TC03: Ray starts after the tube (0 points)
        ray = new Ray(new Point(-5.583033027730401, 2.607180479340663, 0.139875421227561), new Vector (-0.877394108286591, 0.465100000596328, 0.117735161227533));
        intersections = tube.findIntersections(ray);
        assertEquals(intersections, null);

        // TC04: Ray's line is outside the tube (0 points)
        ray = new Ray(new Point(8.140338605851195, 5.28200743921515, 2.744786537812356), new Vector(-0.985346155605859, -0.029385691026044, 0.168016174208549));
        intersections = tube.findIntersections(ray);
        assertEquals(intersections, null);

        // =============== Boundary Values Tests ==================

        // TC05: Ray's line is tangent with the tube surface (0 points)
        tube = new Tube(new Ray(new Point(-6.76671995074253, -4.27969724472397, 3.213816872877256), new Vector(0.734805054927259, 0.636717349024194, -0.233778845717378)), 2.299576183898664);
        ray = new Ray(new Point(3.108807340582757, -4.305345369589642, 3), new Vector(-0.802432214816969, 0.570568961829452, 0.174795882162011));
        intersections = tube.findIntersections(ray);
        assertEquals(intersections, null);

        // TC06: Ray starts on the tube surface and goes inside (1 point)
        ray = new Ray(new Point(-2.327014895662506, -2.470255345552355, 3.878662256014), new Vector(-0.936546055570636, 0.316170442839257, 0.151386052428588));
        intersections = tube.findIntersections(ray);
        assertTrue(
                intersections.size() == 1
                        && intersections.get(0).equals(new Point(-5.574718629429697, -1.373856592273493, 4.403630616927158))
        );

        // TC07: Ray starts on the tube surface and goes outside (0 points)
        ray = new Ray(new Point(-5.574718629429697, -1.373856592273493, 4.403630616927158), new Vector(-0.936546055570636, 0.316170442839257, 0.151386052428588));
        intersections = tube.findIntersections(ray);
        assertEquals(intersections, null);

        // TC08: Ray crosses the tube's middle line (2 points)
        ray = new Ray(new Point(6.710025955961759, 2.767705331654674, 0.105096728728974), new Vector(-0.986219086755935, -0.109461291349892, 0.124056997441919));
        assertTrue(
                (intersections.indexOf(new Point (-3.414976634113887, 1.643922722583186, 1.378725922570857)) != -1)
                        && (intersections.indexOf(new Point(4.57763783117394, 2.531029768452747, 0.373330908836104)) != -1)
                        && intersections.size() == 2
        );

        // TC09: Ray's direction vector verticals to the tube's direction vector (2 points)

    }
    */
}
