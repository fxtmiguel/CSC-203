import java.util.ArrayList;
import java.util.List;

public class Animal {
    private String name;
    private static int age;

    private List<Integer> teethsize;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        this.teethsize = new ArrayList<>();
        ;
    }

    public List<Integer> addTeeth( int feet) {
        if (feet > 5) {
            teethsize.add(feet);
        } else {
            teethsize.add(feet+1);
        }
        return teethsize;

    }

    public double findAverageLength() {
        if (teethsize.isEmpty()) {
            return 0;
        }else {
            int sizeSum = 0;
            for (int length : teethsize) {
                sizeSum += length;

            }
            return (double) sizeSum/ teethsize.size();
        }
    }

    public static boolean isToddler() {
       return age < 3;

    }

    public List<Integer> getTeethSize(){
        return teethsize;
    }
}
