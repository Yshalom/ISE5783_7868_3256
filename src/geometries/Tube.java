package geometries;
import primitives.*;
public class Tube extends RadialGeometry {
    protected final Ray axiRay;
    public Tube (Ray a ,double b){
        radius=b;
        axiRay=a;
    }

}
