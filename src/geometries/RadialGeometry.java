package geometries;

 abstract public class RadialGeometry extends Geometry {
   protected final double radius;
   public RadialGeometry(double a){
       radius=a;
   }

     public double getRadius() {
         return radius;
     }
 }