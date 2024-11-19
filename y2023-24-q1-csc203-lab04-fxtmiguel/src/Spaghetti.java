import java.util.Objects;

public class Spaghetti implements Stringifiable{
    private double noodleDiameter;

    private String sauce;

    public Spaghetti(double noodleDiameter, String sauce){
        this.noodleDiameter = noodleDiameter;
        this.sauce = sauce;

    }

    @Override
    public String stringify() {
        return this.noodleDiameter + "," + this.sauce;
    }

    @Override
    public String toString() {
        return this.noodleDiameter + "," + this.sauce;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Spaghetti o = (Spaghetti) obj;
        return Double.compare(noodleDiameter, o.noodleDiameter ) == 0 && sauce.equals(o.sauce);

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.hashCode(noodleDiameter);
        result = 31 * result + sauce.hashCode();
        return result;
    }

}

