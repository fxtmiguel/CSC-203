import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTests {
    @org.junit.Test
    public void testGetName() {

        Student student = new Student("William", 20, 3.5);
        assertEquals("William", student.getName());
    }

    @org.junit.Test
    public void testGetAge() {
        Student student = new Student("Alice", 20, 3.5);
        assertEquals(20, student.getAge());
    }

    @org.junit.Test
    public void testGetGpa() {
        Student student = new Student("Nicole", 20, 3.7);
        assertEquals(3.7, student.getGpa(), 0.01);
    }

    @org.junit.Test
    public void testGetStudentInfo() {
        Student student = new Student("Alice", 20, 3.5);
        assertEquals("Alice 20 3.5 ", student.getStudentInfo());
    }

    @org.junit.Test
    public void testGetStudentInfo2() {
        Student student = new Student("Mike", 26, 3.3);
        assertEquals("Mike 26 3.3 ", student.getStudentInfo());
    }

    @org.junit.Test
    public void testGetStudentInfo3() {
        Student student = new Student("Ali", 43, 3.4);

        // Test getStudentInfo() method
        assertEquals("Ali 43 3.4 ", StudentFunctions.getStudentInfo(student));
    }

    @org.junit.Test
    public void testGetStudentInfo4() {
        Student student = new Student("Miguel", 3, 3.4);

        // Test getStudentInfo() method
        assertEquals("Miguel 3 3.4 ", StudentFunctions.getStudentInfo(student));
    }
    @org.junit.Test
    public void testGetLetterGrade() {
        Student student = new Student("Bob", 20, 3.5);
        assertEquals("B", student.getLetterGrade());
    }

    @org.junit.Test
    public void testGetLetterGrade2() {
        Student student = new Student("Rob", 20, 2.5);
        assertEquals("C", student.getLetterGrade());
    }
    @org.junit.Test
    public void testGetLetterGrade3() {
        Student student = new Student("Robert", 26, 1.6);
        assertEquals("D", StudentFunctions.getLetterGrade(student));
    }

    @Test
    public void testGetLetterGrade4() {
        Student student = new Student("Robert", 26, 4.0);
        assertEquals("A", StudentFunctions.getLetterGrade(student));
    }
}