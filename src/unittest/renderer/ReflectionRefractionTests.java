/**
 * 
 */
package unittest.renderer;

import static java.awt.Color.*;

import geometries.Plane;
import lighting.DirectionalLight;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
   private Scene scene = new Scene("Test scene");

   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheres() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(150, 150).setVPDistance(1000);

      scene.geometries.add( //
                           new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                           new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
                              .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
      scene.lights.add( //
                       new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                          .setKl(0.0004).setKq(0.0000006));

      camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage() //
         .writeToImage();
   }

   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheresOnMirrors() {
      Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(2500, 2500).setVPDistance(10000); //

      scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

      scene.geometries.add( //
                           new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 50, 100)) //
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                 .setKt(new Double3(0.5, 0, 0))),
                           new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20)) //
                              .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                                        new Point(670, 670, 3000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setKr(1)),
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                                        new Point(-1500, -1500, -2000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));

      scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
         .setKl(0.00001).setKq(0.000005));

      ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
      camera.setImageWriter(imageWriter) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage() //
         .writeToImage();
   }

   /** Produce a picture of a two triangles lighted by a spot light with a
    * partially
    * transparent Sphere producing partial shadow */
   @Test
   public void trianglesTransparentSphere() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(200, 200).setVPDistance(1000);

      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

      scene.geometries.add( //
                           new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                                        new Point(75, 75, -150)) //
                              .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                           new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                              .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                           new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

      scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
         .setKl(4E-5).setKq(2E-7));

      ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
      camera.setImageWriter(imageWriter) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage() //
         .writeToImage();
   }

   @Test
   public void Picture1() {
      Scene scene3 = new Scene("Picture1");

      Sphere sphere1 = new Sphere(new Point(50, -50, -110), 40);
      sphere1.setEmission(new Color(0, 0, 20));
      sphere1.setMaterial(new Material().setKd(new Double3(0.1,0.1,0.45)).setKs(new Double3(0.7,0.7,0.9)).setShininess(300));

      Sphere sphere2 = new Sphere(new Point(0, -130, -130), 20);
      sphere2.setEmission(new Color(30, 40, 20));
      sphere2.setMaterial(new Material().setKd(new Double3(0.4,0.4,0.2)).setKs(new Double3(0.1)).setShininess(8));

      Sphere sphere3 = new Sphere(new Point(60, -180, -104), 45);
      sphere3.setEmission(new Color(0, 0, 0));
      sphere3.setMaterial(new Material().setKd(new Double3(0.05,0.05,0.1)).setKs(new Double3(0.9)).setShininess(1000).setKt(0.85).setSnellParameter(10));

      Material material = new Material().setKd(new Double3(0.25, 0.25, 0.2)).setKs(new Double3(0.2, 0.4, 0.3)).setShininess(301).setKr(0.3);
      Plane plane1 = new Plane(new Point(-110, -110, -150), new Point(95, 100, -150), new Point(110, -110, -150));
      plane1.setMaterial(material);
      Plane plane2 = new Plane(new Point(-110, -110, -150), new Point(95, 100, -150), new Point(-75, 78, 100));
      plane2.setMaterial(material);

      scene3.geometries.add(plane1, plane2, sphere1, sphere2, sphere3);

      scene3.setAmbientLight(new AmbientLight(new Color(30, 30, 30), Double3.ONE));
      scene3.lights.add(new DirectionalLight(new Color(230, 230, 190), new Vector(1, 1, -1)));
      //PointLight pointLight1 = new PointLight(new Color(800, 600, 0), new Point(-3, -60, -100));
      //pointLight1.setKc(0.8).setKl(0.01).setKq(0.0005);
      //scene3.lights.add(pointLight1);
      //PointLight pointLight2 = new PointLight(new Color(450, 450, 600), new Point(10, 20, -50));
      //pointLight2.setKc(0.65).setKl(0.004).setKq(0.0006);
      //scene3.lights.add(pointLight2);
      double M = 1000000;
      SpotLight spotLight = new SpotLight(new Color(M*400, M*400, M*420), new Point(130,-170,-30), new Vector(-5,5,-4));
      spotLight.setKc(M).setKl(50).setKq(2);
      scene3.lights.add(spotLight);

      ImageWriter imageWriter = new ImageWriter("Picture1", 700, 700);
      Camera         camera3                 = new Camera(new Point(0, -1000, 600),
              new Vector(0, 1.3, -1), new Vector(0, 1, 1.3))
              .setVPSize(200, 200).setVPDistance(1000);
      camera3.setImageWriter(imageWriter)
              .setRayTracer(new RayTracerBasic(scene3))
              .renderImage()
              .writeToImage();
   }
}
