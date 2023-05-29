package lighting;
import primitives.*;

public class AmbientLight extends Light {
    public static AmbientLight NONE=new AmbientLight(Color.BLACK,Double3.ZERO);

    /***
     * Constructor function for the AmbientLight class.
     * @param c The color of the light.
     * @param d The strength of the light.
     */
    public AmbientLight(Color c, Double3 d )
    {
        super(c.scale(d));
    }

    /***
     * Constructor function for the AmbientLight class.
     * @param c The color of the light.
     * @param d The strength of the light.
     */
    public AmbientLight(Color c, double d )
    {
        super(c.scale(d));
    }
}
