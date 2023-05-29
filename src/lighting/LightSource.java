package lighting;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

public interface LightSource {
    /***
     * Return the intensity of the light at a specific point.
     * @param p The point to calculate the light at.
     * @return The color of the light source.
     */
    public Color getIntensity(Point p);

    /***
     * Return the direction of the light at a specific point.
     * @param p The point to calculate the direction at.
     * @return The direction of the light source.
     */
    public Vector getL(Point p);
}
