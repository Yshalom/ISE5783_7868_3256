package unittest;

import primitives.*;
import static primitives.Util.isZero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class VectorTests {

    /**
     * This function checks the creating zero vector by three arguments which are contained zero.
     */
    @Test
    @DisplayName("VectorTest - test zero vector")
    void ZeroVector()
    {
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0));
    }

    /**
     * This function checks the length calculation by the vector class's functions ( length & lengthSquared ).
     */
    @Test
    @DisplayName("VectorTest - test length")
    void length()
    {
        Vector v1 = new Vector(1, 2, 3);

        assertAll(
                () -> assertTrue(isZero(v1.lengthSquared() - 14)),
                () -> assertTrue(isZero(new Vector(0, 3, 4).length() - 5))
        );
    }

    /**
     * This function checks the vector class's adding and subtracting by its functions (add, subtract)
     * It checks Zero Vector Exception and whether the calculation is correct.
     */
    @Test
    @DisplayName("VectorTest - Test add & subtract")
    void add_subtract() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        //Zero Vector Exception
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> v1.add(new Vector(-1, -2, -3))),
                () -> assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1))
        );

        // Calculation tests
        assertAll(
                () -> assertTrue(v1.add(v2).equals(new Vector(-1, -2, -3))),
                () -> assertTrue(v1.subtract(v2).equals(new Vector(3, 6, 9)))
        );
    }

    @Test
    @DisplayName("VectorTest - test Cross-Product")
    void Cross_Product(){
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
    @DisplayName("VectorTest - test vector normalization vs vector length and cross-product")
    void tvnvvlacp(){
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertTrue(isZero(u.length() - 1));
        assertThrows(Exception.class,()-> v.crossProduct(u));
        assertTrue(!(v.dotProduct(u) < 0));
    }

    @Test
    @DisplayName("VectorTest - est operations with points and vectors")
    void eowpav(){
        Point p1 = new Point(1, 2, 3);
        assertTrue((p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0))));
        assertTrue(new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)));
    }
}
