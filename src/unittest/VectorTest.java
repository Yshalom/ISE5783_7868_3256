package unittest;

import primitives.*;
import static primitives.Util.isZero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class VectorTest {

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
}
