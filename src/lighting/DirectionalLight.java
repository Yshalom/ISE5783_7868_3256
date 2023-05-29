package lighting;
import primitives.*;

public class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    /***
     * Constructor function for DirectionalLight class.
     * @param color The color of the light.
     * @param direction The direction of the light.
     */
    public DirectionalLight(Color color, Vector direction)
    {
        super(color);
        this.direction = direction.normalize();
    }


    public Color getIntensity(Point p) {
        return super.getIntensity();
    }



    public Vector getL(Point p)
    {
        return direction;
    }
}
