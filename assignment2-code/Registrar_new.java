import java.util.ArrayList;
import java.util.Scanner;

public class Registrar {
    ArrayList<Student> students;
    ArrayList<Course> courses;

    public Registrar() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    /* Hardcoded example. Instead, you need to implement new functions that read
    the input from the terminal and print the corresponding outputs. */
    public void runExampleCommands() {
        Scanner keyboard = new Scanner(System.in);
        while (keyboard.hasNext()){
            String command = keyboard.nextLine();

            switch (command.split(" ")[0]){
                case "COURSE":
                    this.courses.add(new Course(command.split(" ")[1]));
                    break;

                case "STUDENT":
                    this.students.add(new Student(command.split(" ")[1],
                            Integer.parseInt(command.split(" ")[2])));
                    break;

                case "SECTION":
                    for (Course element: courses){
                        if (element.getUniqueName().equals(command.split(" ")[1])){
                            //TODO this line will not work with your current implementations of addSection() method in Course class -> Mistake: should not pass in a new Section object
                            element.addSection(new Section((command.split(" ")[2]),
                                    Integer.parseInt(command.split(" ")[3])));
                        }
                    }
                    break;

                case "ENROLL":
                    for (Student a: students){
                        if (a.getStudentID()==(Integer.parseInt(command.split(" ")[1]))){
                            for (Course element: courses){
                                if (element.getUniqueName().equals((command.split(" ")[2]))){
                                    boolean enrollmentResult = element.enrollStudent(a,
                                            (command.split(" ")[3]));
                                    TerminalPrinter.printEnrollmentResult(enrollmentResult, a.getName(),
                                            element.getUniqueName(), (command.split(" ")[3]));
                                }
                            }
                        }
                    }
                    break;

                case "UNENROLL":
                    for (Student a: students){
                        if (a.getStudentID()==(Integer.parseInt(command.split(" ")[1]))){
                            for (Course element: courses){
                                if (element.getUniqueName().equals((command.split(" ")[2]))){
                                    boolean enrollmentResult = element.unenrollStudent(a,
                                            (command.split(" ")[3]));
                                    TerminalPrinter.printUnenrollmentResult(enrollmentResult, a.getName(),
                                            element.getUniqueName(), (command.split(" ")[3]));
                                }
                            }
                        }
                    }
                    break;

                //case "REQUIREMENT":
                    //TODO Call the necessary functions to add requirement

                //    break;
            }
            if (command.equals("FINISH")) {
                break;
            }
        }
    }
//        Course course1 = new Course("CS2XYZ");
//        Course course2 = new Course("CS1ABC");
//
//        Student student1 = new Student("EMMA", 23345);
//        Student student2 = new Student("DAVID", 123345);
//
//        course1.addSection(new Section("C01", 10));
//        course2.addSection(new Section("C02", 1));
//
//        boolean enrollmentResult = course1.enrollStudent(student1, "C01");
//        TerminalPrinter.printEnrollmentResult(enrollmentResult, student1.getName(),
//                course1.getUniqueName(), "C01");
//
//        enrollmentResult = course1.enrollStudent(student2, "C01");
//        TerminalPrinter.printEnrollmentResult(enrollmentResult, student2.getName(),
//                course1.getUniqueName(), "C01");
//
//        enrollmentResult = course2.enrollStudent(student1, "C02");
//        TerminalPrinter.printEnrollmentResult(enrollmentResult, student1.getName(),
//                course2.getUniqueName(), "C02");
//
//        enrollmentResult = course2.enrollStudent(student2, "C02");
//        TerminalPrinter.printEnrollmentResult(enrollmentResult, student2.getName(),
//                course2.getUniqueName(), "C02");
//
//        enrollmentResult = course2.unenrollStudent(student1, "C02");
//        TerminalPrinter.printUnenrollmentResult(enrollmentResult, student1.getName(),
//                course2.getUniqueName(), "C02");
//
//        enrollmentResult = course2.unenrollStudent(student2, "C02");
//        TerminalPrinter.printUnenrollmentResult(enrollmentResult, student2.getName(),
//                course2.getUniqueName(), "C02");
//
//        enrollmentResult = course2.enrollStudent(student2, "C02");
//        TerminalPrinter.printEnrollmentResult(enrollmentResult, student2.getName(),
//                course2.getUniqueName(), "C02");
}
