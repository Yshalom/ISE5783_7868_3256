package lighting;
import primitives.Color;

/***
 * Light source class.
 * intensity - This parameter is the color of the light.
 */
abstract class Light {
    private Color intensity;

    /***
     * Constructor function for the Light class.
     * @param intensity The color of the light.
     */
    Light(Color intensity)
    {
        this.intensity = intensity;
    }

    /***
     * Getter function for intensity.
     * @return The color of the light source.
     */
    public Color getIntensity() {
        return intensity;
    }
}
