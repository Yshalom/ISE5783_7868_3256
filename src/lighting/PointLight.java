package lighting;
import primitives.*;

public class PointLight extends Light implements LightSource {
    private Point position;
    private double kC = 1, kL = 0, kQ = 0;

    /***
     * Constructor function for PointLight class.
     * @param color The color of the light.
     * @param position The position of the light.
     */
    public PointLight(Color color, Point position)
    {
        super(color);
        this.position = position;
    }

    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    public Color getIntensity(Point p) {
        double d = p.distance(position);
        return getIntensity().scale(1 / (kC + kL*d + kQ*d*d));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    public double getDistance(Point point)
    {
        return point.distance(position);
    }
}
