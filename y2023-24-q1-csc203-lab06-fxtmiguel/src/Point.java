public class Point {
    public double x;
    public double y;
    public double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point scale(double xscale, double yscale, double zscale){
        this.x = x * xscale;
        this.y = y * yscale;
        this.z = z * zscale;
        return this;
    }

    public Point translate(double xtranslate, double ytranslate, double ztranslate){
        this.x = x + xtranslate;
        this.y = y + ytranslate;
        this.z = z + ztranslate;

        return this;
    }

}
