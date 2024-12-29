import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        School school = new School();


        try (BufferedReader br = new BufferedReader(new FileReader("src/students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                String surname = parts[1];
                int age = Integer.parseInt(parts[2]);
                boolean gender = parts[3].equalsIgnoreCase("Male");

                Student student = new Student(name, surname, age, gender);

                for (int i = 4; i < parts.length; i++) {
                    student.addGrade(Integer.parseInt(parts[i]));
                }

                school.addMember(student);
            }
        } catch (IOException e) {
            System.err.println("Error reading students.txt: " + e.getMessage());
        }


        try (BufferedReader br = new BufferedReader(new FileReader("src/teachers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                String surname = parts[1];
                int age = Integer.parseInt(parts[2]);
                boolean gender = parts[3].equalsIgnoreCase("Male");
                String subject = parts[4];
                int yearsOfExperience = Integer.parseInt(parts[5]);
                int salary = Integer.parseInt(parts[6]);

                Teacher teacher = new Teacher(name, surname, age, gender, subject, yearsOfExperience, salary);
                school.addMember(teacher);
            }
        } catch (IOException e) {
            System.err.println("Error reading teachers.txt: " + e.getMessage());
        }


        for (Person member : school.getMembers()) {
            if (member instanceof Student) {
                Student student = (Student) member;
                System.out.println(student.toString() + " GPA: " + student.calculateGPA());
            }

            if (member instanceof Teacher) {
                Teacher teacher = (Teacher) member;
                if (teacher.getYearsOfExperience() > 10) {
                    teacher.giveRaise(10);
                }
                System.out.println(teacher.toString() + " Salary: " + teacher.getSalary());
            }
        }


        System.out.println("\nAll School Members:");
        school.getMembers().sort((a, b) -> ((Person) a).getSurname().compareTo(((Person) b).getSurname()));
        System.out.println(school.toString());
    }
}