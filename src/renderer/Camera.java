package renderer;
import primitives.*;
import static primitives.Util.isZero;

public class Camera {
    private Point p0;
    private Vector vUp, vTo, vRight;
    private double width, height, distance;

    /**
     * Constructor to the Camera class.
     * @param p The location of the camera.
     * @param v1 A vector which directs to the To (foreword) direction.
     * @param v2 A vector which directs to the Up direction.
     */
    public Camera (Point p,Vector v1,Vector v2)
    {
        if(!isZero(v1.dotProduct(v2))) //v1 and v2 are orthogonal.
        {
            throw new IllegalArgumentException();
        }
        vRight=(v1.crossProduct(v2)).normalize();
        vTo=v1.normalize();
        vUp=v2.normalize();
        p0=p;
    }

    /**
     * Setter function for the View Plane's size.
     * @param width The size of the View Plane's width.
     * @param height The size of the View Plane's height.
     * @return The function return the camera (by "this").
     */
    public Camera setVPSize(double width,double height)
    {
        this.width=width;
        this.height=height;
        return this;
    }

    /**
     * Setter function for the View Plane's distance (the distance between the camera and the View Plane)
     * @param distance The distance between the camera and the View Plane
     * @return The function return the camera (by "this").
     */
    public Camera setVPDistance(double distance)
    {
        this.distance=distance;
        return this;
    }

    /**
     * find construct ray for a pixel on the View Plane.
     * @param nX The amount of pixels in the X axis (right-left-axis).
     * @param nY The amount of pixels in the Y axis (up-down-axis).
     * @param j The pixel's location on the X axis.
     * @param i The pixel's location on the Y axis.
     * @return A ray from the camera to the pixel[i,j].
     */
    public Ray constructRay(int nX, int nY, int j, int i)
    {
        Point pc=p0.add(vTo.scale(distance));
        double Ry=height/nY;
        double Rx=width/nX;
        double yi=-(i-(nY-1)/2.0)*Ry;
        double xj=(j-(nX-1)/2.0)*Rx;
        Point pij;
        if (isZero(yi)) { // yi == 0
            if (isZero(xj)) // yi == 0 && xj == 0
                pij = pc;
            else
                pij = pc.add(vRight.scale(xj));
        }
        else if (isZero(xj)) // xj == 0
            pij = pc.add(vUp.scale(yi));
        else
            pij=pc.add(vRight.scale(xj).add(vUp.scale(yi)));

        return new Ray(p0,pij.subtract(p0));
    }

}
