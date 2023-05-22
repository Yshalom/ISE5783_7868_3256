package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {
    public String name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight=AmbientLight.NONE;
    public Geometries geometries=new Geometries();
    public Scene(String name)
    {
        this.name=name;
    }

    /**
     * Setter function for ambientLight
     * @param ambientLight The new ambientLight
     * @return The function return the camera (by "this").
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Setter function for background
     * @param background The new background
     * @return The function return the camera (by "this").
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Setter function for geometries
     * @param geometries The new geometries
     * @return The function return the camera (by "this").
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
