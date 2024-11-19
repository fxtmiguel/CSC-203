import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ClassTests {
    @Test
    public void addTest() {
        Animal animal = new Animal("Lion", 3);
        animal.addTeeth(12);
        animal.addTeeth(11);
        animal.addTeeth(10);
        animal.addTeeth(11);
        List<Integer> teethsize = Arrays.asList(12, 11, 10, 11);
        Assertions.assertEquals(teethsize ,animal.getTeethSize());
    }
    @Test
    public void addTest2() {
        Animal animal = new Animal("Lion", 3);
        animal.addTeeth(1);
        animal.addTeeth(4);
        animal.addTeeth(10);
        animal.addTeeth(11);
        List<Integer> teethsize = Arrays.asList(2, 5, 10, 11);
        Assertions.assertEquals(teethsize ,animal.getTeethSize());
    }

    @Test
    public void ageTest() {
        Animal animal = new Animal("Camel", 2);
        Assertions.assertTrue(animal.isToddler());
    }

    @Test
    public void averageSize() {
        Animal animal = new Animal("Hippo", 3);
        animal.addTeeth(12);
        animal.addTeeth(11);
        animal.addTeeth(10);
        animal.addTeeth(10);
        Assertions.assertEquals(10.75 ,animal.findAverageLength());
    }
    @Test
    public void averageSize2() {
        Animal animal = new Animal("Hippo", 3);
        Assertions.assertEquals(0 ,animal.findAverageLength());
    }
}