package primitives;

public class Material {
    public Double3 kD = Double3.ZERO, ks = Double3.ZERO;
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
}
