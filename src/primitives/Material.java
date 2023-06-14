package primitives;

public class Material {

    /*
    kD - The kD parameter determines how strong the light diffusion would be.
    ks - The ks parameter determines how strong the specular light would be.
    kT - The kT parameter determines the transparency of the material.
    kR - The kR parameter determines how much light would be reflected by the material.
     */
    public Double3 kD = Double3.ZERO, ks = Double3.ZERO, kT = Double3.ZERO, kR = Double3.ZERO;

    public double SnellParameter = 1; // An optional value for snell law calculation.
    public int nShininess = 1;

    /***
     * Setter function for kD
     * @param kD The Material's new kD
     * @return The Material (by this).
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }
    /***
     * Setter function for kD
     * @param kD The Material's new kD
     * @return The Material (by this).
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /***
     * Setter function for ks
     * @param ks The Material's new ks
     * @return The Material (by this).
     */
    public Material setKs(Double3 ks) {
        this.ks = ks;
        return this;
    }
    /***
     * Setter function for ks
     * @param ks The Material's new ks
     * @return The Material (by this).
     */
    public Material setKs(double ks) {
        this.ks = new Double3(ks);
        return this;
    }

    /***
     * Setter function for nShininess
     * @param nShininess The Material's new nShininess
     * @return The Material (by this).
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /***
     * Setter function for kT
     * @param kT The Material's new kT
     * @return The Material (by this).
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }
    /***
     * Setter function for kT
     * @param kT The Material's new kT
     * @return The Material (by this).
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /***
     * Setter function for kR
     * @param kR The Material's new kR
     * @return The Material (by this).
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }
    /***
     * Setter function for kR
     * @param kR The Material's new kR
     * @return The Material (by this).
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /***
     * An optional value for snell law calculation.
     * Setter function for n
     * @param n The Material's new n
     * @return The Material (by this).
     */
    public Material setSnellParameter(double n) {
        this.SnellParameter = n;
        return this;
    }
}
