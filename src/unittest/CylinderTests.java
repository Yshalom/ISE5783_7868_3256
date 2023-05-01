package unittest;

import geometries.Tube;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static primitives.Util.isZero;
import primitives.*;
import geometries.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CylinderTests {
    @Test
    @DisplayName("CylinderTests - getNormal tests")
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: The point is on the round surface
        Cylinder cylinder = new Cylinder(15, new Ray(new Point(-6.246922501406637, 1.041901752287348, 6.514943396109896), new Vector(2.971928366933252, 0.086203661468289, -1.096679798099546)), 2);
        Point point = new Point(2.558603467732269, 0.079135709190056, 4.96970732984834);
        Vector normal = cylinder.getNormal(point);
        assertTrue(normal.equals(new Vector(0.292070525203218, -0.600617638745398, 0.744280364066807)));

        // TC02: The point is on the base whom the cylinder's ray's point is on
        point = new Point(-6.012772014856864, 1.793531519547563, 7.208556711895471);
        normal = cylinder.getNormal(point);
        assertTrue(normal.equals(new Vector (-0.937815716061082, -0.027202253394281, 0.346066063229426)));

        // TC03: The point is on the base whom not the cylinder's ray's point is on
        point = new Point(7.765464752198763, 2.496860379888318, 1.257609453016765);
        normal = cylinder.getNormal(point);
        assertTrue(normal.equals(new Vector (0.937815716061082, 0.027202253394281, -0.346066063229426)));

        // =============== Boundary Values Tests ==================

        // TC04: The point is on the base's edge (the base whom the cylinder's ray's point is on)
        point = new Point(-5.57722446556085, 1.425852228827605, 8.35996008230783);
        normal = cylinder.getNormal(point);
        assertTrue(normal.equals(new Vector (-0.937815716061082, -0.027202253394281, 0.346066063229426)));

        // TC05: The point is on the base's edge (the base whom nat the cylinder's ray's point is on)
        point = new Point(8.477631677743924, 0.668997774651364, 3.043856124270849);
        normal = cylinder.getNormal(point);
        assertTrue(normal.equals(new Vector (0.937815716061082, 0.027202253394281, -0.346066063229426)));

        // TC06: The point is in the center of the base whom the cylinder's ray's point is on
        point = new Point(-6.246922501406637, 1.041901752287348, 6.514943396109896);
        normal = cylinder.getNormal(point);
        assertTrue(normal.equals(new Vector (-0.937815716061082, -0.027202253394281, 0.346066063229426)));

        // TC07: The point is in the center of the base whom nat the cylinder's ray's point is on
        point = new Point(7.820313239509595, 1.449935553201567, 1.323952447668504);
        normal = cylinder.getNormal(point);
        assertTrue(normal.equals(new Vector (0.937815716061082, 0.027202253394281, -0.346066063229426)));
    }

}
