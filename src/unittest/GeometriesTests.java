package unittest;

import primitives.*;
import geometries.*;
import static primitives.Util.isZero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GeometriesTests {

    @Test
    @DisplayName("GeometriesTests - testFindIntersections tests")
    public void testFindIntersectionPoints() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Several geometries intersect (but not all of them)
        Geometries GeometriesSet = new Geometries();
        GeometriesSet.add(new Sphere(new Point(23,4,4), 3));
        GeometriesSet.add(new Plane(new Point(-5,3,1), new Point(-4,4,0), new Point(-7,8,1)));
        GeometriesSet.add(new Triangle(new Point(10, -6, 0), new Point(7, -4, 1), new Point(6, -9, 3)));
        Ray ray = new Ray(new Point(30, 7, 5), new Vector(-27, - 9, 0));
        List<Point> intersections = GeometriesSet.findIntersections(ray);
        assertEquals(isZero(intersections.size()), 3); // Two points with the sphere and one with the plane

        // =============== Boundary Values Tests ==================
        // TC02:

    }




}
