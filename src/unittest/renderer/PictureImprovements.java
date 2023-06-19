package unittest.renderer;

import geometries.*;
import lighting.*;
import renderer.*;
import scene.*;
import primitives.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PictureImprovements {

    @Test
    @DisplayName("PictureImprovements -  test")
    void PictureImprovements()
    {
        //TC01: Scene with 10 geometries and 3 lights\
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
        Material floorMaterial = new Material().setKs(0.3).setShininess(20).setKd(0.6).setKt(0.1);

        left.setMaterial(wallMaterial);
        right.setMaterial(wallMaterial);
        roof.setMaterial(wallMaterial);
        forward.setMaterial(wallMaterial);
        floor.setMaterial(floorMaterial);

        SpotLight spotLight = new SpotLight(new Color(500,500,450), new Point(0,0,370), new Vector(0,0,-1));

        Scene scene = new Scene("PictureImprovements");
        scene.geometries.add(left,roof,right,forward,floor);
        scene.lights.add(spotLight);

        Camera camera = new Camera(new Point(-250,0,200),new Vector(1,0,0),new Vector(0,0,1))
                .setVPSize(500,500).setVPDistance(450)
                .setImageWriter(new ImageWriter("PictureImprovements", 1000,1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImage().writeToImage();

    }
}
