package geometries;
import primitives.*;
public class Tube extends RadialGeometry {
    protected final Ray axiRay;
    public Tube (Ray a ,double b){
        super(b);
        axiRay=a;
    }
}