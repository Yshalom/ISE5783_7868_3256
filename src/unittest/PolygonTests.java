package unittest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import geometries.*;
import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

import static org.junit.jupiter.api.Assertions.*;

/** Testing Polygons
 * @author Dan */
public class PolygonTests {

    /** Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}. */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /** Test method for {@link geometries.Polygon#getNormal(primitives.Point)}. */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point[] pts =
                { new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1) };
        Polygon pol = new Polygon(pts);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = pol.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1]))),
                    "Polygon's normal is not orthogonal to one of the edges");
    }

    @Test
    @DisplayName("PolygonTests - testFindIntersections tests")
    void testFindIntersectionPoints()
    {
        // ============ Equivalence Partitions Tests ==============

        // TC01: The Point is on the polygon (Polygon with 4 edges)
        Polygon polygon = new Polygon(new Point(1.786886601630041, 3.688343322358688, 3.081792689896019), new Point(3.664549372173367, -1.054594469892957, 2.942484583345969), new Point(0.360395308419861, -6.768194294581608, 0.751378991088025), new Point(-3.454678696879648, -1.926369927440817, 0.20356790573192));
        Ray ray = new Ray(new Point(-4.684003061182324, -8.525873938258528, 3.414684282421515), new Vector(1.623218982453868, 1.992605164185262, -0.824241142412184));
        List<Point> intersections = polygon.findIntersections(ray);
        assertTrue(intersections.indexOf(new Point(-0.25452670590427, -3.088408287862584, 1.16547657180019)) != -1);

        // TC02: The point is outside and against edge (Polygon with 4 edges)
        ray = new Ray(new Point(-4.684003061182324, -8.525873938258528, 3.414684282421515), new Vector(2.8852272976247, 8.016552820562733, -0.980817436842589));
        intersections = polygon.findIntersections(ray);
        assertTrue(intersections == null);

        // TC03: The point is outside the polygon and against vertex (Polygon with 4 edges)
        ray = new Ray(new Point(-4.684003061182324, -8.525873938258528, 3.414684282421515), new Vector(6.669182321069779, 5.286881115859902, -0.078330107016742));
        intersections = polygon.findIntersections(ray);
        assertTrue(intersections == null);

        // ============ Equivalence Partitions Tests ==============

        // TC04: The point is on edge (Polygon with 6 edges)
        polygon = new Polygon(new Point(1.228545959668867, 0.65819260769805, 1.735946337006942), new Point (3.45038818290201, -3.240527858130735, 2.41491730086714),
                new Point(2.698616351819513, -6.463012483259682, 0.980827300605363), new Point(-1.38436765820143, -6.372955079475592, -2.142900494847161),
                new Point(-5.575161942081882, 0.655295989227827, -3.509861787047469), new Point(-4.686752281299647, 4.617328119603465, -1.774322363354561));
        ray = new Ray(new Point(1.179513157144087, 2.606788081442247, 1.568282399587051), new Vector(-1.196519996790415, -1.216204690504657, -0.564150321959248));
        intersections = polygon.findIntersections(ray);
        assertTrue(intersections == null);

        // TC05: The point is in vertex (Polygon with 6 edges)
        ray = new Ray(new Point(3.721633969959956, 0.58736638635294, 2.201443917616449), new Vector(-0.614319325546135, -4.233733530315943, -0.732976992395955));
        intersections = polygon.findIntersections(ray);
        assertTrue(intersections == null);

        // TC06: The point is on edge's continuation (Polygon with 6 edges)
        ray = new Ray(new Point(4.146893962612168, 5.175861383949222, -2), new Vector(-1.240028547733657, -3.432232241119443, 0.035225391934364));
        intersections = polygon.findIntersections(ray);
        assertTrue(intersections == null);
    }
}
