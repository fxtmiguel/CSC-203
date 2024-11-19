import java.util.SplittableRandom;

public class StudentFunctions {


    private String name;
    private int age;
    private double gpa;

    public StudentFunctions(final String name, final int age,
                            final double gpa)
    {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public static String getStudentInfo(Student student){
        return student.getName() + " " + student.getAge() + " " + student.getGpa() + " ";
    }
    public static String getLetterGrade(Student student) {
        if (student.getGpa() >= 4.0) {
            return "A";
        } else if (student.getGpa() >= 3.0) {
            return "B";
        } else if (student.getGpa() >= 2.0) {
            return "C";
        } else if (student.getGpa() >= 1.0) {
            return "D";
        } else {
            return "F";
        }
    }

}
