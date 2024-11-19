import java.util.Random;

public class NumberUtil {



    public static int clamp(int value, int low, int high) {
        return Math.min(high, Math.max(value, low));
    }
}
