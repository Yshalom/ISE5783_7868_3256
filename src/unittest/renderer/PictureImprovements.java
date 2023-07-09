package unittest.renderer;

import geometries.*;
import lighting.*;
import renderer.*;
import scene.*;
import primitives.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import unittest.PointTests;

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
        Material floorMaterial = new Material().setKs(0.3).setShininess(20).setKd(new Double3(0.4, 0.4, 0.35)).setKr(0.1);

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
                new Point(130, 162, 279.5),
                new Point(130, 155, 279.5),
                new Point(130, 155, 0),
                new Point(130, 162, 0));
        lamp1.setEmission(new Color(50, 50, 47));;

        Polygon lamp2 = new Polygon(
                new Point(130, 172, 280),
                new Point(130, 145, 280),
                new Point(130, 156, 295),
                new Point(130, 161, 295));
        lamp2.setEmission(new Color(190, 230, 255));

        // Mirror
        Polygon mirror1 = new Polygon(
                new Point(-10, -199.5, 50),
                new Point(-10, -199.5, 250),
                new Point(80, -199.5, 250),
                new Point(80, -199.5, 50)
        );
        mirror1.setMaterial(new Material().setKr(0.8).setKs(0.8).setSnellParameter(5000));

        // pointLight2Sphere
        Sphere pointLight2Sphere = new Sphere(new Point(150, -130, 100), 5);
        pointLight2Sphere.setEmission(new Color(220, 50, 50)).setMaterial(new Material().setKt(0.01));
        Polygon pointLight2Wire = new Polygon(
                new Point(151.5, -130, 400),
                new Point(151.5, -130, 105),
                new Point(148.5, -130, 105),
                new Point(148.5, -130, 400)
        );
        pointLight2Wire.setEmission(new Color(50, 50, 47));

        // Add Spheres
        Sphere sphere1 = new Sphere(new Point(110, 0, 40), 40);
        sphere1.setEmission(new Color(50, 50, 10)).setMaterial(new Material().setKd(new Double3(0.85, 0.85, 0.1)).setKs(0.9).setShininess(500));

        Sphere sphere2 = new Sphere(new Point(50, 50, 31), 30);
        sphere2.setEmission(new Color(0, 10, 15)).setMaterial(new Material().setKd(new Double3(0.02, 0.07, 0.08)).setKs(0.9).setShininess(600).setKt(0.83).setSnellParameter(25));


        // Add lights
        PointLight pointLight1 = new PointLight(new Color(350,350,342), new Point(0,0,350));
        pointLight1.setKc(2).setKl(1E-4).setKq(2E-5);

        SpotLight spotLight1 = new SpotLight(new Color(225, 450, 825), new Point(130, 158.5, 279.5), new Vector(0,0,-1));
        spotLight1.setKc(2).setKl(3E-4).setKq(3E-4);

        AmbientLight ambientLight = new AmbientLight(new Color(30,30,27), 1);

        PointLight pointLight2 = new PointLight(new Color(75000, 7500, 6000), new Point(150, -130, 100));
        pointLight2.setKc(2.5).setKl(0.05).setKq(3E-4);

        SpotLight spotLight2 = new SpotLight(new Color(300,290,280), new Point(30,130,399.5), new Vector(0, -1, -5));
        SpotLight spotLight3 = new SpotLight(new Color(300,290,280), new Point(30,-130,399.5), new Vector(0, 1, -5));
        spotLight2.setKc(2).setKl(4E-3).setKq(1E-4);
        spotLight3.setKc(2).setKl(4E-3).setKq(1E-4);

        Scene scene = new Scene("PictureImprovements");
        scene.geometries.add(left, right, forward, floor, roof, Desk1, Desk2, Desk3, Desk4, Desk5, lamp1, lamp2, pointLight2Sphere, pointLight2Wire, sphere1, sphere2, mirror1);
        scene.setAmbientLight(ambientLight);
        scene.lights.add(pointLight1);
        scene.lights.add(spotLight1);
        scene.lights.add(pointLight2);
        scene.lights.add(spotLight2);
        scene.lights.add(spotLight3);

        // Without improvements
        Camera camera = new Camera(new Point(-300,0,200),new Vector(1,0,0),new Vector(0,0,1))
                .setVPSize(800,600).setVPDistance(500)
                .setImageWriter(new ImageWriter("PictureWithoutImprovements", 1000,750))
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImage().writeToImage();

        // With improvements
        camera.setImageWriter(new ImageWriter("PictureWithImprovements", 1000,750))
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImageWithImageAndRunTimeImprovements(33).writeToImage();
    }
}
