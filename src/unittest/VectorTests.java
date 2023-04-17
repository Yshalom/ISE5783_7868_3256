package unittest;

import primitives.*;
import static primitives.Util.isZero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class  VectorTests {

    /**
     * This function checks the creating zero vector by three arguments which are contained zero.
     */
    @Test
    @DisplayName("VectorTest - test zero vector")
    void ZeroVector()
    {
        // =============== Boundary Values Tests ==================
        // TC01: zero vector creating
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0));
    }

    /**
     * This function checks the vector class's length functions
     */
    @Test
    @DisplayName("VectorTest - test length")
    void testLength()
    {
        // =============== Boundary Values Tests ==================
        // TC01: vector's length functions
        Vector v1 = new Vector(1, 2, 3);
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5));
    }

    /**
     * This function checks the vector class's lengthSquared functions
     */
    @Test
    @DisplayName("VectorTest - test length")
    void testLengthSquared()
    {
        // =============== Boundary Values Tests ==================
        // TC01: vector's lengthSquared functions
        Vector v1 = new Vector(1, 2, 3);
        assertTrue(isZero(v1.lengthSquared() - 14));
    }

    /**
     * This function checks the vector class's add function
     * It checks Zero Vector Exception and whether the calculation is correct.
     */
    @Test
    @DisplayName("VectorTest - Test add function")
    void testAdd() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // =============== Boundary Values Tests ==================
        // TC01: Zero Vector Exception
        assertThrows(IllegalArgumentException.class, () -> v1.add(new Vector(-1, -2, -3)));

        // ============ Equivalence Partitions Tests ==============
        // TC02: Adding two vectors
        assertTrue(v1.add(v2).equals(new Vector(-1, -2, -3)));
    }

    /**
     * This function checks the vector class's subtract function
     * It checks Zero Vector Exception and whether the calculation is correct.
     */
    @Test
    @DisplayName("VectorTest - Test subtract function")
    void testSubtract() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // =============== Boundary Values Tests ==================
        // TC01: Zero Vector Exception
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1));

        // ============ Equivalence Partitions Tests ==============
        // TC02: Subtracting two vectors
        assertTrue(v1.subtract(v2).equals(new Vector(3, 6, 9)));
    }

    /**
     * This function checks the vector class's dot-product function
     */
    @Test
    @DisplayName("VectorTest - test Dot-Product")
    void testDotProduct(){
        // ============ Equivalence Partitions Tests ==============
        // TC01: vector's dotProduct function
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        int res = -28;
        assertTrue(isZero( v1.dotProduct(v2) - res));
    }

    /**
     * This function checks the vector class's scale function
     */
    @Test
    @DisplayName("VectorTest - test scale")
    void testScaling(){
        // ============ Equivalence Partitions Tests ==============
        // TC01: vector's scale function
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-3, -6, -9);
        assertTrue(v1.scale(3).equals(v2));
    }

    /**
     * This function checks the vector class's cross-product function
     */
    @Test
    @DisplayName("VectorTest - test Cross-Product")
    void testCrossProduct(){
        // ============ Equivalence Partitions Tests ==============
        // TC01: vector's crossProduct function
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        assertThrows(Exception.class, () -> v1.crossProduct(v2));
        Vector vr = v1.crossProduct(v3);
        assertAll(
                () -> assertTrue(isZero(vr.length() - v1.length() * v3.length())),
                () -> assertTrue(isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))

        );
    }
    @Test
    @DisplayName("VectorTest - test vector normalization")
    void testNormalize(){
        // ============ Equivalence Partitions Tests ==============
        // TC01: vector's normalize function
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertTrue(isZero(u.length() - 1));
        assertThrows(Exception.class,()-> v.crossProduct(u));
        assertTrue(!(v.dotProduct(u) < 0));
    }

    @Test
    @DisplayName("VectorTest - operations with points and vectors")
    void testOperations(){
        Point p1 = new Point(1, 2, 3);
        assertTrue((p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0))));
        assertTrue(new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)));
    }
}
