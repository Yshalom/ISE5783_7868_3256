package unittest;

import primitives.*;
import geometries.*;
import static primitives.Util.isZero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class PointTests {
    /**
     * This function checks the add function.
     */
    // ============ Equivalence Partitions Tests ==============
    // TC01: Creating vector & point and checking the add function
    @Test
    @DisplayName("PointTest -testAdd")
    void testAdd(){
        Point p1 = new Point(1,2,5);
        Vector v = new Vector(3,4,5);
        Point p2 = new Point(4,6, 10);

        assertTrue(p1.add(v).equals(p2));

    }
    /**
     * This function checks the subtract function.
     */
    // ============ Equivalence Partitions Tests ==============
    // TC02: Creating two point and checking the subtract function
    @Test
    @DisplayName("PointTest -testSubtract")
    void testSubtract(){
        Point p1 = new Point(1,2,5);
        Point p2 = new Point(3,4,5);
        Vector v = new Vector(-2,-2, 0);
        assertTrue(p1.subtract(p2).equals(v));
    }
    /**
     * This function checks the testDistanceSquared function.
     */
    // ============ Equivalence Partitions Tests ==============
    // TC02: Creating two point and checking the testDistanceSquared function
    @Test
    @DisplayName("PointTest -testDistanceSquared")
    void testDistanceSquared() {
        Point p1 = new Point(1, 2, 5);
        Point p2 = new Point(3, 4, 5);
        int res = 8;
        assertTrue(isZero( p1.distanceSquared(p2) - res));
    }
    /**
     * This function checks the testDistance function.
     */
    // ============ Equivalence Partitions Tests ==============
    // TC02: Creating two point and checking the testDistance function
    @Test
    @DisplayName("PointTest -testDistance")
    void testDistance(){
        Point p1 = new Point(1, 2, 5);
        Point p2 = new Point(3, 4, 5);
        int res = 8;
        assertTrue(isZero( p1.distance(p2)*p1.distance(p2) - res));
    }

}