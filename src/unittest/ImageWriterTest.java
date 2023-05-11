package unittest;

import renderer.ImageWriter;
import primitives.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
public class ImageWriterTest {
    @Test
    @DisplayName("imagetest")
    void imagetest ()
    {
        ImageWriter writer = new ImageWriter("TestImageX800Y500", 800, 500);
        Color red = new Color(255, 0, 0);
        Color yellow = new Color(255, 255, 0);

        for (int i = 0; i < 800; i++)
            for (int j = 0; j < 500; j++)
            {
                if (i % 50 == 0 || j % 50 == 0)
                    writer.writePixel(i, j, red);
                else
                    writer.writePixel(i, j, yellow);
            }

        writer.writeToImage();
    }
}
