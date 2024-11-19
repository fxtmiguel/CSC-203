import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpaghettiTests {
    // Insert your tests below

    // Insert your tests above

    @Test
    void testSpaghettiInstantiation() {
        try {
            Class<?> clazz = Class.forName("Spaghetti");
            Constructor<?> constructor = clazz.getConstructor(double.class, String.class);
            Object obj = constructor.newInstance(1, "");
            assertNotNull(obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            fail("Class not found or cannot be instantiated: " + e.getClass());
        }
    }
    @Test
    public void testStringify() {
        Spaghetti spaghetti = new Spaghetti(10.0, "Alfredo");
        assertEquals("10.0,Alfredo", spaghetti.stringify());
    }

    @Test
    public void testToString() {
        Spaghetti spaghetti = new Spaghetti(5.0, "Marinara");
        assertEquals("5.0,Marinara", spaghetti.toString());
    }

    @Test
    public void testEquals() {
        Spaghetti spaghetti1 = new Spaghetti(2.0, "Marinara");
        Spaghetti spaghetti2 = new Spaghetti(2.0, "Marinara");
        Spaghetti spaghetti3 = new Spaghetti(9.0, "Alfredo");

        assertTrue(spaghetti1.equals(spaghetti2));
        assertFalse(spaghetti1.equals(spaghetti3));
        assertFalse(spaghetti1.equals(null));

    }

    @Test
    public void testHashCode() {
        Spaghetti spaghetti = new Spaghetti(30.0, "Pesto");
        int expected = 17;
        expected = 31 * expected + Double.hashCode(30.0);
        expected = 31 * expected + "Pesto".hashCode();
        assertEquals(expected, spaghetti.hashCode());
    }
}