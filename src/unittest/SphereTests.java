package unittest;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import geometries.*;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;

import static org.junit.jupiter.api.Assertions.*;

public class SphereTests {
    /**
     * This function checks the sphere's getNormal function.
     */
    @Test
    @DisplayName("SphereTest - getNormal tests")
    void normal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Creating a sphere and checking the getNormal function - (Is the normal vector equals to the program's output?)
        Sphere sphere = new Sphere(new Point(2.5,1,-3), 3);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> sphere.getNormal(new Point(4.776055460839629, 2.204445679436092, -1.460882673585333)), "");
        // generate the test result
        Vector result = sphere.getNormal(new Point(4.776055460839629, 2.204445679436092, -1.460882673585333));
        // ensure result
        assertTrue(new Vector(0.75868515361321, 0.401481893145364, 0.513039108804889).equals(result));
    }

    @Test
    @DisplayName("SphereTest - testFindIntersections tests")
    void testFindIntersectionPoints()
    {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Two intersections
        Sphere sphere = new Sphere(new Point(-3.4, 3.5, 2), 2.3);
        Ray ray = new Ray(new Point(-2.1, 0.6, 1.2), new Vector(0.5, 9.5, 1.1));
        List<Point> intersections = sphere.findIntersections(ray);
        assertTrue(
        (intersections.indexOf(new Point(-1.858643221040532, 5.185778800229889, 1.730984913710829)) != -1)
                && (intersections.indexOf(new Point(-2.038445427961758, 1.769536868726604, 1.335420058484133)) != -1)
        );

        // TC02: One intersection
        sphere = new Sphere(new Point(-2, -3, 1), 3);
        ray = new Ray(new Point(0.2, -1.6, 2.4), new Vector(-4.5, 2,3));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections.indexOf(new Point(-0.740392608481126, -1.182047729563944, 3.026928405654084)) != -1);

        // TC03: No Intersections - the back of the ray point at the sphere.
        sphere = new Sphere(new Point(2, -3, 4), 4);
        ray = new Ray(new Point(-2,2,2), new Vector(-2,4,1));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections == null);

        // TC04: No Intersections - either the ray or the back of the ray don't point at the sphere.
        sphere = new Sphere(new Point(0, -2, 1), 1);
        ray = new Ray(new Point(0,0,0), new Vector(1, -1, -1));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections == null);

        // =============== Boundary Values Tests ==================

        // TC05: The ray's point is on the surface of the sphere.
        sphere = new Sphere(new Point(1, 2,2), 1);
        ray = new Ray(new Point(1.111295231062723, 1.038064558856064, 2.249586815785443), new Vector(-0.178146236049984, -1.518727444008292, -0.635382530785116));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections == null);
        ray = new Ray(new Point(1.111295231062723, 1.038064558856064, 2.249586815785443), new Vector(0.26890179243615, 2.352730712224302, -0.249586815785443));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections.indexOf(new Point(1.329031130147028, 2.943124143206193, 2.047490692727668)) != -1);

        //TC06: Either the ray or the ray's back point at the center of the sphere
        sphere = new Sphere(new Point(2, 1.5, 1.3), 3);
        ray = new Ray(new Point(2.33388569318817, -1.385806323431808, 2.048760447356328), new Vector(0.452033416234913, -3.906968515233804, 1.013714423424881));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections == null);
        ray = new Ray(new Point(2,1.5,1.3), new Vector(0.785919109423083, -6.792774838665611, 1.762474870781208));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections.indexOf(new Point(2.33388569318817, -1.385806323431808, 2.048760447356328)) != -1);
        ray = new Ray(new Point(2.785919109423083, -5.292774838665611, 3.062474870781208), new Vector(0.785919109423083, -6.792774838665611, 1.762474870781208));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections == null);
        ray = new Ray(new Point(2.33388569318817, -1.385806323431808, 2.048760447356328), new Vector(-1.015720057923762, 8.778966651742628, -2.277818488410566));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections.indexOf(new Point(1.66611430681183, 4.385806323431808, 0.551239552643672)) != -1);
        ray = new Ray(new Point(2, 1.5, 1.3), new Vector(0.514930059289592, -0.164680796154549, 0.7));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections.indexOf(new Point(3.746587725597012, 0.941420340459638, 3.674325184287449)) != -1);
        ray = new Ray(new Point(-1.46717058096745, 2.608842650330184, -3.41329914207296), new Vector(0.514930059289592, -0.164680796154549, 0.7));
        intersections = sphere.findIntersections(ray);
        assertTrue(
                (intersections.indexOf(new Point(3.746587725597012, 0.941420340459638, 3.674325184287449)) != -1)
                        && (intersections.indexOf(new Point(0.253412274402988, 2.058579659540362, -1.074325184287449)) != -1)
        );

        // TC07: Either the ray's direction vector or the back of the ray's direction vector is tangent with the surface of the sphere.
        sphere = new Sphere(new Point(4.346349994341999, -4.008099017677047, 1.660117325062423), 3.5);
        ray = new Ray(new Point(8.56729184135195, -6.056301512618791, 2.866656582468582), new Vector(-4.687581501582791, 4.669643936487269, 3.52082084249896));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections == null);
        ray = new Ray(new Point(6.472604315137252, -3.969629547060173, 4.439966720826513), new Vector(-4.687581501582791, 4.669643936487269, 3.52082084249896));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections == null);
        ray = new Ray(new Point(5.134308797210932, -2.63645516952613, 5.445154310918958), new Vector(-4.687581501582791, 4.669643936487269, 3.52082084249896));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections == null);


        // TC08: The ray's direction vector is perpendicular to the vector from the sphere's center to the ray's start point.
        sphere = new Sphere(new Point(3.514965948196176, 3.14311097938581, 0), 2.4);
        ray = new Ray(new Point(0,0,0), new Vector(0,0,1));
        intersections = sphere.findIntersections(ray);
        assertTrue(intersections == null);
    }
}
