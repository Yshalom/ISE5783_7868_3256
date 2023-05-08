package unittest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import geometries.*;
import primitives.*;
import renderer.Camera;
import java.util.List;

public class IntegrationTests {

    /**
     * The function use the camera and the geometry to calculate the amount of the intersections between the construct rays and the geometry.
     * @param intersectable The geometry
     * @param camera The camera
     * @return the amount of the intersections
     */
    private int FindInersectionWithConstructRays(Intersectable intersectable, Camera camera)
    {
        int sum = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
            {
                List<Point> l = intersectable.findIntersections(camera.constructRay(3,3,i,j));
                if (l != null)
                    sum += l.size();
            }
        return sum;
    }

    /**
     * Test for sphere and camera;
     * the function builds camera and sphere and checks the findIntersections && constructRay functions with the sphere.
     */
    @Test
    @DisplayName("IntegrationTests - Sphere test")
    void testSphereIntegration()
    {
        // TC01: Two intersection points
        Camera camera = new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0))
                .setVPDistance(1).setVPSize(3,3);
        Sphere sphere = new Sphere(new Point(0,0,-3), 1);
        assertEquals(FindInersectionWithConstructRays(sphere, camera), 2);

        // TC02: 18 intersection points
        camera = new Camera(new Point(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0))
                .setVPDistance(1).setVPSize(3,3);
        sphere = new Sphere(new Point(0,0,-2.5), 2.5);
        assertEquals(FindInersectionWithConstructRays(sphere, camera), 18);

        // TC03: 10 intersection points
        sphere = new Sphere(new Point(0,0,-2), 2);
        assertEquals(FindInersectionWithConstructRays(sphere, camera), 10);

        // TC04: 9 intersection points
        sphere = new Sphere(new Point(0,0,-2), 4);
        assertEquals(FindInersectionWithConstructRays(sphere, camera), 9);

        // TC05: There are no intersection points
        sphere = new Sphere(new Point(0,0,1), 0.5);
        assertEquals(FindInersectionWithConstructRays(sphere, camera), 0);
    }

    /**
     * Test for plane and camera;
     * the function builds camera and plane and checks the findIntersections && constructRay functions with the plane.
     */
    @Test
    @DisplayName("IntegrationTests - Plane tests")
    void testPlaneIntegration()
    {
        // TC01: 9 intersection points
        Camera camera = new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0))
                .setVPDistance(1).setVPSize(3,3);
        Plane plane = new Plane(new Point(0, 0,-3), new Vector(0,0,1));
        assertEquals(FindInersectionWithConstructRays(plane, camera), 9);

        // TC02: 9 intersection points (incline plane)
        plane = new Plane(new Point(0, 0,-3), new Vector(0,1,-4));
        assertEquals(FindInersectionWithConstructRays(plane, camera), 9);

        // TC03: 6 intersection points
        plane = new Plane(new Point(0, 0,-2), new Vector(0,2,-1));
        assertEquals(FindInersectionWithConstructRays(plane, camera), 6);
    }


    /**
     * Test for triangle and camera;
     * the function builds camera and triangle and checks the findIntersections && constructRay functions with the triangle.
     */
    @Test
    @DisplayName("IntegrationTests - Triangle tests")
    void testTriangleIntegration()
    {
        // TC01: One intersection point
        Camera camera = new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0))
                .setVPDistance(1).setVPSize(3,3);
        Triangle triangle = new Triangle(new Point(0,1,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
        assertEquals(FindInersectionWithConstructRays(triangle, camera), 1);

        // TC02: Two intersection point
        triangle = new Triangle(new Point(0,20,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
        assertEquals(FindInersectionWithConstructRays(triangle, camera), 2);
    }
}
