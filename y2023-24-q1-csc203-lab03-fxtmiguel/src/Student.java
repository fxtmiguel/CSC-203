import java.util.List;
import java.util.Objects;

public class Student
{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    private double gpa;

    public Student(final String name, final int age,
                   final double gpa)
    {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getStudentInfo(){
        return this.name + " " + this.age + " " + this.gpa + " ";
    }
    public String getLetterGrade() {
        if (gpa >= 4.0) {
            return "A";
        } else if (gpa >= 3.0) {
            return "B";
        } else if (gpa >= 2.0) {
            return "C";
        } else if (gpa >= 1.0) {
            return "D";
        } else {
            return "F";
        }
    }

}
