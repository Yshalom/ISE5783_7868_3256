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
        // TC01: Several geometries get intersected (but not all of them)
        Geometries GeometriesSet = new Geometries();
        GeometriesSet.add(new Sphere(new Point(23,4,4), 3));
        GeometriesSet.add(new Plane(new Point(-5,3,1), new Point(-4,4,0), new Point(-7,8,1)));
        GeometriesSet.add(new Triangle(new Point(10, -6, 0), new Point(7, -4, 1), new Point(6, -9, 3)));
        Ray ray = new Ray(new Point(30, 7, 5), new Vector(-27, - 9, 0));
        List<Point> intersections = GeometriesSet.findIntersections(ray);
        assertEquals(intersections.size(), 3); // Two points with the sphere and one with the plane

        // =============== Boundary Values Tests ==================
        // TC02: All the geometries don't get intersected
        ray = new Ray(new Point(0.083132315458016, 8.29118968085811, 10.83194635064747), new Vector(0.63083430575638, -0.1134121108378, 5.83194635064747));
        intersections = GeometriesSet.findIntersections(ray);
        assertEquals(intersections, null); // Two points with the sphere and one with the plane


        // TC03: only one geometry gets intersected
        ray = new Ray(new Point(-3.474611759138696, -13.272986571240182, -3.90044844567042), new Vector(0.747902820924907, 9.129581993331406, 30.277962933008013));
        intersections = GeometriesSet.findIntersections(ray);
        assertEquals(intersections.size(), 1); // Two points with the sphere and one with the plane

        // TC04: All the geometries gets intersected
        ray = new Ray(new Point(-2.955577654619177, -13.893403731553825, -0.462801495258191), new Vector(33.52391260493789, 24.234802907334064, 6.615457925388881));
        intersections = GeometriesSet.findIntersections(ray);
        assertEquals(intersections.size(), 4); // Two points with the sphere and one with the plane

        // TC05: GeometriesSet is empty
        GeometriesSet = new Geometries();
        intersections = GeometriesSet.findIntersections(ray);
        assertEquals(intersections, null); // Two points with the sphere and one with the plane
    }




}
