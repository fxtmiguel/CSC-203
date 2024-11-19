import java.lang.Math;
import java.util.ArrayList;


class Lab01 {
    public static int count(String s, String c) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c.charAt(0)) {
                sum += 1;
            }
        }
        return sum;
    }

    public static void main(String[] args){
        int x = 2;
        double y = 4.0;
        double z = (int) Math.pow(x, y) / 3;

        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("z: " + z);
        int[] a = {1,2,3,4};
        for (int n : a) {
            System.out.print(n +" ");
        }
        System.out.println();

        String b = "mochimochimochi";
        int found = count(b, "o");
        System.out.println(found);

        ArrayList<Double> c = new ArrayList<>();
        c.add(6.5);
        c.add(4.3);
        c.add(2.1);
            for (int i = 0;i < c.size() ; i++  ){
                System.out.println(i + ": " + c.get(i));
        }

    }

    }

