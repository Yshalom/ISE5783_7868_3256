package unittest.renderer;

import geometries.*;
import lighting.*;
import renderer.*;
import scene.*;
import primitives.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PictureImprovements {

    @Test
    @DisplayName("PictureImprovements -  test")
    void PictureImprovements()
    {
        //TC01: Scene with 10 geometries and 3 lights

        // Add walls

        Point   p0 = new Point(0,0,0),
                p1 = new Point(0,-200,0),
                p2=new Point(0,200,0),
                p3=new Point(0,0,400),
                p4=new Point(200,0,0);

        Plane   left=new Plane(p2,p2.subtract(p3),p2.subtract(p4)),
                right=new Plane(p1,p1.subtract(p3),p1.subtract(p4)),
                floor=new Plane(p0,p2,p4),
                roof=new Plane(p3,p3.subtract(p4),p3.subtract(p1)),
                forward=new Plane(p4,p4.subtract(p2),p4.subtract(p3));

        Material wallMaterial = new Material().setKd(0.9);
        Material floorMaterial = new Material().setKs(0.3).setShininess(20).setKd(new Double3(0.4, 0.4, 0.35)).setKt(0);

        Color WallEmission = new Color(30, 30, 25);
        left.setMaterial(wallMaterial).setEmission(WallEmission);
        right.setMaterial(wallMaterial).setEmission(WallEmission);
        forward.setMaterial(wallMaterial).setEmission(WallEmission);
        floor.setMaterial(floorMaterial).setEmission(WallEmission);
        roof.setMaterial(wallMaterial).setEmission(new Color(95, 95, 90));

        // Add Geometries

        // Desk
        Polygon Desk1 = new Polygon(
                new Point(100, -100, 60),
                new Point(100, -50, 60),
                new Point(150, -50, 60),
                new Point(150, -100, 60));
        Polygon Desk2 = new Polygon(
                new Point(100, -50, 60),
                new Point(100, -55, 60),
                new Point(100, -55, 0),
                new Point(100, -50, 0));
        Polygon Desk3 = new Polygon(
                new Point(100, -100, 60),
                new Point(100, -95, 60),
                new Point(100, -95, 0),
                new Point(100, -100, 0));
        Polygon Desk4 = new Polygon(
                new Point(150, -100, 60),
                new Point(150, -95, 60),
                new Point(150, -95, 0),
                new Point(150, -100, 0));
        Polygon Desk5 = new Polygon(
                new Point(150, -50, 60),
                new Point(150, -55, 60),
                new Point(150, -55, 0),
                new Point(150, -50, 0));

        Material DeskMaterial = new Material().setKd(new Double3(0.4, 0.3, 0.1)).setKs(new Double3(0.2,0.1,0.05)).setShininess(10);
        Desk1.setMaterial(DeskMaterial);
        Desk2.setMaterial(DeskMaterial);
        Desk3.setMaterial(DeskMaterial);
        Desk4.setMaterial(DeskMaterial);
        Desk5.setMaterial(DeskMaterial);


        // Lamp
        Polygon lamp1 = new Polygon(
                new Point(130, 162, 279),
                new Point(130, 155, 279),
                new Point(130, 155, 0),
                new Point(130, 162, 0));
        lamp1.setMaterial(new Material().setKd(0.7));

        Polygon lamp2 = new Polygon(
                new Point(130, 172, 280),
                new Point(130, 145, 280),
                new Point(130, 156, 295),
                new Point(130, 161, 295));
        lamp2.setEmission(new Color(190, 230, 255));

        Point[] points = new Point[] {

        };
        List<Sphere> sphereList = new ArrayList<Sphere>();
        for (int i = 0; i < points.length; i++)
            sphereList.add(new Sphere(points[i], 1));


        // Add light
        PointLight pointLight = new PointLight(new Color(400,400,360), new Point(0,0,350));
        pointLight.setKc(2).setKl(1E-5).setKq(1E-7);

        SpotLight spotLight1 = new SpotLight(new Color(150, 300, 500), new Point(130, 158.5, 279.5), new Vector(0,0,-1));
        spotLight1.setKc(2).setKl(5E-4).setKq(5E-6);

        AmbientLight ambientLight = new AmbientLight(new Color(30,30,27), 1);

        Scene scene = new Scene("PictureImprovements");
        scene.geometries.add(left, right, forward, floor, roof, Desk1, Desk2, Desk3, Desk4, Desk5, lamp1, lamp2);
        for (int i = 0; i < sphereList.size(); i++)
            scene.geometries.add(sphereList.get(i));
        scene.setAmbientLight(ambientLight);
        scene.lights.add(pointLight);
        scene.lights.add(spotLight1);

        Camera camera = new Camera(new Point(-200,0,200),new Vector(1,0,0),new Vector(0,0,1))
                .setVPSize(800,600).setVPDistance(400)
                .setImageWriter(new ImageWriter("PictureImprovements", 800,600))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImage().writeToImage();
    }
}
