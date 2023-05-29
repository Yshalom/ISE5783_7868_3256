package lighting;
import primitives.*;


public class SpotLight extends PointLight implements LightSource {
    private Vector direction;

    /***
     * Constructor function for SpotLight class.
     * @param color The color of the light.
     * @param position The position of the light.
     * @param direction The direction of the light.
     */
    public SpotLight(Color color, Point position, Vector direction)
    {
        super(color, position);
        this.direction = direction.normalize();
    }

    public Color getIntensity(Point p) {
        double dirDotL = direction.dotProduct(super.getL(p));
        if (dirDotL < 0)
            return Color.BLACK;
        Color intensity = super.getIntensity(p);
        return intensity.scale(dirDotL);
    }
}
