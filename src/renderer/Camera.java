package renderer;
import geometries.Polygon;
import primitives.*;
import primitives.Vector;

import java.util.*;

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
    public Ray constructRay(int nX, int nY, int i, int j)
    {
        Point pc=p0.add(vTo.scale(distance));
        double Ry=height/nY;
        double Rx=width/nX;
        double yj=-(j-(nY-1)/2.0)*Ry;
        double xi=(i-(nX-1)/2.0)*Rx;
        Point pij;
        if (isZero(yj)) { // yj == 0
            if (isZero(xi)) // yj == 0 && xi == 0
                pij = pc;
            else
                pij = pc.add(vRight.scale(xi));
        }
        else if (isZero(xi)) // xj == 0
            pij = pc.add(vUp.scale(yj));
        else
            pij=pc.add(vRight.scale(xi).add(vUp.scale(yj)));

        return new Ray(p0,pij.subtract(p0));
    }

    /**
     * find construct beam of ray for a pixel on the View Plane.
     * @param nX The amount of pixels in the X axis (right-left-axis).
     * @param nY The amount of pixels in the Y axis (up-down-axis).
     * @param Pj The pixel's location on the X axis.
     * @param Pi The pixel's location on the Y axis.
     * @param amountOfRaysInEachAxis the amount of the beam will be amountOfRaysInEachAxis squared.
     * @return A beam of ray from the camera to the pixel[i,j].
     */
    public List<Ray> constructBeam(int nX, int nY, int Pi, int Pj, int amountOfRaysInEachAxis)
    {
        if (amountOfRaysInEachAxis <= 1)
            return null;
        Random random = new Random();

        double Ry=height/nY;
        double Rx=width/nX;
        Point pij = p0.add(vTo.scale(distance));
        if (!isZero(Rx * Pi - width/2))
            pij = pij.add(vRight.scale(Rx * Pi - width/2));
        if (!isZero(Ry * Pj - height/2))
            pij = pij.add(vUp.scale(- Ry * Pj + height/2));

        Vector v1 = vUp.scale(Ry / (amountOfRaysInEachAxis - 1));
        Vector v2 = vRight.scale(Rx / (amountOfRaysInEachAxis - 1));

        // Make a jittered grid
        List<Ray> Beam = new ArrayList<Ray>(amountOfRaysInEachAxis * amountOfRaysInEachAxis);
        for (int i = 0; i < amountOfRaysInEachAxis; i++)
            for (int j = 0; j < amountOfRaysInEachAxis; j++) {
                Point aimPoint = pij;

                if (i != 0 && i != amountOfRaysInEachAxis - 1)
                    aimPoint = aimPoint.add(v1.scale(i + random.nextDouble() - 0.5));
                else if (i != 0)
                    aimPoint = aimPoint.add(v1.scale(i));
                // if (i == 0) do nothing

                if (j != 0 && j != amountOfRaysInEachAxis - 1)
                    aimPoint = aimPoint.add(v2.scale(j + random.nextDouble() - 0.5));
                else if (j != 0)
                    aimPoint = aimPoint.add(v2.scale(j));
                // if (j == 0) do nothing

                Beam.add(new Ray(p0, aimPoint.subtract(p0)));
            }
        return Beam;
    }

    /**
     * This function checks the correction of the camera.
     * @return The function return the camera (by this).
     */
    public Camera renderImage()
    {
        return renderImage(false, 0);
    }

    public Camera renderImageWithImprovements(int amountOfRaysInEachAxis)
    {
        return renderImage(true, amountOfRaysInEachAxis);
    }

    private Camera renderImage(boolean WithImprovements, int amountOfRaysInEachAxis)
    {
        if (p0 == null || vUp == null || vTo == null || vRight == null || width == 0 || height == 0 || distance == 0 || imageWriter == null || rayTracer == null)
            throw new MissingResourceException("", "", "");

        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++) {
                if (WithImprovements) {
                    List<Ray> Beam = constructBeam(Nx, Ny, i, j, amountOfRaysInEachAxis);
                    Color color = Color.BLACK;
                    for (Ray ray: Beam) {
                        color = color.add(rayTracer.traceRay(ray));
                    }
                    color = color.scale(1.0 / amountOfRaysInEachAxis / amountOfRaysInEachAxis);
                    imageWriter.writePixel(i, j, color);
                }
                else
                    imageWriter.writePixel(i, j, castRay(Nx, Ny, i, j));
            }
        return this;
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
