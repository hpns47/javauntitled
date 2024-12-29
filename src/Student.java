import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Student extends Person {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
    private int studentID;
    private List<Integer> grades;

    public Student(String name, String surname, int age, boolean gender) {
        super(name, surname, age, gender);
        this.studentID = ID_GENERATOR.getAndIncrement();
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
        } else {
            throw new IllegalArgumentException("grade isnot between 0 and 100.");
        }
    }

    public double calculateGPA() {
        return grades.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }

    @Override
    public String toString() {
        return super.toString() + " I am a student with ID " + studentID + ".";
    }
}