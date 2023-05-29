package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The Geometry class is an abstract class representing a general geometric shape in 3D Cartesian coordinate system.
 * It provides an abstract method for calculating the normal vector to the shape at a given point.
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;

    private Material material = new Material();

    /***
     * Getter function for material
     * @return The Geometry's material.
     */
    public Material getMaterial() {
        return material;
    }

    /***
     * Setter function for material
     * @param material The Geometry's new material
     * @return The Geometry (by this).
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /***
     * Getter function for emission
     * @return The Geometry's emission color
     */
    public Color getEmission() {
        return emission;
    }

    /***
     * Setter function for emission
     * @param emission The Geometry's new emission color
     * @return The Geometry (by this).
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Gets the normal vector to the geometry at the specified point.
     * @param p The point on the surface of the geometry to get the normal vector at.
     * @return The normal vector to the geometry at the specified point.
     */
    public abstract Vector getNormal(Point p);
}