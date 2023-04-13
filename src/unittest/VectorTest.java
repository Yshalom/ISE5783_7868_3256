package unittest;

import primitives.*;
import static primitives.Util.isZero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class VectorTest {

    @Test
    @DisplayName("VectorTest - test zero vector")
    void ZeroVector()
    {
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0));
    }

    @Test
    @DisplayName("VectorTest - test length")
    void length()
    {
        Vector v1 = new Vector(1, 2, 3);

        assertAll(
                () -> assertEquals(isZero(v1.lengthSquared() - 14), true),
                () -> assertEquals(isZero(new Vector(0, 3, 4).length() - 5), true)
        );
    }

    @Test
    @DisplayName("VectorTest - Test add & subtract")
    void add_subtract() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        //Zero Vector Tests
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> v1.add(new Vector(-1, -2, -3))),
                () -> assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1))
        );

        assertAll(
                () -> assertEquals(v1.add(v2).equals(new Vector(-1, -2, -3)), true),
                () -> assertEquals(v1.subtract(v2).equals(new Vector(3, 6, 9)), true)
        );
    }
}
