package renderer;
import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

public class Camera {
    private Point p0;
    private Vector vUp, vTo, vRight;
    private double width, height, distance;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

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
     * Setter function for the imageWriter
     * @param imageWriter The new imageWriter
     * @return The function return the camera (by "this").
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Setter function for the rayTracer
     * @param rayTracer The new rayTracer
     * @return The function return the camera (by "this").
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
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
        Point pij = pc;
        if (xj != 0)
            pij = pc.add(vRight.scale(xj));
        if ( yi != 0)
            pij = pc.add(vUp.scale(yi));

        return new Ray(p0,pij.subtract(p0));
    }

    /**
     * This function checks the correction of the camera.
     */
    public void renderImage()
    {
        if (p0 == null || vUp == null || vTo == null || vRight == null || width == 0 || height == 0 || distance == 0 || imageWriter == null || rayTracer == null)
            throw new MissingResourceException("", "", "");

        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++)
                imageWriter.writePixel(i, j, castRay(Nx, Ny, i, j));
    }

    /**
     * Add a grid to the picture
     * @param interval
     * @param color The grid's color.
     */
    public void printGrid(int interval, Color color)
    {
        if (imageWriter == null)
            throw new MissingResourceException("", "", "");

        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();

        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++)
                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(i, j, color);
    }

    /**
     * Print the image by imageWriter object.
     */
    public void writeToImage()
    {
        if (imageWriter == null)
            throw new MissingResourceException("", "", "");

        imageWriter.writeToImage();
    }

    /**
     * Calculate the color of a pixel on the image.
     * @param Nx The X axis resolution.
     * @param Ny The Y axis resolution.
     * @param i The X axis address.
     * @param j The Y axis address.
     * @return The pixel's color.
     */
    private Color castRay(int Nx, int Ny, int i, int j)
    {
        Ray ray = constructRay(Nx, Ny, i, j);
        return rayTracer.traceRay(ray);
    }
}
