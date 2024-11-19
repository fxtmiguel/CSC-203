import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreatureClassesTests {
    @Test
    void testGrassDinosaurInitialization() {
        try {
            Class<?> clazz = Class.forName("GrassDinosaur");
            Constructor<?> constructor = clazz.getConstructor(String.class);
            Object obj = constructor.newInstance("rose");
            assertNotNull(obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            fail("Class not found or cannot be instantiated: " + e.getMessage());
        }
    }

    @Test
    void testFireLizardInitialization() {
        try {
            Class<?> clazz = Class.forName("FireLizard");
            Constructor<?> constructor = clazz.getConstructor(double.class);
            Object obj = constructor.newInstance(600.0);
            assertNotNull(obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            fail("Class not found or cannot be instantiated: " + e.getMessage());
        }
    }

    @Test
    void testWaterTurtleInitialization() {
        try {
            Class<?> clazz = Class.forName("WaterTurtle");
            Constructor<?> constructor = clazz.getConstructor();
            Object obj = constructor.newInstance();
            assertNotNull(obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            fail("Class not found or cannot be instantiated: " + e.getMessage());
        }
    }
}