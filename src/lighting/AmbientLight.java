package lighting;
import primitives.*;

public class AmbientLight {
    private Color intensity;
    public static AmbientLight NONE=new AmbientLight(Color.BLACK,Double3.ZERO);
    public AmbientLight(Color c, Double3 d )
    {
        intensity=c.scale(d);
    }
    public AmbientLight(Color c, double d )
    {
        intensity=c.scale(d);
    }

    public Color getIntensity() {
        return intensity;
    }

}
